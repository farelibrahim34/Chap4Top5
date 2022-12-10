package com.example.chap4top5

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.chap4top5.databinding.ActivityAddNoteBinding
import com.example.chap4top5.room.DataNote
import com.example.chap4top5.room.NoteDatabase
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async

class AddNoteActivity : AppCompatActivity() {

    lateinit var binding : ActivityAddNoteBinding
    var dbNote : NoteDatabase? = null




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_note)

        binding = ActivityAddNoteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        dbNote = NoteDatabase.getInstance(this)


        binding.btnSave.setOnClickListener {
            addNote()
        }

    }

    fun addNote(){

        GlobalScope.async {
            var title = binding.intputTitle.text.toString()
            var note = binding.inputContent.text.toString()

            dbNote!!.noteDao().insertNote(DataNote(0,title,note))

        }
        finish()

    }
}