package com.example.notes

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class DeleteNotesActivity : AppCompatActivity() {
    lateinit var notes: ArrayList<Note>

    private fun getCheckedItems(adapter: NotesAdapter): List<Note> {
        val checkedItems = mutableListOf<Note>()
        for (i in 0 until adapter.itemCount) {
            val rvNotesDelete = findViewById<RecyclerView>(R.id.rv_notes_delete)
            val viewHolder = rvNotesDelete.findViewHolderForAdapterPosition(i) as NotesAdapter.ViewHolder
            if (viewHolder.isChecked()) {
                checkedItems.add(adapter.myNotes[i])
            }
        }
        return checkedItems
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_delete_notes)

        val rvContacts = findViewById<View>(R.id.rv_notes_delete) as RecyclerView
        val dbHelper = DatabaseHelper.getInstance(this)
        notes = dbHelper.getAllNotes()

        val adapter = NotesAdapter(notes)
        rvContacts.adapter = adapter
        rvContacts.layoutManager = LinearLayoutManager(this)

        val deleteNoteButton = findViewById<Button>(R.id.delete_note)
        deleteNoteButton.setOnClickListener {
            val notesToDelete = getCheckedItems(NotesAdapter(notes))
            for (i in notesToDelete) {
                dbHelper.deleteNote(i.title)
            }

            finish()
        }
    }
}