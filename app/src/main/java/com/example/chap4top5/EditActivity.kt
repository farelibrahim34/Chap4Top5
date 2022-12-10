package com.example.chap4top5

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.chap4top5.databinding.ActivityEditBinding
import com.example.chap4top5.databinding.ActivityMainBinding
import com.example.chap4top5.room.DataNote
import com.example.chap4top5.room.NoteDatabase
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async

class EditActivity : AppCompatActivity() {
    lateinit var binding : ActivityEditBinding
    var NoteDB : NoteDatabase? = null
    lateinit var adapterNote : NoteAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit)
        binding = ActivityEditBinding.inflate(layoutInflater)
        setContentView(binding.root)

        NoteDB = NoteDatabase.getInstance(this)

        //        ambil data yg dikirim dari adapter
        var getDataNote = intent.getSerializableExtra("dataedit") as DataNote
//        set data yang dikirim ke dalam editText
        binding.inputEditeJudul.setText(getDataNote.title)
        binding.inputEditIsi.setText(getDataNote.content)

        binding.btnEditNote.setOnClickListener {
            editNote()

        }

    }
    fun editNote(){
        var idNote = binding.textId.text.toString().toInt()
        var title = binding.inputEditeJudul.text.toString()
        var note = binding.inputEditIsi.text.toString()
        GlobalScope.async {
            NoteDB?.noteDao()?.updateNote(DataNote(idNote,title,note))
            runOnUiThread {
                Toast.makeText(this@EditActivity, "Dat berhasil di Edit", Toast.LENGTH_LONG)
                    .show()
            }
            finish()
        }
    }
}