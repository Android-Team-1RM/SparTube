package com.example.naebaecamteam1rm_spartube.homepage

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import androidx.recyclerview.widget.LinearLayoutManager
import com.example.naebaecamteam1rm_spartube.api.Contants
import com.example.naebaecamteam1rm_spartube.videodetailpage.VideoDetailPageActivity
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
    private var Q // 유튜브 검색값
            : String? = null
    private var etag // Shorts의 id값
            : String? = null
    private val MAX_RESULTS = 20 // 받아올 유튜브 리스트의 최대값

    private val y_datas: ArrayList<TubeDataModel> = ArrayList() // 출력 데이터를 담을 배열
    private val s_datas: ArrayList<TubeDataModel> = ArrayList() // 출력 데이터를 담을 배열
    private val c_datas: ArrayList<TubeDataModel> = ArrayList() // 출력 데이터를 담을 배열

    private val listAdapter by lazy {
        HomeAdapter(mContext)
    }
    private val listShortsAdapter by lazy {
        HomeShortsAdapter(mContext)
    }
    private val listChannelAdapter by lazy {
        HomeCannelAdapter(mContext)
    }
    private lateinit var mContext: Context
    private lateinit var vmanager: LinearLayoutManager // 비디오 매니저
    private lateinit var smanager: LinearLayoutManager // 쇼츠 매니저
    private lateinit var cmanager: LinearLayoutManager // 채널 매니저

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
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun initView() = with(binding) {
//        setMostPopulerVideo() // 모스트 파퓰러
        setMostPopulerShorts() // 쇼츠
//        setCategoryCannels() // 카테고리 채널

        vmanager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        recyclerMpVideo.layoutManager = vmanager
        recyclerMpVideo.adapter = listAdapter

        smanager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        recyclerMpShorts.layoutManager = smanager
        recyclerMpShorts.adapter = listShortsAdapter

        cmanager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        recyclerCategoryCannels.layoutManager = cmanager
        recyclerCategoryCannels.adapter = listChannelAdapter


        listAdapter.itemClick = object : HomeAdapter.ItemClick {
            override fun onClick(view: View, tubeData: TubeDataModel) {
                startActivity(VideoDetailPageActivity.VideoDetailPageNewIntent(context, tubeData))
            }
        }

        listShortsAdapter.itemClick = object : HomeShortsAdapter.ItemClick {
            override fun onClick(view: View, tubeData: TubeDataModel) {
                startActivity(VideoDetailPageActivity.VideoDetailPageNewIntent(context, tubeData))
            }
        }

        listChannelAdapter.itemClick = object : HomeCannelAdapter.ItemClick {
            override fun onClick(view: View, tubeData: TubeDataModel) {
                startActivity(VideoDetailPageActivity.VideoDetailPageNewIntent(context, tubeData))
            }
        }

    }

    // Most populer video 부분
    fun setMostPopulerVideo() = with(binding) {

        Q = "항저우 아시안게임"
        RetrofitInstance.api.getList(Contants.MY_KEY, "snippet", Q, "video", MAX_RESULTS)?.enqueue(object :
                Callback<VideoDTO> {
                override fun onResponse(call: Call<VideoDTO>, response: Response<VideoDTO>) {
                    if (response.isSuccessful) {//응답 성공시 실행
                        Log.d("test", "Response")
                        val data = response.body()
                        val youtubeList = data?.items
                        if (youtubeList == null) {// 가져온 데이터 없으면 리턴
                            return
                        } else {
                            for (i in youtubeList.indices) { // 가져오고 싶은 데이터 불러오고 어뎁터에 저장하는 위치
                                val title = youtubeList.get(i).snippet.title
                                val thumbnail = youtubeList.get(i).snippet.thumbnails.high.url
                                val description = youtubeList.get(i).snippet.description
                                val videoID = youtubeList.get(i).id.videoId
                                var url = "https://www.youtube.com/watch?v=" + videoID
//                            val url = data.etag
                                Log.d("title", "$title")
                                Log.d("thumbnail", "$thumbnail")
                                Log.d("description", "$description")
                                Log.d("url","$url")
//                            Log.d("url","$url")

                                y_datas.add(
                                    TubeDataModel(
                                        title = title,
                                        thumbnail = thumbnail,
                                        description = description,
                                        videoId = videoID,
                                        url = url
                                        )
                                )
                                Log.d("y_datas", "$y_datas")
                                listAdapter.list = y_datas //리스트를 어댑터에 적용
                                listAdapter.notifyDataSetChanged()// notity

                            }
                        }

                    }
                }

                override fun onFailure(call: Call<VideoDTO>, t: Throwable) {//실패시 찍히는 로그
                    Log.d("test1", "fail")
                }

            })

    }

    // Most populer shorts 부분
    fun setMostPopulerShorts() = with(binding) {
        Q = "항저우 아시안게임"
        etag = "TxVSfGoUyT7CJ7h7ebjg4vhIt6g"
        RetrofitInstance.api.getShortsList(Contants.MY_KEY, "snippet", Q, etag, "video", MAX_RESULTS)?.enqueue(object :
            Callback<VideoDTO> {
            override fun onResponse(call: Call<VideoDTO>, response: Response<VideoDTO>) {
                if (response.isSuccessful) {//응답 성공시 실행
                    Log.d("test", "Response")
                    val data = response.body()
                    val youtubeList = data?.items
                    if (youtubeList == null) {// 가져온 데이터 없으면 리턴
                        return
                    } else {
                        for (i in youtubeList.indices) { // 가져오고 싶은 데이터 불러오고 어뎁터에 저장하는 위치
                            val title = youtubeList.get(i).snippet.title
                            val thumbnail = youtubeList.get(i).snippet.thumbnails.high.url
                            val description = youtubeList.get(i).snippet.description
                            val videoID = youtubeList.get(i).id.videoId
//                            val url = data.etag
                            Log.d("title", "$title")
                            Log.d("thumbnail", "$thumbnail")
                            Log.d("description", "$description")
//                            Log.d("url","$url")

                            y_datas.add(
                                TubeDataModel(
// y_data에
                                    title = title,
                                    thumbnail = thumbnail,
                                    description = description,
                                    videoId = videoID

                                )
                            )
                            Log.d("s_datas", "$s_datas")
                            listShortsAdapter.list = s_datas //리스트를 어댑터에 적용
                            listShortsAdapter.notifyDataSetChanged()// notity

                        }
                    }

                }
            }

            override fun onFailure(call: Call<VideoDTO>, t: Throwable) {//실패시 찍히는 로그
                Log.d("test1", "fail")
            }

        })

    }

    //Cannels by category 부분
    fun setCategoryCannels() = with(binding) {
        Q = "항저우 아시안게임"
        //channelId = "UCnXNukjRxXGD8aeZGRV-lYg" //스포타임 채널 ID
        RetrofitInstance.api.getchannelList(Contants.MY_KEY, "snippet", Q, "channel", /*channelId,*/MAX_RESULTS)?.enqueue(object :
                Callback<VideoDTO> {
                override fun onResponse(call: Call<VideoDTO>, response: Response<VideoDTO>) {
                    if (response.isSuccessful) {//응답 성공시 실행
                        Log.d("test", "Response")
                        val data = response.body()
                        val youtubeList = data?.items
                        if (youtubeList == null) {// 가져온 데이터 없으면 리턴
                            return
                        } else {
                            for (i in youtubeList.indices) { // 가져오고 싶은 데이터 불러오고 어뎁터에 저장하는 위치
                                val title = youtubeList.get(i).snippet.title
                                val thumbnail = youtubeList.get(i).snippet.thumbnails.high.url
                                val description = youtubeList.get(i).snippet.description
                                val videoID = youtubeList.get(i).id.videoId
//                            val url = data.etag
                                Log.d("title", "$title")
                                Log.d("thumbnail", "$thumbnail")
                                Log.d("description", "$description")
//                            Log.d("url","$url")

                                y_datas.add(
                                    TubeDataModel(
// y_data에
                                        title = title,
                                        thumbnail = thumbnail,
                                        description = description,
                                        videoId = videoID

                                    )
                                )
                                Log.d("c_datas", "$c_datas")
                                listChannelAdapter.list = c_datas //리스트를 어댑터에 적용
                                listChannelAdapter.notifyDataSetChanged()// notity

                            }
                        }

                    }
                }

                override fun onFailure(call: Call<VideoDTO>, t: Throwable) {//실패시 찍히는 로그
                    Log.d("Channeltest", "Channelfail")
                }

            })

    }

    fun modifyItemToAddFavorite(item: TubeDataModel) {
        listAdapter.modifyItemToAddFavorite(item)
    }
}