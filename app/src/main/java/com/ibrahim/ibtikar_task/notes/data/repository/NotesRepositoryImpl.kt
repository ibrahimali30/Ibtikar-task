package com.ibrahim.ibtikar_task.notes.data.repository


import com.ibrahim.ibtikar_task.notes.data.model.Note
import com.ibrahim.ibtikar_task.notes.data.source.local.NotesLocalDataSource
import com.ibrahim.ibtikar_task.notes.domain.repsitory.NotesRepository
import io.reactivex.Flowable
import javax.inject.Inject


class NotesRepositoryImpl @Inject constructor(
    private val recipesLocalDataSource: NotesLocalDataSource
) : NotesRepository {

    override fun insertNote(recipeItem: Note) = recipesLocalDataSource.insertNote(recipeItem)

    override fun deleteNote(recipeItem: Note) = recipesLocalDataSource.deleteNote(recipeItem)

    override fun getAndObserveNotes(): Flowable<List<Note>> {
        return recipesLocalDataSource.getAndObserveNotes()
    }


}
