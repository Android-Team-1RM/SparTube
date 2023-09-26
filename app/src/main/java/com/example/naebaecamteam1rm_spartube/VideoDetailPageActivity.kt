package com.example.naebaecamteam1rm_spartube

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
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
//                TubeDataModel = tubeDataModel
                TubeData = tubeData
            }

    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityVideoDetailPageBinding.inflate(layoutInflater)
        setContentView(binding.root)

        overridePendingTransition(R.anim.activity_video_detail_page_slide_up, 0)

        btnSet()
    }


    private fun btnSet(){

        binding.btnLike.setOnClickListener{

        }

        binding.btnShare.setOnClickListener{

        }

        binding.btnPlayList.setOnClickListener {

        }

        binding.icBtnDown.setOnClickListener{
            finish()
        }
    }
}