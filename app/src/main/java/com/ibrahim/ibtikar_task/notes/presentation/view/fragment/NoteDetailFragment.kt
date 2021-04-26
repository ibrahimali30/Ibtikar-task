package com.ibrahim.ibtikar_task.notes.presentation.view.fragment


import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.ibrahim.ibtikar_task.R
import com.ibrahim.ibtikar_task.notes.data.model.Note
import com.ibrahim.ibtikar_task.notes.presentation.viewmodel.NotesViewModel
import kotlinx.android.synthetic.main.fragment_note_detail.*
import javax.inject.Inject

class NoteDetailFragment
constructor(
    private val note: Note?
): Fragment(R.layout.fragment_note_detail) {
    @Inject
    lateinit var notesViewModel: NotesViewModel
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        bindNoteDetails()
    }

    private fun bindNoteDetails() {
        note ?: return
        note.apply {
            note_title.setText(title)
            note_body.setText(body)
        }
    }
}