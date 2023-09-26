package com.example.naebaecamteam1rm_spartube.searchpage

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.naebaecamteam1rm_spartube.videodetailpage.VideoDetailPageActivity
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
    private val MY_KEY = "AIzaSyAc4RlJZzge6tvjG8zZjefnsJy9A_iSnTw"
    private val MAX_RESULTS = 20 // 받아올 유튜브 리스트의 최대값
    private lateinit var mContext: Context
    private lateinit var adapter: SearchAdapter
    private val youDatas: ArrayList<TubeDataModel> = ArrayList() // 출력 데이터를 담을 배열
    private lateinit var gridmanager: StaggeredGridLayoutManager

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mContext = context
    }
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
        setView()
        searchInEdit()
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

        firstBtn.setOnClickListener {
            adapter.clearItem()
            settest(firstBtn.text.toString())
        }
        secondBtn.setOnClickListener {
            adapter.clearItem()
            settest(secondBtn.text.toString())
        }
        thirdBtn.setOnClickListener {
            adapter.clearItem()
            settest(thirdBtn.text.toString())
        }
    }

    private fun searchInEdit(){
        binding.tvSearch.setOnClickListener {
            val search = binding.etSearch.text.toString()
            if (search.isNotEmpty()){
                adapter.clearItem()
                settest(search)
            }
        }
    }



    fun setView(){
        gridmanager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        adapter = SearchAdapter(mContext)
        binding.rvSearchResult.adapter = adapter
        binding.rvSearchResult.layoutManager = gridmanager

        adapter.itemClick = object : SearchAdapter.ItemClick{
            override fun onClick(view: View, tubeData : TubeDataModel) {
                startActivity(VideoDetailPageActivity.VideoDetailPageNewIntent(context,tubeData))
            }
        }

    }


    fun settest(search: String) =with(binding){
        RetrofitInstance.api.getList(MY_KEY,"snippet","아시안게임 $search","videop",MAX_RESULTS)?.enqueue( object :
            Callback<VideoDTO> {
            override fun onResponse(call: Call<VideoDTO>, response: Response<VideoDTO>) {
                if(response.isSuccessful){
                    Log.d("test","Response")
                    val data = response.body()
                    val youtubeList = data?.items
                    if(youtubeList == null){
                        return
                    }else{
                        for(i in youtubeList.indices){
                            val title = youtubeList.get(i).snippet.title
                            val thumbnail = youtubeList.get(i).snippet.thumbnails.default.url
                            Log.d("title","$title")
                            Log.d("url","$thumbnail")
                            youDatas.add(
                                TubeDataModel(
                                title = title,
                                thumbnail = thumbnail, description = null)
                            )
                            Log.d("y_datas","$youDatas")
//                            listAdapter.list = y_datas
//                            listAdapter.notifyDataSetChanged() // 어뎁터 설정
                        }
                    }
                }
                adapter.items = youDatas
                adapter.notifyDataSetChanged()
            }


            override fun onFailure(call: Call<VideoDTO>, t: Throwable) {
                Log.d("test1","fail")
            }

        })

    }
}