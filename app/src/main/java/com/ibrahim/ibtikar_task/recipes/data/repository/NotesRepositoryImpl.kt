package com.ibrahim.ibtikar_task.recipes.data.repository


import com.ibrahim.ibtikar_task.recipes.data.model.NoteItem
import com.ibrahim.ibtikar_task.recipes.data.source.local.NotesLocalDataSource
import com.ibrahim.ibtikar_task.recipes.domain.repsitory.NotesRepository
import io.reactivex.Flowable
import javax.inject.Inject


class NotesRepositoryImpl @Inject constructor(
    private val recipesLocalDataSource: NotesLocalDataSource
) : NotesRepository {

    override fun insertRecipe(recipeItem: NoteItem) = recipesLocalDataSource.insertRecipe(recipeItem)

    override fun getFavouriteNotes(): Flowable<List<NoteItem>> {
        return recipesLocalDataSource.getFavouriteNotes()
    }


}
