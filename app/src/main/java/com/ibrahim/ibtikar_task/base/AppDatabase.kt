package com.ibrahim.ibtikar_task.base

import androidx.room.*
import com.ibrahim.ibtikar_task.recipes.data.model.NoteItem
import com.ibrahim.ibtikar_task.recipes.data.source.local.NotestDao

@Database(
    entities = [NoteItem::class],
    version = 1 , exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun recipestDao(): NotestDao

}

