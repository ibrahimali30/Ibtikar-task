package com.ibrahim.ibtikar_task.notes.data.source.local

import androidx.room.*
import com.ibrahim.ibtikar_task.notes.data.model.Note
import io.reactivex.Flowable
import io.reactivex.Maybe
import io.reactivex.Single


@Dao
interface NotestDao {


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertNote(Note: Note): Maybe<Long>

    @Delete
    fun deleteNote(Note: Note): Single<Int>

    @Query("select * from Note")
    fun getAllNotes(): Flowable<List<Note>>


}