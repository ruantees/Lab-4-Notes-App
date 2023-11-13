package com.example.notes

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class Note(val title: String, val note: String) {
    companion object {
        private var lastNoteId = 0
        fun createNotesList(numNotes: Int): ArrayList<Note> {
            val notes = ArrayList<Note>()
            for (i in 1..numNotes) {
                notes.add(Note("title" + ++lastNoteId, "note$lastNoteId"))
            }
            return notes
        }
    }
}

class NotesAdapter(private val myNotes: List<Note>): RecyclerView.Adapter<NotesAdapter.ViewHolder>() {
    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val titleTextView = itemView.findViewById<TextView>(R.id.title)
        val noteTextView = itemView.findViewById<TextView>(R.id.note)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotesAdapter.ViewHolder {
        val context = parent.context
        val inflater = LayoutInflater.from(context)
        val noteView = inflater.inflate(R.layout.note_row, parent, false)
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