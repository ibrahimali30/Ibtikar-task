package com.ibrahim.ibtikar_task.notes.presentation.view.fragment


import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.ibrahim.ibtikar_task.R
import com.ibrahim.ibtikar_task.utils.hideKeyboard
import com.ibrahim.ibtikar_task.utils.timeToFormattedString
import com.ibrahim.ibtikar_task.notes.data.model.Note
import com.ibrahim.ibtikar_task.notes.presentation.view.helper.TimePickerHelper
import com.ibrahim.ibtikar_task.notes.presentation.viewmodel.NotesViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_note_detail.*


@AndroidEntryPoint
class NoteDetailFragment
constructor(
    private val note: Note?,
    private val notesViewModel: NotesViewModel
): Fragment(R.layout.fragment_note_detail) {

    val dateAndTimePicker by lazy {
        TimePickerHelper(activity , ::onTimePicked)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        bindNoteDetails()
        initViews()
    }

    private fun initViews() {

        container_due_date.setOnClickListener {
            showDateSelector()
        }

        btSaveNote.setOnClickListener {
            saveNote()
        }
    }

    private fun saveNote() {
        if (isAllNoteDataFilled()) {
            saveNoteAndClose()
        } else {
            Toast.makeText(requireContext(), "Please fill all data", Toast.LENGTH_SHORT).show()
        }
    }

    private fun onTimePicked(timeLong: Long) {
        tv_date.text = timeToFormattedString(timeLong)
    }

    private fun showDateSelector() {
        dateAndTimePicker.show()
    }

    private fun saveNoteAndClose() {
        val note = Note(
                note_title_fragment.text.toString(),
                note_body_fragment.text.toString(),
                tv_date.text.toString(),
                dateAndTimePicker.calendar.time.time,
                note?.id ?: 0
        )

        notesViewModel.saveNote(note, this@NoteDetailFragment.note)

        requireActivity().hideKeyboard()
        activity?.onBackPressed()
    }

    private fun isAllNoteDataFilled(): Boolean {
        return note_title_fragment.text.isNotEmpty() &&
                note_title_fragment.text.isNotEmpty() &&
                tv_date.text.isNotEmpty() &&
                tv_date.text.toString() != "Select time"
    }

    private fun bindNoteDetails() {
        note ?: return
        note.apply {
            note_title_fragment.setText(title)
            note_body_fragment.setText(body)
            tv_date.text = timeToFormattedString(note.time)
        }
    }
}


