package com.ibrahim.ibtikar_task.notes.presentation.view.fragment


import android.app.Dialog
import android.app.TimePickerDialog
import android.os.Bundle
import android.text.format.DateFormat.is24HourFormat
import android.util.Log
import android.view.View
import android.widget.TimePicker
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import com.ibrahim.ibtikar_task.R
import com.ibrahim.ibtikar_task.notes.data.model.Note
import com.ibrahim.ibtikar_task.notes.presentation.viewmodel.NotesViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_note_detail.*
import java.text.DateFormat
import java.util.*
import javax.inject.Inject

@AndroidEntryPoint
class NoteDetailFragment
constructor(
    private val note: Note?,
    private val notesViewModel: NotesViewModel
): Fragment(R.layout.fragment_note_detail) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        bindNoteDetails()
        initViews()
    }

    private fun initViews() {
        handleSaveButtonStatus()
        note_title.doOnTextChanged {  _, _, _, _ -> handleSaveButtonStatus() }
        note_body.doOnTextChanged { _, _, _, _ -> handleSaveButtonStatus() }


        container_due_date.setOnClickListener {
            showDateSelector()
        }

        btSaveNote.setOnClickListener {
            saveNoteAndClose()
        }
    }

    private fun showDateSelector() {
        TimePickerFragment().show(activity!!.supportFragmentManager, "timePicker")
    }

    private fun saveNoteAndClose() {
        notesViewModel.insertNote(
            Note(
                note_title.text.toString(),
                note_body.text.toString(),
                tv_date.text.toString(),
                note?.id?:0
            )
        )
        activity?.onBackPressed()
    }

    private fun handleSaveButtonStatus() {
        btSaveNote.isEnabled =
            note_title.text.isNotEmpty() &&
                    note_title.text.isNotEmpty() &&
                    tv_date.text.isNotEmpty() &&
                    tv_date.text.toString() != "Due Date..."
    }

    private fun bindNoteDetails() {
        note ?: return
        note.apply {
            note_title.setText(title)
            note_body.setText(body)
        }
    }
}


class TimePickerFragment : DialogFragment(), TimePickerDialog.OnTimeSetListener {


    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val c = Calendar.getInstance()
        val hour = c.get(Calendar.HOUR_OF_DAY)
        val minute = c.get(Calendar.MINUTE)

        // Create a new instance of TimePickerDialog and return it
        return TimePickerDialog(activity, this, hour, minute, false)    }


    override fun onTimeSet(view: TimePicker, hourOfDay: Int, minute: Int) {
        // Do something with the time chosen by the user
    }
}