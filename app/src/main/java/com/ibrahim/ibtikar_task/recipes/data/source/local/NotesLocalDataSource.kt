package com.ibrahim.ibtikar_task.recipes.data.source.local

import android.annotation.SuppressLint
import com.ibrahim.ibtikar_task.recipes.data.model.NoteItem
import io.reactivex.Flowable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject


class NotesLocalDataSource @Inject constructor(
    private val forecastDao: NotestDao
) {

    @SuppressLint("CheckResult")
    fun insertRecipe(recipeItem: NoteItem) {
        TODO("Not yet implemented")

    }

    fun getFavouriteNotes(): Flowable<List<NoteItem>> {
        TODO("Not yet implemented")
    }


}