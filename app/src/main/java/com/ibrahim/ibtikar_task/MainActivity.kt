package com.ibrahim.ibtikar_task

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import com.ibrahim.ibtikar_task.notes.data.model.Note
import com.ibrahim.ibtikar_task.notes.presentation.view.adapter.NoteListAdapter
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    lateinit var listAdapter: NoteListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setupRecyclerView()
    }



    private var itemTouchHelper: ItemTouchHelper? = null

    private fun setupRecyclerView(){
        recycler_view.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
//            val topSpacingDecorator = TopSpacingItemDecoration(20)
//            addItemDecoration(topSpacingDecorator)
            itemTouchHelper = ItemTouchHelper(
                NoteItemTouchHelperCallback({
                    it
                })
            )
            itemTouchHelper?.attachToRecyclerView(this)

            listAdapter = NoteListAdapter(::onItemSelected)

            adapter = listAdapter
        }

        listAdapter.submitList(listOf(
            Note("123","123sfdsf","sdfds","12/123/123","12/123/123"),
            Note("123","123sfdsf","sdfds","12/123/123","12/123/123"),
            Note("123","123sfdsf","sdfds","12/123/123","12/123/123"),
            Note("123","123sfdsf","sdfds","12/123/123","12/123/123"),
            Note("123","123sfdsf","sdfds","12/123/123","12/123/123"),
            Note("123","123sfdsf","sdfds","12/123/123","12/123/123")
        ))
    }

    private fun onItemSelected(i: Int, note: Note) {
        
    }

}