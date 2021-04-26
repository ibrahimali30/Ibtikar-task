package com.ibrahim.ibtikar_task.notes.presentation.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ibrahim.ibtikar_task.notes.data.model.Note
import com.ibrahim.ibtikar_task.notes.domain.interactor.GetNotesUseCase
import com.ibrahim.ibtikar_task.utils.AppAlarmManager
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class NotesViewModel @Inject constructor(
        private val refreshForecastUseCase: GetNotesUseCase,
        private val appAlarmManager: AppAlarmManager,
): ViewModel() {

    private val compositeDisposable = CompositeDisposable()
    val notesListLiveData by lazy { MutableLiveData<List<Note>>() }

    fun getAndObserveNotes() {
        refreshForecastUseCase.getAndObserveNotes()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe {
                    handleSuccessResponse(it)
                }.addTo(compositeDisposable)
    }

    private fun handleSuccessResponse(it: List<Note>) {
        notesListLiveData.value = it
    }

    fun saveNote(note: Note?, oldNote: Note?) {
        note ?: return
        refreshForecastUseCase.insertNote(note)
        //set alarm
        appAlarmManager.apply {
            cancelAlarm(oldNote)
            setAlarm(note)
        }
    }

    fun deleteNote(note: Note?) {
        note ?: return
        refreshForecastUseCase.deleteNote(note)
        //cancel alarm on deletion
        appAlarmManager.cancelAlarm(note)

    }

    override fun onCleared() {
        compositeDisposable.dispose()
        super.onCleared()
    }
}