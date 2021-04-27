package com.ibrahim.ibtikar_task.notes.data.source.local

import android.annotation.SuppressLint
import com.ibrahim.ibtikar_task.notes.data.model.Note
import io.reactivex.Flowable
import io.reactivex.Maybe
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject


class NotesLocalDataSource @Inject constructor(
    private val notestDao: NotestDao
) {

    @SuppressLint("CheckResult")
    fun insertNote(recipeItem: Note): Maybe<Long> {
        return notestDao.insertNote(recipeItem)
    }

    @SuppressLint("CheckResult")
    fun deleteNote(recipeItem: Note): Single<Int> {
        return notestDao.deleteNote(recipeItem)
    }

    fun getAndObserveNotes(): Flowable<List<Note>> {
        return notestDao.getAllNotes()
    }


}