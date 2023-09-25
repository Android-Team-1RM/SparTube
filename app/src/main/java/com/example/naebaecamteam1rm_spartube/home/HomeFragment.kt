package com.example.naebaecamteam1rm_spartube.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import com.example.naebaecamteam1rm_spartube.R
import com.example.naebaecamteam1rm_spartube.data.TestData
import com.example.naebaecamteam1rm_spartube.data.VideoDTO
import com.example.naebaecamteam1rm_spartube.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private val MY_KEY = "YOUR_KEY" // API KEY
    private var Q // 유튜브 검색값
            : String? = null
    private val MAX_RESULTS = 20 // 받아올 유튜브 리스트의 최대값
    private val y_datas: List<VideoDTO> = ArrayList() // 출력 데이터를 담을 배열

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val view = binding.root

        Q = "아시안 게임"



        return view
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

class YoutubeActivity : AppCompatActivity() {

    protected fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_youtube)


        // 리사이클러뷰 사이즈 지정 및 레이아웃 설정
        y_recyclerView = findViewById(R.id.youtube_recyclerView)
        y_recyclerView.setHasFixedSize(true)
        y_recyclerView.addItemDecoration(
            DividerItemDecoration(
                this@YoutubeActivity,
                DividerItemDecoration.VERTICAL
            )
        ) // 구분선 지정
        y_recyclerView.setLayoutManager(LinearLayoutManager(this@YoutubeActivity))
        val gson: Gson = GsonBuilder().setLenient().create() // JSON 응답을 객체로 변환하기 위해 필요
        val retrofit: Retrofit = Builder()
            .baseUrl(YoutubeService.YOUTUBE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson)) // gson 부여
            .build()
        val retrofitAPI: YoutubeService =
            retrofit.create(YoutubeService::class.java) // 유튜브 인터페이스와 연결
        retrofitAPI.getList(MY_KEY, "snippet", Q, "video", MAX_RESULTS)
            .enqueue(object : Callback<YoutubeAPI?>() {
                // API KEY, snippet 영역을 받아옴, 검색값, 비디오만, MAX_RESULT 값 만큼 유튜브 서버에 요청
                fun onResponse(call: Call<YoutubeAPI?>?, response: Response<YoutubeAPI?>) {
                    if (response.isSuccessful()) { // 성공적으로 받아왔을 때
                        Log.d("YOUTUBE", "Response")
                        val data: YoutubeAPI = response.body() // 받아온 데이터를 변수에 저장
                        val youtubeList: List<ItemsBean> = data.getItems() // 변수에서 아이템 리스트들을 받아온다.
                        for (i in 0 until youtubeList.size()) {
                            val title: String = youtubeList[i].getSnippet().getTitle() // 타이틀 파싱
                            val url: String = youtubeList[i].getSnippet().getThumbnails()
                                .getMedium().getUrl() // 썸네일 url 파싱
                            y_datas.add(Youtube(title, url)) // 필요한 데이터만 추가
                            y_adapter = YoutubeAdapter(this@YoutubeActivity, y_datas) // 어댑터 설정
                            y_recyclerView.setAdapter(y_adapter)
                        }
                    }
                }

                fun onFailure(call: Call<YoutubeAPI?>?, t: Throwable) {
                    Log.d("YOUTUBE", "Error")
                    t.printStackTrace()
                }
            })
    }
}