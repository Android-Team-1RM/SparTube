package com.example.naebaecamteam1rm_spartube

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.naebaecamteam1rm_spartube.data.TubeDataModel
import com.example.naebaecamteam1rm_spartube.databinding.ActivityVideoDetailPageBinding


class VideoDetailPageActivity : AppCompatActivity() {

    private lateinit var binding: ActivityVideoDetailPageBinding

    // VideoDetailPage Intent 생성하기
    companion object{
        //나중에 위치 받아서 값 설정하기
        // 위치 사용될 변수
        lateinit var TubeData : TubeDataModel
        fun VideoDetailPageNewIntent(context: Context?, tubeData : TubeDataModel) =
            Intent(context, VideoDetailPageActivity::class.java).apply{
                TubeData = tubeData
            }

    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityVideoDetailPageBinding.inflate(layoutInflater)
        setContentView(binding.root)

        overridePendingTransition(R.anim.activity_video_detail_page_slide_up,R.anim.activity_video_detail_page_none)

        initView()
        btnSet()

    }

    private fun initView(){
        Glide.with(this)
            .load(TubeData.thumbnail)
            .error(R.drawable.video_detail_page_img_base)
            .fitCenter()
            .into(binding.ivThumbnail)
        binding.tvTitle.text = TubeData.title
        binding.tvDescription.text = TubeData.description
    }

    private fun btnSet(){

        binding.btnLike.setOnClickListener{

            //좋아요의 값을 갖고가는 함수를 만든다.      -> TubeData.isLike를 넘겨주면 된다.

        }

        binding.btnShare.setOnClickListener{
            val sharedIntent = Intent().apply {
                action = Intent.ACTION_SEND
                putExtra(
                    Intent.EXTRA_TEXT,
                    // 전달하려는 Data(Value)
                    TubeData.url
                )
                type = "text/plain"
            }
            startActivity(Intent.createChooser(sharedIntent, null))
        }

        binding.btnPlayList.setOnClickListener {

            //재생 목록에서 값을 갖고가는 함수를 만든다.      -> TubeData를 넘겨주면 된다.

        }

        binding.icBtnDown.setOnClickListener{

            finish()
            overridePendingTransition(R.anim.activity_video_detail_page_none,R.anim.activity_video_detail_page_slide_down)
        }
    }


}