package com.ibrahim.ibtikar_task.notes.data.source.local

import android.annotation.SuppressLint
import com.ibrahim.ibtikar_task.notes.data.model.Note
import io.reactivex.Flowable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject


class NotesLocalDataSource @Inject constructor(
    private val notestDao: NotestDao
) {

    @SuppressLint("CheckResult")
    fun insertNote(recipeItem: Note) {
        notestDao.insertNote(recipeItem)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                it
            }, {})
    }

    fun deleteNote(recipeItem: Note) {
        notestDao.deleteNote(recipeItem)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                it
            }, {})
    }

    fun getFavouriteNotes(): Flowable<List<Note>> {
        return notestDao.getAllNotes()
    }


}