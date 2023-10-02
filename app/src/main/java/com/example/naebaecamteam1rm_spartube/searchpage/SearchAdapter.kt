package com.example.naebaecamteam1rm_spartube.searchpage

import android.content.Context
import android.net.Uri
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.bumptech.glide.Glide
import com.example.naebaecamteam1rm_spartube.api.Contants
import com.example.naebaecamteam1rm_spartube.data.ChannelDTO
import com.example.naebaecamteam1rm_spartube.data.RetrofitInstance
import com.example.naebaecamteam1rm_spartube.data.TubeDataModel
import com.example.naebaecamteam1rm_spartube.databinding.ItemFragmentSearchBinding
import com.example.naebaecamteam1rm_spartube.databinding.ItemRecyclerviewBinding
import retrofit2.Callback
import retrofit2.Response

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
            ItemFragmentSearchBinding.inflate(
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

    inner class Holder(val binding: ItemFragmentSearchBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: TubeDataModel) = with(binding){ //클릭이벤트추가부분
            RetrofitInstance.api.getChannelThumbnail(Contants.MY_KEY,"snippet", item.channelId)?.enqueue(object :
                Callback<ChannelDTO> {
                override fun onResponse(
                    call: retrofit2.Call<ChannelDTO>,
                    response: Response<ChannelDTO>
                ) {
                    Log.d("test", "Response")
                    val data = response.body()
                    Log.d("test1","$data")
//                    ivChannelThumbnail.load(Uri.parse(data?.items!!.get(0).snippet.thumbnails.medium.url)) << Iv 지정

                }

                override fun onFailure(call: retrofit2.Call<ChannelDTO>, t: Throwable) {
                    Log.d("test", "fail")
                }
            })


            itemView.setOnClickListener{
                itemClick?.onClick(it, item)
            }
            Glide.with(mContext)
                .load(Uri.parse(item.thumbnail))
                .into(ivThumbnails)
            tvTitle.text = item.title
            tvChannel.text = item.channelName
        }
    }
}