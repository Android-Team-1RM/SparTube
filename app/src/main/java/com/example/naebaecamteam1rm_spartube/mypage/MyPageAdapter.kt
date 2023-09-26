package com.example.naebaecamteam1rm_spartube.mypage

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.naebaecamteam1rm_spartube.R
import com.example.naebaecamteam1rm_spartube.databinding.ItemMyPageRecyclerviewBinding
import com.example.naebaecamteam1rm_spartube.databinding.ItemRecyclerviewBinding

class MyPageAdapter(context : Context,
                    private val onClickItem: (MyPageModel) -> Unit
): ListAdapter<MyPageModel,MyPageAdapter.ViewHolder>(
    object:DiffUtil.ItemCallback<MyPageModel>(){
        override fun areContentsTheSame(oldItem: MyPageModel, newItem: MyPageModel): Boolean {
            return oldItem.thumbnail == newItem.thumbnail
        }

        override fun areItemsTheSame(oldItem: MyPageModel, newItem: MyPageModel): Boolean {
            return oldItem == newItem
        }
    }) {
    private var mContext = context

    interface ItemClick {

        fun onClick(view : View, tubeData : MyPageModel)

    }

    var itemClick: ItemClick? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(ItemMyPageRecyclerviewBinding.inflate(LayoutInflater.from(parent.context), parent, false),
        onClickItem)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }
    inner class ViewHolder(
        private val binding: ItemMyPageRecyclerviewBinding,
        private val onClickItem: (MyPageModel) -> Unit
    ):RecyclerView.ViewHolder(binding.root){

        fun bind(item:MyPageModel) = with(binding){
            container.setOnClickListener{
                onClickItem(
                    item
                )
            }
            Glide.with(mContext)
                .load(item.thumbnail)
                .error(R.drawable.video_detail_page_img_base)
                .into(ivThumbnails)
            tvTitle.text = item.title
        }

    }
}