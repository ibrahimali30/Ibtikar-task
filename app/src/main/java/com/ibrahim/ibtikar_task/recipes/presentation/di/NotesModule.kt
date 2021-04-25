package com.ibrahim.ibtikar_task.recipes.presentation.di

import com.ibrahim.ibtikar_task.base.AppDatabase
import com.ibrahim.ibtikar_task.recipes.data.repository.NotesRepositoryImpl
import com.ibrahim.ibtikar_task.recipes.domain.repsitory.NotesRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent

@Module
@InstallIn(ActivityComponent::class)
class NotesModule {

    @Provides
    fun providesNotesRepository(recipesRepositoryImpl: NotesRepositoryImpl): NotesRepository = recipesRepositoryImpl



    @Provides
    fun providesNotesDao(recipesDatabase: AppDatabase) = recipesDatabase.recipestDao()


}