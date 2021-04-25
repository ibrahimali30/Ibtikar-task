package com.ibrahim.ibtikar_task.notes.domain.interactor
import com.ibrahim.ibtikar_task.notes.data.model.Note
import com.ibrahim.ibtikar_task.notes.domain.repsitory.NotesRepository
import io.reactivex.Flowable
import io.reactivex.Single
import javax.inject.Inject

class GetNotesUseCase @Inject constructor(private val recipesRepository: NotesRepository) {


    fun insertNote(recipeItem: Note) = recipesRepository.insertNote(recipeItem)

    fun deleteNote(recipeItem: Note) = recipesRepository.deleteNote(recipeItem)

    fun getFavouriteNotes(): Flowable<List<Note>> {
        return recipesRepository.getFavouriteNotes()
    }

}