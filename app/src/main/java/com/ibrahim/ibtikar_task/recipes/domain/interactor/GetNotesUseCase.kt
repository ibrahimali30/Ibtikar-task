package com.ibrahim.ibtikar_task.recipes.domain.interactor
import com.ibrahim.ibtikar_task.recipes.data.model.NoteItem
import com.ibrahim.ibtikar_task.recipes.domain.repsitory.NotesRepository
import io.reactivex.Flowable
import io.reactivex.Single
import javax.inject.Inject

class GetNotesUseCase @Inject constructor(private val recipesRepository: NotesRepository) {

    fun fetchNotes(): Single<ArrayList<NoteItem>> {
        TODO("Not yet implemented")
    }

    fun insertRecipe(recipeItem: NoteItem) = recipesRepository.insertRecipe(recipeItem)

    fun getFavouriteNotes(): Flowable<List<NoteItem>> {
        return recipesRepository.getFavouriteNotes()
    }

}