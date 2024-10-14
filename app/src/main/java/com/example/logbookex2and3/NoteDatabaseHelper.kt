package com.example.logbookex2and3

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class NoteDatabaseHelper(context:Context):SQLiteOpenHelper(context,DB_NAME,null, DB_Version) {

    companion object{
         private  const val  DB_NAME = "logbookthree.db"
        private  const val  DB_Version= 1
        private  const val  Table_Name= "Notes"
        private  const val  Column_ID= "noteId"
        private  const val  Column_Title= "noteTitle"
    }

    override fun onCreate(db: SQLiteDatabase?) {
        val createQuery = "CREATE TABLE $Table_Name ($Column_ID INTEGER PRIMARY KEY, $Column_Title TEXT)"
        db?.execSQL(createQuery)


    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        val dropQuery = "DROP TABLE IF EXISTS $Table_Name"
        db?.execSQL(dropQuery)
        onCreate(db)

    }

    fun Insert(note: Note){
        val db = writableDatabase
        val values = ContentValues().apply {
            put(Column_Title,note.noteTitle)
        }
        db.insert(Table_Name,null,values)
        db.close()
    }

    fun GetAllNotes(): List<Note>{
        val list = mutableListOf<Note>()
        val database = readableDatabase
        val selectQuery = "SELECT * FROM $Table_Name"
        val cursor = database.rawQuery(selectQuery,null)

        while (cursor.moveToNext()){
            val noteId = cursor.getInt(cursor.getColumnIndexOrThrow(Column_ID))
            val noteTitle = cursor.getString(cursor.getColumnIndexOrThrow(Column_Title))

            val result = Note(noteId,noteTitle)
            list.add(result)

        }
        cursor.close()
        database.close()
        return list

    }

    fun updateNote(note: Note){
        val database = writableDatabase
        val results = ContentValues().apply{
            put(Column_Title,note.noteTitle)
        }
        val condition = "$Column_ID = ?"
        val conditionBindValue = arrayOf(note.noteId.toString())
        database.update(Table_Name,results,condition,conditionBindValue)
        database.close()
    }

    fun getNoteById(noteId :Int):Note{
        val database = readableDatabase
        val selectQuery = "SELECT * FROM $Table_Name WHERE $Column_ID=$noteId"
        val cursor = database.rawQuery(selectQuery,null)
        cursor.moveToFirst()

        val Id = cursor.getInt(cursor.getColumnIndexOrThrow(Column_ID))
        val noteTitle = cursor.getString(cursor.getColumnIndexOrThrow(Column_Title))

        cursor.close()
        database.close()
        return Note(Id,noteTitle)
    }

    fun delete(noteId: Int){
         val database = writableDatabase
        val condition = "$Column_ID = ?"
        val conditionBindValue = arrayOf(noteId.toString())
        database.delete(Table_Name,condition,conditionBindValue)
        database.close()
    }

}