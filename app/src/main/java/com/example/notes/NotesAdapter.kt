package com.example.notes

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class NotesAdapter(private val context: Context, val myNotes: List<Note>): RecyclerView.Adapter<NotesAdapter.ViewHolder>() {
    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val titleTextView: TextView = itemView.findViewById(R.id.title)
        val noteTextView: TextView = itemView.findViewById(R.id.note)
        val checkBox: CheckBox? = itemView.findViewById(R.id.my_checkbox)

        fun isChecked(): Boolean {
            return checkBox?.isChecked == true
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotesAdapter.ViewHolder {
        val context = parent.context
        val inflater = LayoutInflater.from(context)

        val layoutId = when (context) {
            is MainActivity -> R.layout.note_row
            is DeleteNotesActivity -> R.layout.delete_note_row
            else -> throw IllegalArgumentException("Invalid context")
        }

        val noteView = inflater.inflate(layoutId, parent, false)
        return ViewHolder(noteView)
    }

    override fun onBindViewHolder(viewHolder: NotesAdapter.ViewHolder, position: Int) {
        // Get the data model based on position
        val note: Note = myNotes[position]
        // Set item views based on your views and data model
        val titleTextView = viewHolder.titleTextView
        titleTextView.text = note.title
        val noteTextView = viewHolder.noteTextView
        noteTextView.text = note.note
    }

    // Returns the total count of items in the list
    override fun getItemCount(): Int {
        return myNotes.size
    }
}