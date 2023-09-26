package com.example.naebaecamteam1rm_spartube.mypage

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.naebaecamteam1rm_spartube.R
import com.example.naebaecamteam1rm_spartube.databinding.ItemRecyclerviewBinding

class MyPageAdapter: ListAdapter<MyPageModel,MyPageAdapter.ViewHolder>(
    object:DiffUtil.ItemCallback<MyPageModel>(){
        override fun areContentsTheSame(oldItem: MyPageModel, newItem: MyPageModel): Boolean {
            return oldItem.thumbnails == newItem.thumbnails
        }

        override fun areItemsTheSame(oldItem: MyPageModel, newItem: MyPageModel): Boolean {
            return oldItem == newItem
        }
    }) {



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(ItemRecyclerviewBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }
    inner class ViewHolder(
        private val binding:ItemRecyclerviewBinding
    ):RecyclerView.ViewHolder(binding.root){
        fun bind(item:MyPageModel) = with(binding){
            ivThumbnails.setImageResource(R.drawable.mypage_img_profile)
            tvTitle.text = item.title
        }

    }
}