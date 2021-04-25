package com.ibrahim.ibtikar_task.recipes.presentation.viewmodel

import android.os.Handler
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ibrahim.ibtikar_task.recipes.data.model.NoteItem
import com.ibrahim.ibtikar_task.recipes.domain.interactor.GetNotesUseCase
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class NotesViewModel @Inject constructor(
    private val refreshForecastUseCase: GetNotesUseCase
): ViewModel() {

    private val compositeDisposable = CompositeDisposable()

    val screenState by lazy { MutableLiveData<ScreenState>() }
    val favouriteNotesList by lazy { MutableLiveData<List<NoteItem>>() }


    fun getFetchNotes() {

        screenState.value = ScreenState.Loading

        refreshForecastUseCase.fetchNotes()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                handleSuccessResponse(it)
            }, {
                handleErrorResponse(it)
            }).addTo(compositeDisposable)
    }

    fun getFavouriteNotes() {
        refreshForecastUseCase.getFavouriteNotes()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                favouriteNotesList.value = it
            }, {

            }).addTo(compositeDisposable)
    }

    fun insertRecipe(recipeItem: NoteItem) {
        refreshForecastUseCase.insertRecipe(recipeItem)
    }



    fun handleErrorResponse(it: Throwable) {
        screenState.value = ScreenState.ErrorLoadingFromApi(it)
    }

    private fun handleSuccessResponse(it: ArrayList<NoteItem>) {
        screenState.value = ScreenState.SuccessAPIResponse(it)
    }

    private fun delay(delay: Long = 1000 , function: () -> Unit) {
        Handler().postDelayed(
            {function() }, delay
        )
    }

    sealed class ScreenState {
        object Loading : ScreenState()
        class SuccessAPIResponse(val data: List<NoteItem>) : ScreenState()
        class ErrorLoadingFromApi(val error: Throwable) : ScreenState()
    }

    override fun onCleared() {
        compositeDisposable.dispose()
        super.onCleared()
    }
}