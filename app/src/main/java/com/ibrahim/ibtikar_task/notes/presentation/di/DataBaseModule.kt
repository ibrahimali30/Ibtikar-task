package com.ibrahim.ibtikar_task.notes.presentation.di

import android.app.Application
import androidx.room.Room
import com.ibrahim.ibtikar_task.base.AppDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent


@Module
@InstallIn(SingletonComponent::class)
class DataBaseModule {

    @Provides
    fun provideLocalDatabase(context: Application): AppDatabase {
        return Room.databaseBuilder(context, AppDatabase::class.java, "NotesDatabase")
            .fallbackToDestructiveMigration()
            .build()
    }


}