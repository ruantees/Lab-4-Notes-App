package com.example.notes

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import java.util.Date

class DatabaseHelper(context: Context) : SQLiteOpenHelper(context, "Notes.db", null, 1) {

    companion object {
        private lateinit var instance: DatabaseHelper
        fun getInstance(context: Context): DatabaseHelper {
            instance = DatabaseHelper(context)
            return instance
        }
    }

    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL("CREATE TABLE Notes(title TEXT PRIMARY KEY, note TEXT)")
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL("DROP TABLE IF EXISTS Notes")
        onCreate(db)
    }

    fun insertNote(title: String, note: String): Boolean {
        val db = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put("title", title)
        contentValues.put("note", note)
        val result = db.insert("Notes", null, contentValues)
        db.close()
        return result != -1L
    }

    @SuppressLint("Range")
    fun getAllNotes(): ArrayList<Note> {
        val noteList = ArrayList<Note>()

        val db = this.readableDatabase
        val cursor = db.rawQuery("SELECT * FROM Notes", null)

        if (cursor.moveToFirst()) {
            do {
                val id = cursor.getString(cursor.getColumnIndex("title"))
                val title = cursor.getString(cursor.getColumnIndex("title"))
                val note = cursor.getString(cursor.getColumnIndex("note"))
                val creationDate = Date()

                noteList.add(Note(id, title, note, creationDate))
            } while (cursor.moveToNext())
        }

        cursor.close()
        db.close()

        return noteList
    }
}