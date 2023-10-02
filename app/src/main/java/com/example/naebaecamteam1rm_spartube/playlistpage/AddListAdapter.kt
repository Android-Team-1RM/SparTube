package com.example.naebaecamteam1rm_spartube.playlistpage

import android.app.Person
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.naebaecamteam1rm_spartube.R

class AddListAdapter : RecyclerView.Adapter<PlayListAdapter.ViewHolder>() {
    private val items = ArrayList<PlayListModel>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val itemView = inflater.inflate(R.layout.fragment_playlist, parent, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items[position]
        holder.setItem(item)
    }

    fun addItem(item: Person) {
        items.add(item)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val title: TextView = itemView.findViewById(R.id.)
        private val thumbnail: TextView = itemView.findViewById(R.id.)
        private val description: TextView = itemView.findViewById(R.id.)

        fun setItem(item: Person) {
            title.text = item.title
            thumbnail.text = item.thumbnail
            description.text = item.description
        }
    }
}