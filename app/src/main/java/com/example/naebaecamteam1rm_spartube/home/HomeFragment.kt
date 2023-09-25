package com.example.naebaecamteam1rm_spartube.home

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.naebaecamteam1rm_spartube.data.RetrofitInstance
import com.example.naebaecamteam1rm_spartube.data.TubeDataModel
import com.example.naebaecamteam1rm_spartube.data.VideoDTO
import com.example.naebaecamteam1rm_spartube.databinding.FragmentHomeBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private val MY_KEY = "" // API KEY
    private var Q // 유튜브 검색값
            : String? = null
    private val MAX_RESULTS = 20 // 받아올 유튜브 리스트의 최대값
    private val y_datas: ArrayList<TubeDataModel> = ArrayList() // 출력 데이터를 담을 배열
    private val listAdapter by lazy{
        HomeAdapter(mContext)
    }
    private lateinit var  mContext :Context
    private lateinit var gridmanager:StaggeredGridLayoutManager

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mContext = context
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        initView()
        settest()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
    private fun initView() = with(binding){
        gridmanager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        recycler.layoutManager =gridmanager
        recycler.adapter=listAdapter

    }
    fun settest() =with(binding){
        Q = "아시안게임"
        RetrofitInstance.api.getList(MY_KEY,"snippet",Q,"videop",MAX_RESULTS)?.enqueue( object :
            Callback<VideoDTO>{
            override fun onResponse(call: Call<VideoDTO>, response: Response<VideoDTO>) {
                if(response.isSuccessful){//응답 성공시 실행
                    Log.d("test","Response")
                    val data = response.body()
                    val youtubeList = data?.items
                    if(youtubeList == null){// 가져온 데이터 없으면 리턴
                        return
                    }else{
                        for(i in youtubeList.indices){ // 가져오고 싶은 데이터 불러오고 어뎁터에 저장하는 위치
                            val title = youtubeList.get(i).snippet.title
                            val thumbnail = youtubeList.get(i).snippet.thumbnails.default.url
                            val description = youtubeList.get(i).snippet.description
//                            val url = data.etag
                            Log.d("title","$title")
                            Log.d("thumbnail","$thumbnail")
                            Log.d("description","$description")
//                            Log.d("url","$url")

                            y_datas.add(TubeDataModel(// y_data에
                                title = title,
                                thumbnail = thumbnail,
                                description= description,

                            )
                            )
                            Log.d("y_datas","$y_datas")
                            listAdapter.list = y_datas //리스트를 어댑터에 적용
                            listAdapter.notifyDataSetChanged()// notity

                        }
                    }


                }
            }


            override fun onFailure(call: Call<VideoDTO>, t: Throwable) {//실패시 찍히는 로그
                Log.d("test1","fail")
            }

            })

    }
}


