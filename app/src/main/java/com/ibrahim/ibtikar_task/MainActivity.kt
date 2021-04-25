package com.ibrahim.ibtikar_task

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import com.ibrahim.ibtikar_task.notes.data.model.Note
import com.ibrahim.ibtikar_task.notes.presentation.view.adapter.NoteListAdapter
import com.ibrahim.ibtikar_task.notes.presentation.viewmodel.NotesViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    lateinit var listAdapter: NoteListAdapter

    @Inject
    lateinit var notesViewModel: NotesViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setupRecyclerView()
        notesViewModel.getFavouriteNotes()
        initObserverse()
    }

    private fun initObserverse() {
        notesViewModel.screenState.observe(this , Observer {
            listAdapter.submitList(it)
        })

        listOf(
            Note("1","123sfdsf","sdfds","12/123/123","12/123/123"),
            Note("2","123sfdsf","sdfds","12/123/123","12/123/123"),
            Note("3","123sfdsf","sdfds","12/123/123","12/123/123"),
            Note("4","123sfdsf","sdfds","12/123/123","12/123/123"),
            Note("5","123sfdsf","sdfds","12/123/123","12/123/123"),
            Note("6","123sfdsf","sdfds","12/123/123","12/123/123")
        ).forEach {
            notesViewModel.insertNote(it)
        }
    }


    private var itemTouchHelper: ItemTouchHelper? = null

    private fun setupRecyclerView(){
        recycler_view.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
//            val topSpacingDecorator = TopSpacingItemDecoration(20)
//            addItemDecoration(topSpacingDecorator)
            itemTouchHelper = ItemTouchHelper(
                NoteItemTouchHelperCallback { position ->
                    notesViewModel.deleteNote(listAdapter.getNote(position))
                }
            )
            itemTouchHelper?.attachToRecyclerView(this)

            listAdapter = NoteListAdapter(::onItemSelected)

            adapter = listAdapter
        }


    }

    private fun onItemSelected(i: Int, note: Note) {

    }

}