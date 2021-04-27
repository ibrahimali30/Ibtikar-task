package com.ibrahim.ibtikar_task.notes.data.source.local

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import com.google.common.truth.Truth
import com.ibrahim.ibtikar_task.base.AppDatabase
import com.ibrahim.ibtikar_task.notes.data.model.Note
import io.mockk.impl.annotations.MockK
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule

class NotesLocalDataSourceTest {


    @Rule
    @JvmField
    val instantExecutorRule = InstantTaskExecutorRule()

    private lateinit var recipesDatabase: AppDatabase
    private lateinit var forecastDao: NotestDao

    @Before
    fun setUp() {
        recipesDatabase = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            AppDatabase::class.java
        ).allowMainThreadQueries()
            .build()

        forecastDao = recipesDatabase.notesDao()
    }

    val recipe = generateRecipesEntity()


    @Test
    fun insertNote() {
        forecastDao.insertNote(recipe)
            .subscribe {
                Truth.assertThat(it).isGreaterThan(0)
            }
    }

    @Test
    fun deleteNote() {
        insertNote()
        forecastDao.deleteNote(recipe)
            .subscribe({
                Truth.assertThat(it).isGreaterThan(0)
            },{

            })
    }

    @Test
    fun getAndObserveNotes() {
        insertNote()
        forecastDao.getAllNotes()
            .subscribe {
                Truth.assertThat(it.size).isGreaterThan(0)
                Truth.assertThat(it[0]).isEqualTo(recipe)
            }
    }
}

fun generateRecipesEntity(id: Int = 1): Note {
    return Note("title", "body", "date", 1234234, id)
}