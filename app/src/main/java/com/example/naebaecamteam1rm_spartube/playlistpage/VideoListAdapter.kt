package com.example.naebaecamteam1rm_spartube.playlistpage

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.naebaecamteam1rm_spartube.R

class VideoListAdapter(private val context: Context, private val videoItems: ArrayList<VideoListModel>) :
    RecyclerView.Adapter<VideoListAdapter.VideoViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VideoViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.fragment_playlist, parent, false)
        return VideoViewHolder(view)
    }

    override fun onBindViewHolder(holder: VideoViewHolder, position: Int) {
        val item = videoItems[position]
        holder.titleTextView.text = item.title
        holder.durationTextView.text = item.duration
    }

    override fun getItemCount(): Int {
        return videoItems.size
    }

    inner class VideoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val titleTextView: TextView = itemView.findViewById(R.id.)
        val durationTextView: TextView = itemView.findViewById(R.id.)
    }
}
