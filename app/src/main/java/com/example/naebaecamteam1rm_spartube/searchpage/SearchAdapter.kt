package com.example.naebaecamteam1rm_spartube.searchpage

import android.content.Context
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.naebaecamteam1rm_spartube.data.TubeDataModel
import com.example.naebaecamteam1rm_spartube.databinding.ItemRecyclerviewBinding

class SearchAdapter(private val context: Context) : RecyclerView.Adapter<SearchAdapter.Holder>() {
    var items = ArrayList<TubeDataModel>()
    var mContext = context


    fun clearItem() {
        items.clear()
        notifyDataSetChanged()
    }

    interface ItemClick {
        fun onClick(view : View, tubeData : TubeDataModel)
    }

    var itemClick : ItemClick? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        return Holder(
            ItemRecyclerviewBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        var currentItem = items[position]
        holder.bind(currentItem)
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getItemCount() = items.size

    inner class Holder(val binding: ItemRecyclerviewBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: TubeDataModel) = with(binding){ //클릭이벤트추가부분
            itemView.setOnClickListener{
                itemClick?.onClick(it, item)
            }
            Glide.with(mContext)
                .load(Uri.parse(item.thumbnail))
                .into(ivThumbnails)
            tvTitle.text = item.title
        }
    }
}