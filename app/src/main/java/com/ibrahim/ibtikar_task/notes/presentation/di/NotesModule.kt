package com.ibrahim.ibtikar_task.notes.presentation.di

import android.app.Application
import com.ibrahim.ibtikar_task.base.AppDatabase
import com.ibrahim.ibtikar_task.notes.data.repository.NotesRepositoryImpl
import com.ibrahim.ibtikar_task.notes.data.source.local.NotestDao
import com.ibrahim.ibtikar_task.notes.domain.repsitory.NotesRepository
import com.ibrahim.ibtikar_task.utils.AppAlarmManager
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent

@Module
@InstallIn(ActivityComponent::class)
class NotesModule {

    @Provides
    fun providesNotesRepository(recipesRepositoryImpl: NotesRepositoryImpl): NotesRepository {
        return recipesRepositoryImpl
    }

    @Provides
    fun providesNotesDao(recipesDatabase: AppDatabase): NotestDao {
        return recipesDatabase.notesDao()
    }

    @Provides
    fun providesAlarmManager(context: Application):AppAlarmManager{
        return AppAlarmManager(context)
    }


}