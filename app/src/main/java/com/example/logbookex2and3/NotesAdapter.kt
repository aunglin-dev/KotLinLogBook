package com.example.logbookex2and3

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.view.menu.MenuView.ItemView
import androidx.recyclerview.widget.RecyclerView


class NotesAdapter(private var notes:List<Note>, context: Context):
    RecyclerView.Adapter<NotesAdapter.NoteViewHolder>() {

        private val database : NoteDatabaseHelper = NoteDatabaseHelper(context)

        class NoteViewHolder(itemView : View): RecyclerView.ViewHolder(itemView){

            val titleTextView : TextView = itemView.findViewById(R.id.titleTxtView)
            val updateBtn : ImageView = itemView.findViewById(R.id.updatBtn)
            val delBtn : ImageView = itemView.findViewById(R.id.DeleteBtn)
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        val createView = LayoutInflater.from(parent.context).inflate(R.layout.note_item,parent,false)
        return NoteViewHolder(createView)
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        val note = notes[position]
        holder.titleTextView.text=note.noteTitle

            holder.updateBtn.setOnClickListener{
                val intent = Intent(holder.itemView.context, UpdateActivity::class.java).apply {
                    putExtra("note_id",note.noteId)
                }
                holder.itemView.context.startActivity(intent)
            }
        holder.delBtn.setOnClickListener{
             database.delete(note.noteId)
            refreshData(database.GetAllNotes())
            Toast.makeText(holder.itemView.context,"Successfully Deleted",Toast.LENGTH_SHORT).show()
        }
    }

    override fun getItemCount(): Int =notes.size

    fun refreshData(newNotes: List<Note>){
        notes = newNotes
        notifyDataSetChanged()
    }

}
