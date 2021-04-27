package com.ibrahim.ibtikar_task.notes.data.source.local

import android.os.Build
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import com.google.common.truth.Truth
import com.ibrahim.ibtikar_task.RxSchedulerRule
import com.ibrahim.ibtikar_task.base.AppDatabase
import com.ibrahim.ibtikar_task.notes.data.repository.NotesRepositoryImpl
import com.ibrahim.ibtikar_task.utils.generateNoteEntity
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.verify
import io.reactivex.Maybe
import io.reactivex.Single
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnit
import org.mockito.junit.MockitoRule
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config


@Config(sdk = [Build.VERSION_CODES.P])
@RunWith(RobolectricTestRunner::class)
class NotesLocalDataSourceTest {


    @get:Rule
    val mockitoRule: MockitoRule = MockitoJUnit.rule()

    @get:Rule
    val taskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val rxSchedulerRule = RxSchedulerRule()

    @MockK
    lateinit var recipesLocalDataSource: NotesLocalDataSource

    private lateinit var recipesDatabase: AppDatabase
    private lateinit var forecastDao: NotestDao

    val note = generateNoteEntity(1)

    @Before
    fun setUp() {
        MockKAnnotations.init(this)

        recipesDatabase = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            AppDatabase::class.java
        ).allowMainThreadQueries()
            .build()

        forecastDao = recipesDatabase.notesDao()

    }

    @Test
    fun insertNote() {
        every {
            forecastDao.insertNote(note)
        } returns Maybe.just(1)

        recipesLocalDataSource.insertNote(note)
            .subscribe {
                Truth.assertThat(it).isEqualTo(1)
            }

        verify { forecastDao.insertNote(note) }
    }

    @Test
    fun deleteNote() {
    }

    @Test
    fun getAndObserveNotes() {
    }
}