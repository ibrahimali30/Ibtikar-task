package com.ibrahim.ibtikar_task.notes.domain.repsitory


import com.ibrahim.ibtikar_task.notes.data.model.Note
import io.reactivex.Flowable

interface NotesRepository {
    fun insertNote(recipeItem: Note)
    fun deleteNote(recipeItem: Note)
    fun getFavouriteNotes(): Flowable<List<Note>>
}