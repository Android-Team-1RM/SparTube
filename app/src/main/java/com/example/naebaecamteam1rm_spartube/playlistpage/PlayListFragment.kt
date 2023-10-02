package com.example.naebaecamteam1rm_spartube.playlistpage

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.naebaecamteam1rm_spartube.data.RetrofitInstance
import com.example.naebaecamteam1rm_spartube.data.TubeDataModel
import com.example.naebaecamteam1rm_spartube.data.VideoDTO
import com.example.naebaecamteam1rm_spartube.databinding.FragmentPlaylistBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class PlaylistFragment : Fragment() {

    companion object{
        fun newInstance() = PlaylistFragment
    }
    private var _binding : FragmentPlaylistBinding? =null
    private val binding get() = _binding!!
    private var Q
            : String? = null
    private val MAX_RESULTS = 20
    private val t_datas: ArrayList<TubeDataModel> = ArrayList()
    private lateinit var playlistRecyclerView: RecyclerView
    private lateinit var VideosRecyclerView: RecyclerView
    private lateinit var mContext: Context
    private val listAdapter by lazy {
        PlayListAdapter(mContext)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mContext = context
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentPlaylistBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initView()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun initView() = with(binding) {
        setPlayList()
        setListVideo()

        // RecyclerView 초기화
        playlistRecyclerView = binding.playlistRecyclerView
        playlistRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        val playlistAdapter = PlayListAdapter() // 적절한 어댑터 클래스로 대체
        playlistRecyclerView.adapter = playlistAdapter

        // Videos RecyclerView 초기화
        VideosRecyclerView = binding.VideolistRecyclerView
        VideosRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        val playlistVideosAdapter = VideoListAdapter() // 적절한 어댑터 클래스로 대체
        VideosRecyclerView.adapter = playlistVideosAdapter

        // 현재 뷰 반환
       // return view
    }

    fun setPlayList() = with(binding) {

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
                            val thumbnail = youtubeList.get(i).snippet.thumbnails.default.url
                            val description = youtubeList.get(i).snippet.description

                            Log.d("title", "$title")
                            Log.d("thumbnail", "$thumbnail")
                            Log.d("description", "$description")

                            t_datas.add(
                                TubeDataModel(

                                    title = title,
                                    thumbnail = thumbnail,
                                    description = description,

                                    )
                            )
                            Log.d("y_datas", "$t_datas")
                            listAdapter.list = t_datas //리스트를 어댑터에 적용
                            listAdapter.notifyDataSetChanged()// 어댑터에 데이터 변경 알림

                        }
                    }

                }
            }

            override fun onFailure(call: Call<VideoDTO>, t: Throwable) {//실패시 찍히는 로그
                Log.d("test1", "fail")
            }

        })

    }

    fun setListVideo() = with(binding) {

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
                            val thumbnail = youtubeList.get(i).snippet.thumbnails.default.url
                            val description = youtubeList.get(i).snippet.description

                            Log.d("title", "$title")
                            Log.d("thumbnail", "$thumbnail")
                            Log.d("description", "$description")

                            t_datas.add(
                                TubeDataModel(

                                    title = title,
                                    thumbnail = thumbnail,
                                    description = description,

                                    )
                            )
                            Log.d("y_datas", "$t_datas")
                            listAdapter.list = t_datas //리스트를 어댑터에 적용
                            listAdapter.notifyDataSetChanged()// 어댑터에 데이터 변경 알림

                        }
                    }

                }
            }

            override fun onFailure(call: Call<VideoDTO>, t: Throwable) {//실패시 찍히는 로그
                Log.d("test1", "fail")
            }

        })
    }
}