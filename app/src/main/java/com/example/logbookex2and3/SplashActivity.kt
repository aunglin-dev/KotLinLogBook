package com.example.logbookex2and3

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.logbookex2and3.databinding.ActivitySplashBinding

class SplashActivity : AppCompatActivity() {

    private  lateinit var bind: ActivitySplashBinding
    private  lateinit var db : NoteDatabaseHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bind = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(bind.root)

        db = NoteDatabaseHelper(this)

        bind.saveBtn.setOnClickListener{
            val noteTitle = bind.titleTxt.text.toString();
            val note = Note(0,noteTitle)
            db.Insert(note)
            finish()
            Toast.makeText(this, "Note Saved", Toast.LENGTH_SHORT).show()
        }

    }
}