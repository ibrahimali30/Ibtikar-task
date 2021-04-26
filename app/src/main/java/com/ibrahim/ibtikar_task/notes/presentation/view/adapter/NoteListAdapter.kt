package com.ibrahim.ibtikar_task.notes.presentation.view.adapter

import android.view.*
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import com.ibrahim.ibtikar_task.R
import com.ibrahim.ibtikar_task.utils.timeToFormattedStringAdapter
import com.ibrahim.ibtikar_task.notes.data.model.Note
import kotlinx.android.synthetic.main.layout_note_list_item.view.*
import java.lang.IndexOutOfBoundsException


class NoteListAdapter(
    private val onItemSelected: (adapterPosition: Int, note: Note) -> Unit
) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>()
{

    val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Note>() {

        override fun areItemsTheSame(oldItem: Note, newItem: Note): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Note, newItem: Note): Boolean {
            return oldItem == newItem
        }

    }
    private val differ = AsyncListDiffer(this, DIFF_CALLBACK)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        return NoteViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.layout_note_list_item,
                parent,
                false
            ),
            onItemSelected
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is NoteViewHolder -> {
                holder.bind(differ.currentList.get(position))
            }
        }
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    fun getNote(index: Int): Note? {
        return try{
            differ.currentList[index]
        }catch (e: IndexOutOfBoundsException){
            e.printStackTrace()
            null
        }
    }

    fun submitList(list: List<Note>) {
        val commitCallback = Runnable {

        }
        differ.submitList(list, commitCallback)
    }


    class NoteViewHolder(
        itemView: View,
        val onItemSelected: (adapterPosition: Int, note: Note) -> Unit
    ) : RecyclerView.ViewHolder(itemView) {


        fun bind(item: Note) = with(itemView) {
            setOnClickListener {
                onItemSelected(adapterPosition, item)
            }

            note_title.text = item.title
            note_body.text = item.body
            note_timestamp.text = timeToFormattedStringAdapter(item.time)

            }
    }
}















