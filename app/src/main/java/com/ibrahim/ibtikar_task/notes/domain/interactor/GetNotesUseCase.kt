package com.ibrahim.ibtikar_task.notes.domain.interactor
import com.ibrahim.ibtikar_task.notes.data.model.Note
import com.ibrahim.ibtikar_task.notes.domain.repsitory.NotesRepository
import io.reactivex.Flowable
import io.reactivex.Maybe
import io.reactivex.Single
import javax.inject.Inject

class GetNotesUseCase @Inject constructor(private val recipesRepository: NotesRepository) {


    fun insertNote(recipeItem: Note): Maybe<Long> {
        return recipesRepository.insertNote(recipeItem)
    }

    fun deleteNote(recipeItem: Note): Single<Int> {
        return recipesRepository.deleteNote(recipeItem)
    }

    fun getAndObserveNotes(): Flowable<List<Note>> {
        return recipesRepository.getAndObserveNotes()
    }

}