package com.example.naebaecamteam1rm_spartube.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.naebaecamteam1rm_spartube.data.TestData
import com.example.naebaecamteam1rm_spartube.databinding.ItemRecyclerviewBinding

class HomeAdapter(val hItems: MutableList<TestData>) : RecyclerView.Adapter<HomeAdapter.Holder>() {

    interface ItemClick {
        fun onClick(view : View, position : Int)
    }

    var itemClick : ItemClick? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val binding = ItemRecyclerviewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return Holder(binding)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.itemView.setOnClickListener {  //클릭이벤트추가부분
            itemClick?.onClick(it, position)
        }
        holder.preview.setImageResource(hItems[position].preview)
        holder.title.text = hItems[position].title
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getItemCount(): Int {
        return hItems.size
    }

    inner class Holder(val binding: ItemRecyclerviewBinding) : RecyclerView.ViewHolder(binding.root) {
        val preview = binding.ivPreview
        val title = binding.tvTitle
    }
}