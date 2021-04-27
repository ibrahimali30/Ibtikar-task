package com.ibrahim.ibtikar_task.notes.domain.repsitory


import com.ibrahim.ibtikar_task.notes.data.model.Note
import io.reactivex.Flowable
import io.reactivex.Maybe
import io.reactivex.Single

interface NotesRepository {
    fun insertNote(recipeItem: Note): Maybe<Long>
    fun deleteNote(recipeItem: Note): Single<Int>
    fun getAndObserveNotes(): Flowable<List<Note>>
}