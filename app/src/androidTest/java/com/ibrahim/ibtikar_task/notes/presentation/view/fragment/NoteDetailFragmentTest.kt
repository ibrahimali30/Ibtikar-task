package com.ibrahim.ibtikar_task.notes.presentation.view.fragment

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import com.ibrahim.ibtikar_task.base.AppDatabase
import com.ibrahim.ibtikar_task.notes.data.source.local.NotestDao
import com.ibrahim.ibtikar_task.notes.data.source.local.generateRecipesEntity
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class NoteDetailFragmentTest{

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
    fun generalFragmentTest(){

//        Espresso.onView(withText(entity.title))
//            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))

    }






}