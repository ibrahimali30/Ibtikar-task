package com.ibrahim.ibtikar_task.notes.data.repository


import android.os.Build
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import com.google.common.truth.Truth
import com.ibrahim.ibtikar_task.RxSchedulerRule
import com.ibrahim.ibtikar_task.base.AppDatabase
import com.ibrahim.ibtikar_task.notes.data.source.local.NotesLocalDataSource
import com.ibrahim.ibtikar_task.notes.data.source.local.NotestDao
import com.ibrahim.ibtikar_task.utils.generateNoteEntity
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.verify
import io.reactivex.Flowable
import io.reactivex.Maybe
import io.reactivex.Single
import org.junit.Test
import org.junit.Before
import org.junit.Rule
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnit
import org.mockito.junit.MockitoRule
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@Config(sdk = [Build.VERSION_CODES.P])
@RunWith(RobolectricTestRunner::class)
class NotesRepositoryImplTest {

    @get:Rule
    val mockitoRule: MockitoRule = MockitoJUnit.rule()

    @get:Rule
    val taskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val rxSchedulerRule = RxSchedulerRule()


    @MockK
    lateinit var notesLocalDataSource: NotesLocalDataSource

    private lateinit var notesDatabase: AppDatabase
    private lateinit var forecastDao: NotestDao


    private lateinit var notesRepository: NotesRepositoryImpl

    val notesMok = generateNoteEntity()

    @Before
    fun setUp() {
        MockKAnnotations.init(this)

        notesDatabase = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            AppDatabase::class.java
        ).allowMainThreadQueries()
            .build()

        forecastDao = notesDatabase.notesDao()
        notesRepository = NotesRepositoryImpl(notesLocalDataSource)

    }


    @Test
    fun getAndObserveNotes() {

        every {
            notesLocalDataSource.getAndObserveNotes()
        } returns Flowable.just(listOf(notesMok))

        notesRepository.getAndObserveNotes()
            .subscribe {
                Truth.assertThat(it[0].id).isEqualTo(notesMok.id)
            }

        verify { notesLocalDataSource.getAndObserveNotes() }
    }

    @Test
    fun insertRecipe() {
        every {
            notesLocalDataSource.insertNote(notesMok)
        } returns Maybe.just(1)

        notesRepository.insertNote(notesMok)
            .subscribe {
                Truth.assertThat(it).isEqualTo(1)
            }

        verify { notesLocalDataSource.insertNote(notesMok) }
    }

    @Test
    fun deleteNote() {
        every {
            notesLocalDataSource.deleteNote(notesMok)
        } returns Single.just(1)

        notesRepository.deleteNote(notesMok)
            .subscribe({
                Truth.assertThat(it).isEqualTo(1)
            }, {

            })

        verify { notesLocalDataSource.deleteNote(notesMok) }
    }



}