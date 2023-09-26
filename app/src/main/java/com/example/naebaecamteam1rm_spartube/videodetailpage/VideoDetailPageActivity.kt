package com.example.naebaecamteam1rm_spartube.videodetailpage

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import com.example.naebaecamteam1rm_spartube.R
import com.example.naebaecamteam1rm_spartube.data.TubeDataModel
import com.example.naebaecamteam1rm_spartube.databinding.ActivityVideoDetailPageBinding
import com.example.naebaecamteam1rm_spartube.main.MainActivity
import com.example.naebaecamteam1rm_spartube.mypage.MyPageModel


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

        overridePendingTransition(
            R.anim.activity_video_detail_page_slide_up,
            R.anim.activity_video_detail_page_none
        )

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

        val leftPadding = resources.getDimensionPixelSize(R.dimen.left_padding) // 리소스에서 패딩 값을 가져옴
        val topPadding = resources.getDimensionPixelSize(R.dimen.top_padding)
        val rightPadding = resources.getDimensionPixelSize(R.dimen.right_padding)
        val bottomPadding = resources.getDimensionPixelSize(R.dimen.bottom_padding)

        binding.btnLike.setPadding(leftPadding, topPadding, rightPadding, bottomPadding)


        val backgroundDrawableRes = if (TubeData.isLike) {
            R.drawable.video_detail_page_btn_shape_like // 좋아요 상태일 때 배경 drawable
        } else {
            R.drawable.video_detail_page_btn_shape_unlike // 좋아요 상태가 아닐 때 배경 drawable
        }

        binding.btnLike.setBackgroundResource(backgroundDrawableRes)

        binding.btnLike.setOnClickListener{
            if (TubeData.isLike) {

                TubeData.isLike = false
                binding.btnLike.setBackgroundResource(R.drawable.video_detail_page_btn_shape_unlike)

                val mainActivity = MainActivity.newInstence()
                mainActivity!!.addFavorite(
                    MyPageModel(
                        TubeData.title,
                        TubeData.thumbnail,
                        TubeData.description,
                        TubeData.url,
                        TubeData.isLike
                    )

                )
                Toast.makeText(this@VideoDetailPageActivity, "좋아요", Toast.LENGTH_SHORT).show()
            }
            else{

                TubeData.isLike = true
                binding.btnLike.setBackgroundResource(R.drawable.video_detail_page_btn_shape_like)

                val mainActivity = MainActivity.newInstence()
                mainActivity!!.addFavorite(
                    MyPageModel(
                        TubeData.title,
                        TubeData.thumbnail,
                        TubeData.description,
                        TubeData.url,
                        TubeData.isLike
                    )

                )
                Toast.makeText(this@VideoDetailPageActivity, "좋아요", Toast.LENGTH_SHORT).show()
            }
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
            overridePendingTransition(
                R.anim.activity_video_detail_page_none,
                R.anim.activity_video_detail_page_slide_down
            )
        }
    }


}