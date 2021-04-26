package com.ibrahim.ibtikar_task.notes.presentation.view.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.transition.Slide
import com.ibrahim.ibtikar_task.utils.NoteItemTouchHelperCallback
import com.ibrahim.ibtikar_task.R
import com.ibrahim.ibtikar_task.utils.AppAlarmManager
import com.ibrahim.ibtikar_task.notes.data.model.Note
import com.ibrahim.ibtikar_task.notes.presentation.view.adapter.NoteListAdapter
import com.ibrahim.ibtikar_task.notes.presentation.view.fragment.NoteDetailFragment
import com.ibrahim.ibtikar_task.notes.presentation.viewmodel.NotesViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    lateinit var listAdapter: NoteListAdapter
    lateinit var itemTouchHelper: ItemTouchHelper

    @Inject
    lateinit var notesViewModel: NotesViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        notesViewModel.getAndObserveNotes()
        setupRecyclerView()
        initObservers()
        initViews()
    }

    private fun initViews() {
        add_new_note_fab.setOnClickListener {
            navigateToAddEditNote()
        }
    }

    private fun initObservers() {
        notesViewModel.notesListLiveData.observe(this , Observer {
            listAdapter.submitList(it)
        })

    }

    private fun setupRecyclerView(){
        recycler_view.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            itemTouchHelper = ItemTouchHelper(
                    NoteItemTouchHelperCallback { position ->
                        removeNote(listAdapter.getNote(position))
                    }
            )
            itemTouchHelper.attachToRecyclerView(this)
            listAdapter = NoteListAdapter(::onNoteSelected)
            adapter = listAdapter
        }
    }

    private fun removeNote(note: Note?) {
        notesViewModel.deleteNote(note)
    }

    private fun onNoteSelected(i: Int, note: Note) {
        navigateToAddEditNote(note)
    }

    private fun navigateToAddEditNote(note: Note? = null) {
        val noteDetailsFragment = NoteDetailFragment(note , notesViewModel)
        noteDetailsFragment.enterTransition = Slide(Gravity.END)

        supportFragmentManager.beginTransaction()
            .replace(android.R.id.content, noteDetailsFragment)
            .addToBackStack(noteDetailsFragment::class.java.name)
            .commit()
    }

}