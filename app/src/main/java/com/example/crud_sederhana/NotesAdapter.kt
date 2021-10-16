package com.example.crud_sederhana

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.crud_sederhana.model.DeleteResponse
import com.example.crud_sederhana.model.GetData
import com.example.crud_sederhana.network.ApiService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.ArrayList

class NotesAdapter(private val listNote: ArrayList<GetData>) :
    RecyclerView.Adapter<NotesAdapter.myViewHolder>() {
    class myViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val title: TextView = itemView.findViewById(R.id.notes_title)
        val body: TextView = itemView.findViewById(R.id.notes_Body)
        val update_at: TextView = itemView.findViewById(R.id.notes_updated_at)
        val notes: RelativeLayout = itemView.findViewById(R.id.notes)
        val id: TextView = itemView.findViewById(R.id.notes_id)
        val delete: ImageView = itemView.findViewById(R.id.image_delete)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): myViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_notes, parent, false)
        return myViewHolder(view)
    }

    override fun onBindViewHolder(holder: myViewHolder, position: Int) {
        val data = listNote[position]
        holder.title.text = data.title
        holder.body.text = data.body
        holder.update_at.text = data.updated_at
        holder.notes.setOnClickListener {
            val intent = Intent(holder.id.context, UpdateActivity::class.java)
            intent.putExtra("id", listNote[position].id)
            intent.putExtra("title", listNote[position].title)
            intent.putExtra("body", listNote[position].body)
            holder.notes.context.startActivity(intent)
        }
        holder.delete.setOnClickListener {
            ApiService.endpoint.deleteNotes(data.id).enqueue(object :Callback<DeleteResponse>{
                override fun onResponse(
                    call: Call<DeleteResponse>,
                    response: Response<DeleteResponse>
                ) {
                    if (response.isSuccessful){
//                        Toast.makeText(this, "Delete Data is Succes", Toast.LENGTH_SHORT).
                    }
                }

                override fun onFailure(call: Call<DeleteResponse>, t: Throwable) {
                    TODO("Not yet implemented")
                }

            })
        }
    }

    override fun getItemCount() = listNote.size

    fun setData(data: List<GetData>) {
        listNote.clear()
        listNote.addAll(data)
        notifyDataSetChanged()
    }
}