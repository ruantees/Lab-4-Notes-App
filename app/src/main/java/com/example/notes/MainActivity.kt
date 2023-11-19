package com.example.notes

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    lateinit var notes: ArrayList<Note>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setUpUI()
    }

    override fun onRestart() {
        super.onRestart()
        setContentView(R.layout.activity_main)
        setUpUI()
    }

    private fun setUpUI() {
        val rvContacts = findViewById<View>(R.id.rv_notes) as RecyclerView
        val dbHelper = DatabaseHelper.getInstance(this)

        notes = dbHelper.getAllNotes()

        val adapter = NotesAdapter(notes)
        rvContacts.adapter = adapter
        rvContacts.layoutManager = LinearLayoutManager(this)

        val newNoteOpener = findViewById<Button>(R.id.new_note)
        newNoteOpener.setOnClickListener {
            val intent = Intent(this, CreateNoteActivity::class.java)
            startActivity(intent)
        }

        val deleteNoteOpener = findViewById<Button>(R.id.delete_note)
        deleteNoteOpener.setOnClickListener {
            val intent = Intent(this, DeleteNotesActivity::class.java)
            startActivity(intent)
        }
    }
}