package com.ibrahim.ibtikar_task.recipes.data.source.local

import androidx.room.*
import com.ibrahim.ibtikar_task.recipes.data.model.NoteItem
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Maybe
import io.reactivex.Single


@Dao
interface NotestDao {


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertRecipe(recipeItem: NoteItem): Long

    @Delete
    fun deleteRecipe(recipeItem: NoteItem): Int

    @Query("select * from NoteItem")
    fun getFavouriteNotes(): List<NoteItem>


}