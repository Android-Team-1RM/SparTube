package com.example.naebaecamteam1rm_spartube.homepage

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.naebaecamteam1rm_spartube.api.Contants
import com.example.naebaecamteam1rm_spartube.data.RetrofitInstance
import com.example.naebaecamteam1rm_spartube.data.TubeDataModel
import com.example.naebaecamteam1rm_spartube.data.VideoDTO
import com.example.naebaecamteam1rm_spartube.databinding.FragmentHomeBinding
import com.example.naebaecamteam1rm_spartube.videodetailpage.VideoDetailPageActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding? = null

    private val binding get() = _binding!!

    private var Q // 유튜브 검색값
            : String? = null
    private var videoDuration = "short" // 영상 길이
    private val MAX_RESULTS = 50 // 받아올 유튜브 리스트의 최대값

    private var y_datas: ArrayList<TubeDataModel> = ArrayList() // 출력 데이터를 담을 배열
    private var s_datas: ArrayList<TubeDataModel> = ArrayList() // 출력 데이터를 담을 배열
    private var c_datas: ArrayList<TubeDataModel> = ArrayList() // 출력 데이터를 담을 배열

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
    private var nextPageToken: String? = null
    private var currentNextPageToken: String? = null
    private var nextPageTokenForShort: String? = null
    private var nextPageTokenForChannel: String? = null

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
//        setMostPopulerShorts() // 쇼츠
//        setCategoryCannels() // 카테고리 채널
        //모스트비디오 인피니티스크롤 적용
        recyclerMpVideo.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                val lastVisibleItemPosition =
                    (recyclerMpVideo.layoutManager as LinearLayoutManager?)!!.findLastCompletelyVisibleItemPosition()
                val itemCount = listAdapter!!.itemCount - 1
                Log.d("test7", "$lastVisibleItemPosition")
                Log.d("test8", "$itemCount")
                Log.d("test8", "$")

                if(!recyclerMpVideo.canScrollHorizontally(1) && lastVisibleItemPosition == itemCount) {
                    listAdapter.deleteLoading()
                    infinityAddItems()
                }
            }
        })
        //쇼츠 어뎁터 인피니티스크롤 적용
        recyclerMpShorts.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                val lastVisibleItemPosition =
                    (recyclerMpShorts.layoutManager as LinearLayoutManager?)!!.findLastCompletelyVisibleItemPosition()
                val itemCount = listShortsAdapter!!.itemCount - 1
                val test = recyclerMpShorts.scrollState
