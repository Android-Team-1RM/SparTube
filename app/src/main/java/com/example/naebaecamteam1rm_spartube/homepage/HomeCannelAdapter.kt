package com.example.naebaecamteam1rm_spartube.homepage

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

import com.example.naebaecamteam1rm_spartube.data.TubeDataModel
import com.example.naebaecamteam1rm_spartube.databinding.ItemLoadingVerticalBinding
import com.example.naebaecamteam1rm_spartube.databinding.ItemRecyclerviewBinding

class HomeCannelAdapter(context: Context) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    var list = ArrayList<TubeDataModel>()
    private val VIEW_TYPE_ITEM = 0
    private val VIEW_TYPE_LOADING = 1

    var mContext = context

    interface ItemClick {

        fun onClick(view: View, tubeData: TubeDataModel)

    }

    var itemClick: ItemClick? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            VIEW_TYPE_ITEM -> {
                return Holder(
                    ItemRecyclerviewBinding.inflate(
                        LayoutInflater.from(parent.context), parent, false
                    )
                )
            }

            else -> {
                return LoadingViewHolder(
                    ItemLoadingVerticalBinding.inflate(
                        LayoutInflater.from(parent.context), parent, false
                    )
                )
            }
        }
//        return Holder(
//            ItemRecyclerviewBinding.inflate(
//                LayoutInflater.from(parent.context), parent, false
//            )
//        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        var item = list[position]
        if (holder is Holder) {
            holder.bind(item)
        } else {

        }

        //20번째 가되면 추가로 아이템을 넣는 형식을 설정

//        holder.itemView.setOnClickListener {  //클릭이벤트추가부분
//            itemClick?.onClick(it, position)
//        }
//        holder.thumbnail.setImageResource(list[position].url)
//        holder.title.text = list[position].title
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun getItemViewType(position: Int): Int {
        return when (list[position].title) {
            " " -> VIEW_TYPE_LOADING
            else -> VIEW_TYPE_ITEM
        }
    }

    inner class LoadingViewHolder(private val binding: ItemLoadingVerticalBinding) :
        RecyclerView.ViewHolder(binding.root) {

    }

    inner class Holder(val binding: ItemRecyclerviewBinding) :
        RecyclerView.ViewHolder(binding.root) {
        //        val thumbnail = binding.ivThumbnails
        //        val title = binding.tvTitle

        fun bind(item: TubeDataModel) = with(binding) { //클릭이벤트추가부분
            itemView.setOnClickListener {
                itemClick?.onClick(it, item)
            }
            Glide.with(mContext)
                .load(item.thumbnail)
                .into(ivThumbnails)
            tvTitle.text = item.title
        }
    }


    fun modifyItemToAddFavorite(item: TubeDataModel) {//좋아요 바꾸기 위한 함수
        if (item == null) return
        fun findIndex(item: TubeDataModel): Int {
            val findPosition = list.find {
                it.thumbnail == item?.thumbnail
            }
            return list.indexOf(findPosition)
        }

        val findPosition = findIndex(item)
        if (findPosition < 0) {
            return
        }
        list[findPosition] = item
        notifyDataSetChanged()
    }

    fun deleteLoading() {
        list.removeAt(list.lastIndex)
    }

}