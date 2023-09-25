package com.example.naebaecamteam1rm_spartube.home

import android.R
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.naebaecamteam1rm_spartube.data.VideoDTO

class YoutubeAdapter(context: Context, datas: List<VideoDTO>) :
    RecyclerView.Adapter<YoutubeAdapter.ViewHolder>() {
    private val context: Context
    private var datas: List<VideoDTO> = ArrayList<VideoDTO>()

    init {
        this.context = context
        this.datas = datas
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): YoutubeAdapter.ViewHolder {
        val view: View =
            LayoutInflater.from(parent.context).inflate(R.layout.item, parent, false)
        return YoutubeAdapter.ViewHolder(view)
    }

    override fun onBindViewHolder(holder: YoutubeAdapter.ViewHolder, position: Int) {
        holder.bind(context, datas, position)
    }

    override fun getItemCount(): Int {
        return datas.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var title: TextView
        var thumbnail: ImageView

        init {
            title = itemView.findViewById(R.id.youtube_title)
            thumbnail = itemView.findViewById(R.id.youtube_thumbnail)
        }

        fun bind(context: Context, datas: List<VideoDTO>, position: Int) {
            title.setText(datas[position].getTitle()) // 해당 포지션에서 제목을 받아옴
            val url: String = datas[position].getUrl() // 해당 포지션에서 썸네일 url을 받아옴
            Glide.with(context).load(url).into(thumbnail) // url -> 사진으로 변경 후 이미지뷰에 설정
            itemView.setOnClickListener(object : View.OnClickListener() {
                // 리스트를 눌렀을 때 액션 설정(해당 유튜브 동영상이 나오게 설정해야 함)
                fun onClick(view: View?) {
                    Toast.makeText(context, "클릭하였음.", Toast.LENGTH_SHORT).show()
                }
            })
        }
    }
}