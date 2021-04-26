package com.ibrahim.ibtikar_task.base

import androidx.room.*
import com.ibrahim.ibtikar_task.notes.data.model.Note
import com.ibrahim.ibtikar_task.notes.data.source.local.NotestDao

@Database(
    entities = [Note::class],
    version = 5 , exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun notesDao(): NotestDao

}

