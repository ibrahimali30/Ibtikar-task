package com.ibrahim.ibtikar_task.recipes.domain.repsitory


import com.ibrahim.ibtikar_task.recipes.data.model.NoteItem
import io.reactivex.Flowable

import io.reactivex.Single

interface NotesRepository {
    fun insertRecipe(recipeItem: NoteItem)
    fun getFavouriteNotes(): Flowable<List<NoteItem>>
}