package com.example.naebaecamteam1rm_spartube.mypage

import android.content.Context
import android.net.Uri
import android.telecom.Call
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.bumptech.glide.Glide
import com.example.naebaecamteam1rm_spartube.R
import com.example.naebaecamteam1rm_spartube.api.Contants
import com.example.naebaecamteam1rm_spartube.data.ChannelDTO
import com.example.naebaecamteam1rm_spartube.data.RetrofitInstance
import com.example.naebaecamteam1rm_spartube.data.TubeDataModel
import com.example.naebaecamteam1rm_spartube.databinding.ItemMyPageRecyclerviewBinding
import com.example.naebaecamteam1rm_spartube.databinding.ItemRecyclerviewBinding
import com.example.naebaecamteam1rm_spartube.videodetailpage.VideoDetailPageActivity
import retrofit2.Callback
import retrofit2.Response
import java.nio.channels.Channel

class MyPageAdapter(context : Context,
                    private val onClickItem: (MyPageModel) -> Unit,
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
        private val onClickItem: (MyPageModel) -> Unit,
    ):RecyclerView.ViewHolder(binding.root){

        fun bind(item:MyPageModel) = with(binding){
            RetrofitInstance.api.getChannelThumbnail(Contants.MY_KEY,"snippet", item.channelId)?.enqueue(object :
                Callback<ChannelDTO> {
                override fun onResponse(
                    call: retrofit2.Call<ChannelDTO>,
                    response: Response<ChannelDTO>
                ) {
                    Log.d("test", "Response")
                    val data = response.body()
                    Log.d("test1","$data")
                    ivChannelThumbnail.load(Uri.parse(data?.items!!.get(0).snippet.thumbnails.medium.url))
                    tvChannel.text = data?.items!!.get(0).snippet.title

                }

                override fun onFailure(call: retrofit2.Call<ChannelDTO>, t: Throwable) {
                    Log.d("test", "fail")
                }
            })

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

            tvDescription.text= item.description

        }

    }

}