//                Log.d("test7", "$lastVisibleItemPosition")
//                Log.d("test8", "$itemCount")
//                Log.d("test9", "$test")
                if (!recyclerMpShorts.canScrollHorizontally(1) && lastVisibleItemPosition == itemCount) {
                    Log.d("endScroll", "endScroll")
                    listShortsAdapter.deleteLoading()
                    infinityAddItemsShort()

                }
            }
        })
        //채널 어뎁터 인피니티스크롤 적용
        recyclerCategoryCannels.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                val lastVisibleItemPosition =
                    (recyclerCategoryCannels.layoutManager as LinearLayoutManager?)!!.findLastCompletelyVisibleItemPosition()
                val itemCount = listChannelAdapter!!.itemCount - 1
                Log.d("test7", "$lastVisibleItemPosition")
                Log.d("test8", "$itemCount")
                if (!recyclerCategoryCannels.canScrollHorizontally(1) && lastVisibleItemPosition == itemCount) {
                    listChannelAdapter.deleteLoading()
                    infinityAddItemsChannel()
                }
            }
        })


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
        RetrofitInstance.api.getList(Contants.MY_KEY, "snippet", Q, "video", MAX_RESULTS)
            ?.enqueue(object :
                Callback<VideoDTO> {
                override fun onResponse(call: Call<VideoDTO>, response: Response<VideoDTO>) {
                    if (response.isSuccessful) {//응답 성공시 실행
                        Log.d("test", "Response")
                        val data = response.body()
                        val youtubeList = data?.items
                        if (youtubeList == null) {// 가져온 데이터 없으면 리턴
                            return
                        } else {
                            nextPageToken = data?.nextPageToken

                            Log.d("nextPageToken", "$nextPageToken")
                            for (i in youtubeList.indices) { // 가져오고 싶은 데이터 불러오고 어뎁터에 저장하는 위치
                                var title = youtubeList.get(i).snippet.title
                                title = title.replace("&#39;", "'")
                                title = title.replace("&quot;","\"")
                                val thumbnail = youtubeList.get(i).snippet.thumbnails.high.url
                                val description = youtubeList.get(i).snippet.description
                                val videoID = youtubeList.get(i).id.videoId
                                val channelID = youtubeList.get(i).snippet.channelId
                                var url = "https://www.youtube.com/watch?v=" + videoID
//                            val url = data.etag
                                Log.d("title", "$title")
                                Log.d("thumbnail", "$thumbnail")
                                Log.d("description", "$description")
                                Log.d("url", "$url")
//                            Log.d("url","$url")

                                y_datas.add(
                                    TubeDataModel(
                                        title = title,
                                        thumbnail = thumbnail,
                                        description = description,
                                        videoId = videoID,
                                        url = url,
                                        channelId = channelID
                                    )
                                )
//                                y_datas.add(TubeDataModel(" "))
//                                Log.d("y_datas", "$y_datas")
//                                listAdapter.list = y_datas //리스트를 어댑터에 적용
//                                listAdapter.notifyDataSetChanged()// notity


                            }
                            y_datas.add(TubeDataModel(" "))
                            Log.d("y_datas", "$y_datas")
                            listAdapter.list = y_datas //리스트를 어댑터에 적용
                            listAdapter.notifyDataSetChanged()// notity
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
        Q =
            "황저우 아시안게임 쇼츠 shorts" // https://www.youtube.com/shorts/ -> 모든 쇼츠는 이 url을 가지고 잇어서 url제한을 하면 나올지도?
        //videoCategoryId = "19" // videoDuration에서 short로 하고 필터로 시간 줄이기
        RetrofitInstance.api.getShortsList(
            Contants.MY_KEY,
            "snippet",
            Q,
            videoDuration,
            "video",
            MAX_RESULTS
        )?.enqueue(object :
            Callback<VideoDTO> {
            override fun onResponse(call: Call<VideoDTO>, response: Response<VideoDTO>) {
                if (response.isSuccessful) {//응답 성공시 실행
                    Log.d("test", "Response")
                    val data = response.body()
                    val youtubeList = data?.items
                    nextPageTokenForShort = data?.nextPageToken
                    if (youtubeList == null) {// 가져온 데이터 없으면 리턴
                        return
                    } else {
                        for (i in youtubeList.indices) { // 가져오고 싶은 데이터 불러오고 어뎁터에 저장하는 위치
                            var title = youtubeList.get(i).snippet.title
                            title = title.replace("&#39;", "'")
                            title = title.replace("&quot;","\"")
                            val thumbnail = youtubeList.get(i).snippet.thumbnails.high.url
                            val description = youtubeList.get(i).snippet.description
                            val videoID = youtubeList.get(i).id.videoId
                            val channelID = youtubeList.get(i).snippet.channelId
//                            val url = data.etag
                            Log.d("title", "$title")
                            Log.d("thumbnail", "$thumbnail")
                            Log.d("description", "$description")
                            Log.d("shorts", "$videoID")
                            var url = "https://www.youtube.com/shorts/" + videoID

                            s_datas.add(
                                TubeDataModel(
// y_data에
                                    title = title,
                                    thumbnail = thumbnail,
                                    description = description,
                                    videoId = videoID,
                                    url = url,
                                    channelId = channelID

                                )
                            )


                        }

                    }
                    Log.d("s_datas", "$s_datas")
                    s_datas.add(TubeDataModel(" "))
                    listShortsAdapter.list = s_datas //리스트를 어댑터에 적용
                    listShortsAdapter.notifyDataSetChanged()// notity
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
        RetrofitInstance.api.getchannelList(Contants.MY_KEY, "snippet", Q, "channel", MAX_RESULTS)
            ?.enqueue(object :
                Callback<VideoDTO> {
                override fun onResponse(call: Call<VideoDTO>, response: Response<VideoDTO>) {
                    if (response.isSuccessful) {//응답 성공시 실행
                        Log.d("test", "Response")
                        val data = response.body()
                        val youtubeList = data?.items
                        nextPageTokenForChannel = data!!.nextPageToken
                        if (youtubeList == null) {// 가져온 데이터 없으면 리턴
                            return
                        } else {
                            for (i in youtubeList.indices) { // 가져오고 싶은 데이터 불러오고 어뎁터에 저장하는 위치
                                var title = youtubeList.get(i).snippet.title
                                title = title.replace("&#39;", "'")
                                title = title.replace("&quot;","\"")
                                val thumbnail = youtubeList.get(i).snippet.thumbnails.high.url
                                val description = youtubeList.get(i).snippet.description
                                val videoID = youtubeList.get(i).id.videoId
                                val channelID = youtubeList.get(i).snippet.channelId
//                            val url = data.etag
                                Log.d("title", "$title")
                                Log.d("thumbnail", "$thumbnail")
                                Log.d("description", "$description")
                                Log.d("shorts", "$videoID")
                                var url = "https://www.youtube.com/channel/$channelID"

                                c_datas.add(
                                    TubeDataModel(
// y_data에
                                        title = title,
                                        thumbnail = thumbnail,
                                        description = description,
                                        videoId = videoID,
                                        url = url,
                                        channelId = channelID

                                    )
                                )

                            }
                            Log.d("c_datas", "$c_datas")
                            c_datas.add(TubeDataModel(" "))
                            listChannelAdapter.list = c_datas //리스트를 어댑터에 적용
                            listChannelAdapter.notifyDataSetChanged()// notity

                        }

                    }
                }

                override fun onFailure(call: Call<VideoDTO>, t: Throwable) {//실패시 찍히는 로그
                    Log.d("Channeltest", "Channelfail")
                }
            })
    }

    fun infinityAddItems() {
        Log.d("nextPageToken", "$nextPageToken")
        Log.d("currentNextPageToken", "$currentNextPageToken")
        if (nextPageToken == currentNextPageToken) {
            return
        } else {
            RetrofitInstance.api.getNextList(
                Contants.MY_KEY,
                "snippet",
                Q,
                "video",
                nextPageToken,
                MAX_RESULTS
            )?.enqueue(object :
                Callback<VideoDTO> {
                override fun onResponse(call: Call<VideoDTO>, response: Response<VideoDTO>) {
                    if (response.isSuccessful) {//응답 성공시 실행
                        Log.d("test", "Response")
                        val data = response.body()
                        val youtubeList = data?.items
                        currentNextPageToken = data?.nextPageToken
                        if (youtubeList == null) {// 가져온 데이터 없으면 리턴
                            return
                        } else {
                            nextPageTokenForShort = data?.nextPageToken
                            for (i in 1 until youtubeList.size - 1) { // 가져오고 싶은 데이터 불러오고 어뎁터에 저장하는 위치
                                var title = youtubeList.get(i).snippet.title
                                title = title.replace("&#39;", "'")
                                title = title.replace("&quot;","\"")
                                val thumbnail = youtubeList.get(i).snippet.thumbnails.high.url
                                val description = youtubeList.get(i).snippet.description
                                val videoID = youtubeList.get(i).id.videoId
                                val channelID = youtubeList.get(i).snippet.channelId
                                var url = "https://www.youtube.com/watch?v=" + videoID
//                            val url = data.etag
                                Log.d("title", "$title")
                                Log.d("thumbnail", "$thumbnail")
                                Log.d("description", "$description")
                                Log.d("url", "$url")
//                            Log.d("url","$url")

                                y_datas.add(
                                    TubeDataModel(
                                        title = title,
                                        thumbnail = thumbnail,
                                        description = description,
                                        videoId = videoID,
                                        url = url,
                                        channelId = channelID
                                    )
                                )


                            }
                            Log.d("y_datas", "$y_datas")
                            y_datas.add(TubeDataModel(" "))
                            listAdapter.list = y_datas //리스트를 어댑터에 적용
                            listAdapter.notifyDataSetChanged()// notity
                        }

                    }
                }

                override fun onFailure(call: Call<VideoDTO>, t: Throwable) {//실패시 찍히는 로그
                    Log.d("test1", "fail")
                }

            })
        }


    }

    fun infinityAddItemsShort() {
        Log.d("nextPageTokenForShort", "$nextPageTokenForShort")
        Q =
            "황저우 아시안게임 쇼츠 shorts" // https://www.youtube.com/shorts/ -> 모든 쇼츠는 이 url을 가지고 잇어서 url제한을 하면 나올지도?
        //videoCategoryId = "19" // videoDuration에서 short로 하고 필터로 시간 줄이기
        RetrofitInstance.api.getNextShortsList(
            Contants.MY_KEY,
            "snippet",
            Q,
            videoDuration,
            "video",
            nextPageTokenForShort,
            MAX_RESULTS
        )?.enqueue(object :
            Callback<VideoDTO> {
            override fun onResponse(call: Call<VideoDTO>, response: Response<VideoDTO>) {
                if (response.isSuccessful) {//응답 성공시 실행
                    Log.d("test", "Response")
                    val data = response.body()
                    val youtubeList = data?.items
                    if (youtubeList == null) {// 가져온 데이터 없으면 리턴
                        return
                    } else {
                        nextPageTokenForShort = data?.nextPageToken
                        for (i in 1 until youtubeList.size - 1) { // 가져오고 싶은 데이터 불러오고 어뎁터에 저장하는 위치
                            var title = youtubeList.get(i).snippet.title
                            title = title.replace("&#39;", "'")
                            title = title.replace("&quot;","\"")
                            val thumbnail = youtubeList.get(i).snippet.thumbnails.high.url
                            val description = youtubeList.get(i).snippet.description
                            val videoID = youtubeList.get(i).id.videoId
                            val channelID = youtubeList.get(i).snippet.channelId
                            var url = "https://www.youtube.com/shorts/" + videoID
//                            val url = data.etag
                            Log.d("title", "$title")
                            Log.d("thumbnail", "$thumbnail")
                            Log.d("description", "$description")
                            Log.d("url", "$url")
//                            Log.d("url","$url")

                            s_datas.add(
                                TubeDataModel(
                                    title = title,
                                    thumbnail = thumbnail,
                                    description = description,
                                    videoId = videoID,
                                    url = url,
                                    channelId = channelID
                                )
                            )

                        }
                        Log.d("s_datas", "$s_datas")
                        s_datas.add(TubeDataModel(" "))
                        listShortsAdapter.list = s_datas //리스트를 어댑터에 적용
                        listShortsAdapter.notifyDataSetChanged()// notity
                    }

                }
            }

            override fun onFailure(call: Call<VideoDTO>, t: Throwable) {//실패시 찍히는 로그
                Log.d("test1", "fail")
            }

        })

    }

    private fun infinityAddItemsChannel() {
        Log.d("nextPageTokenForChannel", "$nextPageTokenForChannel")
        Q = "황저우 아시안게임"
        RetrofitInstance.api.getNextChannelList(
            Contants.MY_KEY,
            "snippet",
            Q,
            "Channel",
            nextPageTokenForShort,
            MAX_RESULTS
        )?.enqueue(object :
            Callback<VideoDTO> {
            override fun onResponse(call: Call<VideoDTO>, response: Response<VideoDTO>) {
                if (response.isSuccessful) {//응답 성공시 실행
                    Log.d("test", "Response")
                    val data = response.body()
                    val youtubeList = data?.items
                    if (youtubeList == null) {// 가져온 데이터 없으면 리턴
                        return
                    } else {
                        nextPageTokenForChannel = data?.nextPageToken
                        for (i in 1 until youtubeList.size - 1) { // 가져오고 싶은 데이터 불러오고 어뎁터에 저장하는 위치
                            var title = youtubeList.get(i).snippet.title
                            title = title.replace("&#39;", "'")
                            title = title.replace("&quot;","\"")
                            val thumbnail = youtubeList.get(i).snippet.thumbnails.high.url
                            val description = youtubeList.get(i).snippet.description
                            val videoID = youtubeList.get(i).id.videoId
                            val channelID = youtubeList.get(i).snippet.channelId
                            var url = "https://www.youtube.com/channel/$channelID"
//                            val url = data.etag
                            Log.d("title", "$title")
                            Log.d("thumbnail", "$thumbnail")
                            Log.d("description", "$description")
                            Log.d("url", "$url")
//                            Log.d("url","$url")

                            c_datas.add(
                                TubeDataModel(
                                    title = title,
                                    thumbnail = thumbnail,
                                    description = description,
                                    videoId = videoID,
                                    url = url,
                                    channelId = channelID
                                )
                            )


                        }

                    }
                    Log.d("y_datas", "$c_datas")
                    c_datas.add(TubeDataModel(" "))
                    listChannelAdapter.list = c_datas //리스트를 어댑터에 적용
                    listChannelAdapter.notifyDataSetChanged()// notity

                }
            }

            override fun onFailure(call: Call<VideoDTO>, t: Throwable) {//실패시 찍히는 로그
                Log.d("test1", "fail")
            }

        })

    }

    fun modifyItemToAddFavorite(item: TubeDataModel) {
        listAdapter.modifyItemToAddFavorite(item)
    }
}