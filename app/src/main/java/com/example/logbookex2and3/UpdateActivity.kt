package com.example.logbookex2and3

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.logbookex2and3.databinding.ActivityUpdateBinding

class UpdateActivity : AppCompatActivity() {

    private lateinit var bind : ActivityUpdateBinding
    private  lateinit var database : NoteDatabaseHelper
    private   var noteId: Int = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bind = ActivityUpdateBinding.inflate(layoutInflater)
        setContentView(bind.root)

        database = NoteDatabaseHelper(this)

            noteId = intent.getIntExtra("note_id",-1)
        if(noteId == -1){
             finish()
            return
        }

     val updatedNote = database.getNoteById(noteId)
        bind.updatetitleTxt.setText(updatedNote.noteTitle)

        bind.updatesaveBtn.setOnClickListener{
            val  updatedTitle= bind.updatetitleTxt.text.toString()
            val updatedNote = Note(noteId,updatedTitle)
            database.updateNote(updatedNote)
            finish()
            Toast.makeText(this,"Saved Updates",Toast.LENGTH_SHORT).show()
        }

    }
}