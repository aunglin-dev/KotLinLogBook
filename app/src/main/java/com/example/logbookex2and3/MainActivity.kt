package com.example.logbookex2and3

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.logbookex2and3.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {

    private lateinit var bind: ActivityMainBinding
    private lateinit var database: NoteDatabaseHelper
    private lateinit var Nadapter : NotesAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bind = ActivityMainBinding.inflate(layoutInflater)
        setContentView(bind.root)

        database = NoteDatabaseHelper(this)
        Nadapter = NotesAdapter(database.GetAllNotes(),this)


        bind.notesRecycler.layoutManager = LinearLayoutManager(this)
        bind.notesRecycler.adapter = Nadapter

        bind.addBtn.setOnClickListener(){
            val intent = Intent(this, SplashActivity::class.java)
            startActivity(intent)
        }

    }
    override fun onResume(){
        super.onResume()
        Nadapter.refreshData(database.GetAllNotes())
    }
}