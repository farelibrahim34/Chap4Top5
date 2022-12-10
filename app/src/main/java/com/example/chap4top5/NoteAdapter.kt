package com.example.chap4top5

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.chap4top5.databinding.ItemNoteBinding
import com.example.chap4top5.room.DataNote
import com.example.chap4top5.room.NoteDatabase
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async

class NoteAdapter(var listNote : List<DataNote>): RecyclerView.Adapter<NoteAdapter.ViewHolder>() {

    var DBNote : NoteDatabase? = null

    class ViewHolder(var binding : ItemNoteBinding):RecyclerView.ViewHolder(binding.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var view = ItemNoteBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.txtId.text = listNote[position].id.toString()
        holder.binding.txtJudul.text = listNote[position].title


        holder.binding.btnDelete.setOnClickListener {

            DBNote = NoteDatabase.getInstance(it.context)

            GlobalScope.async {
                val del = DBNote?.noteDao()?.deleteNote(listNote[position])
                (holder.itemView.context as MainActivity).getAllNote()
//                        kalau ga pake ini pas hapus bisa, data ga ke update langsung tapi, ga ada note berhasil atau ga
                (holder.itemView.context as MainActivity).runOnUiThread {
                    if (del != 0) {
                        Toast.makeText(
                            it.context,
                            "Data ${listNote[position].id} Success Deleted",
                            Toast.LENGTH_SHORT
                        ).show()
//                                ga pake ini data ga ke update
                        (holder.itemView.context as MainActivity).getAllNote()
                    } else {
                        Toast.makeText(
                            it.context,
                            "Data ${listNote[position].id} Failed to Delete ",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
            }


        }

        holder.binding.btnEdit.setOnClickListener {
            var move = Intent(it.context, EditActivity::class.java)
            move.putExtra("dataedit", listNote[position])
            it.context.startActivity(move)
        }
    }

    override fun getItemCount(): Int {
        return listNote.size
    }
}