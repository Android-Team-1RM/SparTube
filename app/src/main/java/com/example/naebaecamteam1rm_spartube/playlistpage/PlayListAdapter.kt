package com.example.naebaecamteam1rm_spartube.playlistpage

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.naebaecamteam1rm_spartube.data.TubeDataModel
import com.example.naebaecamteam1rm_spartube.databinding.ItemRecyclerviewBinding
import com.example.naebaecamteam1rm_spartube.homepage.HomeAdapter
import com.example.naebaecamteam1rm_spartube.searchpage.SearchAdapter

class PlayListAdapter(context: Context) : RecyclerView.Adapter<PlayListAdapter.Holder>() {

    var list = ArrayList<TubeDataModel>()

    var mContext = context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlaylistAdapter.Holder {
        return HomeAdapter.Holder(
            ItemRecyclerviewBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    interface ItemClick {
        fun onClick(view : View, tubeData : TubeDataModel)
    }

    override fun onBindViewHolder(holder: HomeAdapter.Holder, position: Int) {
        var currentItem = items[position]
        holder.bind(currentItem)
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getItemCount(): Int {
        return list.size
    }

    inner class Holder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    }

}