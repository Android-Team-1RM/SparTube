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
import com.example.naebaecamteam1rm_spartube.databinding.ItemLoadingBinding
import com.example.naebaecamteam1rm_spartube.databinding.ItemMyPageRecyclerviewBinding
import com.example.naebaecamteam1rm_spartube.databinding.ItemRecyclerviewBinding
import com.example.naebaecamteam1rm_spartube.videodetailpage.VideoDetailPageActivity
import retrofit2.Callback
import retrofit2.Response
import java.nio.channels.Channel

class MyPageAdapter(
    context: Context,
    private val onClickItem: (MyPageModel) -> Unit,
    private val onClickFav: (MyPageModel) -> Unit,
) : ListAdapter<MyPageModel, MyPageAdapter.ViewHolder>(
    object : DiffUtil.ItemCallback<MyPageModel>() {
        override fun areContentsTheSame(oldItem: MyPageModel, newItem: MyPageModel): Boolean {
            return oldItem.thumbnail == newItem.thumbnail
        }

        override fun areItemsTheSame(oldItem: MyPageModel, newItem: MyPageModel): Boolean {
            return oldItem == newItem
        }
    }) {
    private var mContext = context
    private val VIEW_TYPE_ITEM = 0
    private val VIEW_TYPE_LOADING = 1

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemMyPageRecyclerviewBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            ),
            onClickItem, onClickFav
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
        Log.d("Binding!", "$position")
    }

    inner class ViewHolder(
        private val binding: ItemMyPageRecyclerviewBinding,
        private val onClickItem: (MyPageModel) -> Unit,
        private val onClickFav: (MyPageModel) -> Unit,
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: MyPageModel) = with(binding) {
            RetrofitInstance.api.getChannelThumbnail(Contants.MY_KEY, "snippet", item.channelId)
                ?.enqueue(object :
                    Callback<ChannelDTO> {
                    override fun onResponse(
                        call: retrofit2.Call<ChannelDTO>,
                        response: Response<ChannelDTO>
                    ) {
                        if (response.isSuccessful) {
                            Log.d("test", "Response")
                            val data = response.body()
                            Log.d("test1", "$data")
                            val channelTag = data?.items!!.get(0).snippet.customUrl
                            Log.d("test2", "$channelTag")
                            ivChannelThumbnail.load(Uri.parse(data?.items!!.get(0).snippet.thumbnails.medium.url))
                            tvChannel.text = data?.items!!.get(0).snippet.title
                        }

                    }

                    override fun onFailure(call: retrofit2.Call<ChannelDTO>, t: Throwable) {
                        Log.d("test", "fail")
                    }
                })
            ivFavBtn.setOnClickListener {
                Log.d("test", "delete")
                item.isLike = false
                onClickFav(
                    item
                )
            }
            container.setOnClickListener {
                onClickItem(
                    item
                )
            }

            Glide.with(mContext)
                .load(item.thumbnail)
                .error(R.drawable.video_detail_page_img_base)
                .into(ivThumbnails)
            tvTitle.text = item.title

            tvDescription.text = item.description

        }

    }

    override fun getItemViewType(position: Int): Int {// 아이템뷰와 프로그래스바 타입 구분
        val item = getItem(position)
        return when (item.title) {
            " " -> VIEW_TYPE_LOADING
            else -> VIEW_TYPE_ITEM
        }
    }

//    inner class NoticeViewHolder(private val binding:ItemLoadingBinding):
//            RecyclerView.ViewHolder(binding.root){
//
//            }

}