package com.ibrahim.ibtikar_task.notes.presentation.viewmodel

import android.os.Handler
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ibrahim.ibtikar_task.notes.data.model.Note
import com.ibrahim.ibtikar_task.notes.domain.interactor.GetNotesUseCase
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class NotesViewModel @Inject constructor(
    private val refreshForecastUseCase: GetNotesUseCase
): ViewModel() {

    private val compositeDisposable = CompositeDisposable()

    val screenState by lazy { MutableLiveData<List<Note>>() }


    fun getFavouriteNotes() {
        refreshForecastUseCase.getFavouriteNotes()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                handleSuccessResponse(it)
            }, {

            }).addTo(compositeDisposable)
    }

    fun insertNote(recipeItem: Note?) {
        recipeItem ?: return
        refreshForecastUseCase.insertNote(recipeItem)
    }

    fun deleteNote(recipeItem: Note?) {
        recipeItem ?: return
        refreshForecastUseCase.deleteNote(recipeItem)
    }

    fun handleErrorResponse(it: Throwable) {

    }

    private fun handleSuccessResponse(it: List<Note>) {
        screenState.value = it
    }

    private fun delay(delay: Long = 1000 , function: () -> Unit) {
        Handler().postDelayed(
            {function() }, delay
        )
    }


    override fun onCleared() {
        compositeDisposable.dispose()
        super.onCleared()
    }
}