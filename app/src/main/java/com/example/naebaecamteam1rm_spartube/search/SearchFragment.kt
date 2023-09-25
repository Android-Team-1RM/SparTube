package com.example.naebaecamteam1rm_spartube.search

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.naebaecamteam1rm_spartube.data.RetrofitInstance
import com.example.naebaecamteam1rm_spartube.data.TubeDataModel
import com.example.naebaecamteam1rm_spartube.data.VideoDTO
import com.example.naebaecamteam1rm_spartube.databinding.FragmentSearchBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SearchFragment : Fragment() {
    private lateinit var binding: FragmentSearchBinding
    private val tag = "SearchFragment"
    private val MAX_RESULTS = 20 // 받아올 유튜브 리스트의 최대값
    private val y_datas: ArrayList<TubeDataModel> = ArrayList() // 출력 데이터를 담을 배열

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentSearchBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        updateBtn()
        binding.ivRefresh.setOnClickListener { updateBtn() }
    }


    private fun updateBtn(){
        val btnRandomWord = searchWordList.shuffled().take(3)
        val firstBtn = binding.btnRandomFirst
        val secondBtn = binding.btnRandomSecond
        val thirdBtn  =binding.btnRandomThird
        firstBtn.text = btnRandomWord.getOrElse(0){""}
        secondBtn.text = btnRandomWord.getOrElse(1){""}
        thirdBtn.text = btnRandomWord.getOrElse(2){""}
    }

//    fun settest(search: String) =with(binding){
//
//        RetrofitInstance.api.getList(MY_KEY,"snippet",search,"videop",MAX_RESULTS)?.enqueue( object :
//            Callback<VideoDTO> {
//            override fun onResponse(call: Call<VideoDTO>, response: Response<VideoDTO>) {
//                if(response.isSuccessful){
//                    Log.d("test","Response")
//                    val data = response.body()
//                    val youtubeList = data?.items
//                    if(youtubeList == null){
//                        return
//                    }else{
//                        for(i in youtubeList.indices){
//                            val title = youtubeList.get(i).snippet.title
//                            val thumbnail = youtubeList.get(i).snippet.thumbnails.default.url
//                            Log.d("title","$title")
//                            Log.d("url","$thumbnail")
//
//                            y_datas.add(
//                                TubeDataModel(
//                                title = title,
//                                thumbnail = thumbnail)
//                            )
//                            Log.d("y_datas","$y_datas")
////                            listAdapter.list = y_datas
////                            listAdapter.notifyDataSetChanged() // 어뎁터 설정
//
//                        }
//                    }
//
//
//                }
//            }
//
//
//            override fun onFailure(call: Call<VideoDTO>, t: Throwable) {
//                Log.d("test1","fail")
//            }
//
//        })
//
//    }
}