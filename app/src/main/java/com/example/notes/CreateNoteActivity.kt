package com.example.notes

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class CreateNoteActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_note)

        val titleInput = findViewById<EditText>(R.id.title_input)
        val noteTextInput = findViewById<EditText>(R.id.note_text_input)
        val saveNoteButton = findViewById<Button>(R.id.save_note)

        saveNoteButton.setOnClickListener {
            val titleInputString = titleInput.text.toString()
            val noteTextInputString = noteTextInput.text.toString()
            val currentTime = System.currentTimeMillis().toString()
            Log.d("time", currentTime)

            if (titleInputString.isEmpty()){
                Toast.makeText(this, "Empty Title", Toast.LENGTH_SHORT).show()
            }
            else if (noteTextInputString.isEmpty()){
                Toast.makeText(this, "Empty Note", Toast.LENGTH_SHORT).show()
            }

            val dbHelper = DatabaseHelper.getInstance(this)
            val isNoteSaved = dbHelper.insertNote(titleInputString, noteTextInputString)

            if (isNoteSaved) {
                Toast.makeText(this, "Note saved successfully!", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Error while saving note!", Toast.LENGTH_SHORT).show()
            }

            finish()
        }
    }
}