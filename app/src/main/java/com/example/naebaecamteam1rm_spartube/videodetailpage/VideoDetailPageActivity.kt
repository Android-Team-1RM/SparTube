package com.example.naebaecamteam1rm_spartube.videodetailpage

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import coil.load
import com.example.naebaecamteam1rm_spartube.R
import com.example.naebaecamteam1rm_spartube.Utils
import com.example.naebaecamteam1rm_spartube.UtilsImpl
import com.example.naebaecamteam1rm_spartube.data.TubeDataModel
import com.example.naebaecamteam1rm_spartube.data.toMyPageModel
import com.example.naebaecamteam1rm_spartube.databinding.ActivityVideoDetailPageBinding
import com.example.naebaecamteam1rm_spartube.main.MainActivity
import com.example.naebaecamteam1rm_spartube.mypage.MyPageModel


class VideoDetailPageActivity : AppCompatActivity() {

    private lateinit var binding: ActivityVideoDetailPageBinding
    private val utils: Utils = UtilsImpl(this)

    // VideoDetailPage Intent 생성하기
    companion object {
        //나중에 위치 받아서 값 설정하기
        // 위치 사용될 변수
        lateinit var TubeData: TubeDataModel
        fun VideoDetailPageNewIntent(context: Context?, tubeData: TubeDataModel) =
            Intent(context, VideoDetailPageActivity::class.java).apply {
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


    private fun initView() {
//        Glide.with(this)
//            .load(Uri.parse(TubeData.thumbnail))
//            .placeholder(R.drawable.video_detail_page_img_base)
//            .error(R.drawable.video_detail_page_img_base)
//            .fitCenter()
//            .into(binding.ivThumbnail)
        binding.ivThumbnail.load(Uri.parse(TubeData.thumbnail))

        binding.tvTitle.text = TubeData.title
        binding.tvDescription.text = TubeData.description
        if (TubeData.description == "" || TubeData.description == " "|| TubeData.description == null) {
            binding.cvDescription.visibility = View.INVISIBLE
        }
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    private fun btnSet() {

        val leftPadding = resources.getDimensionPixelSize(R.dimen.left_padding)
        val topPadding = resources.getDimensionPixelSize(R.dimen.top_padding)
        val rightPadding = resources.getDimensionPixelSize(R.dimen.right_padding)
        val bottomPadding = resources.getDimensionPixelSize(R.dimen.bottom_padding)

        binding.btnLike.setPadding(leftPadding, topPadding, rightPadding, bottomPadding)


        val backgroundDrawableRes = if (TubeData.isLike) {
            R.drawable.video_detail_page_btn_shape_like // 좋아요 상태일 때 배경 drawable
        } else {
            R.drawable.video_detail_page_btn_shape_im // 좋아요 상태가 아닐 때 배경 drawable
        }

        val thumbUpDrawableRes = if (TubeData.isLike) {
            resources.getDrawable(R.drawable.video_detail_page_btn_ic_like) // 좋아요 상태일 때 Drawable 리소스
        } else {
            resources.getDrawable(R.drawable.video_detail_page_btn_ic_like_im) // 좋아요 상태가 아닐 때 Drawable 리소스
        }

        binding.btnLike.setCompoundDrawablesRelativeWithIntrinsicBounds(
            thumbUpDrawableRes,
            null,
            null,
            null
        )

        binding.btnLike.setBackgroundResource(backgroundDrawableRes)



        binding.btnLike.setOnClickListener {


            Log.d("btnLike", "btnLikeOk")
            if (TubeData.isLike) {

                TubeData.isLike = false
                binding.btnLike.setBackgroundResource(R.drawable.video_detail_page_btn_shape_im)

                binding.btnLike.setCompoundDrawablesRelativeWithIntrinsicBounds(
                    resources.getDrawable(
                        R.drawable.video_detail_page_btn_ic_like_im
                    ), null, null, null
                )

                val mainActivity = MainActivity.newInstence()
                mainActivity!!.removeFavoriteToMyPage(TubeData.toMyPageModel())
                mainActivity.modifyFavoriteToHome(TubeData)
                mainActivity.modifyFavoriteToSearch(TubeData)
                utils.deletePrefItem(TubeData.thumbnail!!)
                Toast.makeText(this@VideoDetailPageActivity, "좋아요 해제", Toast.LENGTH_SHORT).show()
            } else {

                TubeData.isLike = true
                binding.btnLike.setBackgroundResource(R.drawable.video_detail_page_btn_shape_like)

                binding.btnLike.setCompoundDrawablesRelativeWithIntrinsicBounds(
                    resources.getDrawable(
                        R.drawable.video_detail_page_btn_ic_like
                    ), null, null, null
                )

                val mainActivity = MainActivity.newInstence()
                mainActivity!!.addFavorite(
                    MyPageModel(
                        title = TubeData.title,
                        thumbnail = TubeData.thumbnail,
                        description = TubeData.description,
                        videoId = TubeData.videoId,
                        url = TubeData.url,
                        channelId = TubeData.channelId,
                        isLike = TubeData.isLike
                    )

                )
                mainActivity.modifyFavoriteToHome(TubeData)
                mainActivity.modifyFavoriteToSearch(TubeData)
                utils.addPrefItem(TubeData.toMyPageModel())
                Toast.makeText(this@VideoDetailPageActivity, "좋아요", Toast.LENGTH_SHORT).show()
            }
        }


        binding.btnShare.setOnClickListener {
            Log.d("btnShare", "btnShareOk")
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
            Log.d("btnPlayList", "btnPlayList")
            //재생 목록에서 값을 갖고가는 함수를 만든다.      -> TubeData를 넘겨주면 된다.

        }

        binding.icBtnDown.setOnClickListener {

            finish()
            overridePendingTransition(
                R.anim.activity_video_detail_page_none,
                R.anim.activity_video_detail_page_slide_down
            )
        }
        binding.ivThumbnail.setOnClickListener {
            var intent = Intent(Intent.ACTION_VIEW, Uri.parse("${TubeData.url}"))
            startActivity(intent)
        }

    }
}