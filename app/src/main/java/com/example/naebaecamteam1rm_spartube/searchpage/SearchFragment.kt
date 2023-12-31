package com.example.naebaecamteam1rm_spartube.searchpage

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView.OnQueryTextListener
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.naebaecamteam1rm_spartube.api.Contants
import com.example.naebaecamteam1rm_spartube.videodetailpage.VideoDetailPageActivity
import com.example.naebaecamteam1rm_spartube.data.RetrofitInstance
import com.example.naebaecamteam1rm_spartube.data.TubeDataModel
import com.example.naebaecamteam1rm_spartube.data.VideoDTO
import com.example.naebaecamteam1rm_spartube.databinding.FragmentSearchBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SearchFragment : Fragment() {
    private var _binding: FragmentSearchBinding? = null
    private val binding get() = _binding!!

    private val tag = "SearchFragment"
    private var MAX_RESULTS = 20 // 받아올 유튜브 리스트의 최대값
    private lateinit var mContext: Context
    private lateinit var adapter: SearchAdapter
    private val youDatas: ArrayList<TubeDataModel> = ArrayList() // 출력 데이터를 담을 배열
    private var nextPageToken: String? = null
    private var currentNextPageToken: String? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mContext = context
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentSearchBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setSearchView()
        setRv()
        updateBtn()
        binding.ivRefresh.setOnClickListener { updateBtn() }
    }

    //키워드 버튼을 눌렀을 때 각 키워드로 검색
    private fun updateBtn() {
        val btnRandomWord = searchWordList.shuffled().take(3)
        val firstBtn = binding.btnRandomFirst
        val secondBtn = binding.btnRandomSecond
        val thirdBtn = binding.btnRandomThird
        firstBtn.text = btnRandomWord.getOrElse(0) { "" }
        secondBtn.text = btnRandomWord.getOrElse(1) { "" }
        thirdBtn.text = btnRandomWord.getOrElse(2) { "" }

        firstBtn.setOnClickListener {
            adapter.clearItem()
            settest(firstBtn.text.toString())
            infiniteScroll(firstBtn.text.toString())
        }
        secondBtn.setOnClickListener {
            adapter.clearItem()
            settest(secondBtn.text.toString())
            infiniteScroll(secondBtn.text.toString())
        }
        thirdBtn.setOnClickListener {
            adapter.clearItem()
            settest(thirdBtn.text.toString())
            infiniteScroll(thirdBtn.text.toString())
        }
    }

    //서치뷰
    fun setSearchView() {
        binding.svSearch.setOnQueryTextListener(object : OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                adapter.clearItem()
                settest(query.toString())

                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }
        })
        binding.svSearch.setOnSearchClickListener {
            binding.tvSearch.visibility = View.INVISIBLE
        }
        binding.svSearch.setOnCloseListener {
            binding.tvSearch.visibility = View.VISIBLE
            false
        }

    }

    //리사이클러뷰
    fun setRv() {
        adapter = SearchAdapter(mContext)
        binding.rvSearchResult.adapter = adapter
        binding.rvSearchResult.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)

        adapter.itemClick = object : SearchAdapter.ItemClick {
            override fun onClick(view: View, tubeData: TubeDataModel) {
                startActivity(VideoDetailPageActivity.VideoDetailPageNewIntent(context, tubeData))
            }
        }
        infiniteScroll(binding.rvSearchResult.toString())

    }

    fun settest(search: String) = with(binding) {
        CoroutineScope(Dispatchers.IO).launch {
            RetrofitInstance.api.getList(
                Contants.MY_KEY,
                "snippet",
                "아시안게임 $search",
                "videop",
                MAX_RESULTS
            )?.enqueue(object :
                Callback<VideoDTO> {
                override fun onResponse(call: Call<VideoDTO>, response: Response<VideoDTO>) {
                    if (response.isSuccessful) {
                        Log.d("test", "Response")
                        val data = response.body()
                        val youtubeList = data?.items
                        if (youtubeList == null) {
                            return
                        } else {
                            nextPageToken = data?.nextPageToken
                            for (i in youtubeList.indices) {
                                var title = youtubeList.get(i).snippet.title
                                title = title.replace("&#39;", "'")
                                title = title.replace("&quot;", "\"")
                                val channelId = youtubeList.get(i).snippet.channelId
                                val description = youtubeList.get(i).snippet.description
                                val thumbnail = youtubeList.get(i).snippet.thumbnails.high.url
                                val channeltitle = youtubeList.get(i).snippet.channelTitle
                                val videoID = youtubeList.get(i).id.videoId
                                var url = "https://www.youtube.com/watch?v=" + videoID
                                Log.d("title", "$title")
                                Log.d("url", "$thumbnail")
                                youDatas.add(
                                    TubeDataModel(
                                        title = title,
                                        thumbnail = thumbnail,
                                        description = description,
                                        channelName = channeltitle,
                                        url = url,
                                        channelId = channelId
                                    )
                                )
                                Log.d("y_datas", "$youDatas")
                                adapter.items = youDatas
                                adapter.notifyDataSetChanged()
                            }
                        }
                    }

                }

                override fun onFailure(call: Call<VideoDTO>, t: Throwable) {
                    Log.d("test1", "fail")
                }

            })
        }

    }

    fun setNextTest(search: String) = with(binding) {
        CoroutineScope(Dispatchers.IO).launch {
            RetrofitInstance.api.getNextList(
                Contants.MY_KEY,
                "snippet",
                search,
                "video",
                nextPageToken,
                MAX_RESULTS
            )?.enqueue(object :
                Callback<VideoDTO> {
                override fun onResponse(call: Call<VideoDTO>, response: Response<VideoDTO>) {
                    if (response.isSuccessful) {
                        Log.d("test", "Response")
                        val data = response.body()
                        val youtubeList = data?.items
                        if (youtubeList == null) {
                            return
                        } else {
                            nextPageToken = data?.nextPageToken
                            for (i in youtubeList.indices) {
                                var title = youtubeList.get(i).snippet.title
                                title = title.replace("&#39;", "'")
                                title = title.replace("&quot;", "\"")
                                val channelId = youtubeList.get(i).snippet.channelId
                                val description = youtubeList.get(i).snippet.description
                                val thumbnail = youtubeList.get(i).snippet.thumbnails.high.url
                                val channeltitle = youtubeList.get(i).snippet.channelTitle
                                val videoID = youtubeList.get(i).id.videoId
                                var url = "https://www.youtube.com/watch?v=" + videoID
                                Log.d("title", "$title")
                                Log.d("url", "$thumbnail")
                                youDatas.add(
                                    TubeDataModel(
                                        title = title,
                                        thumbnail = thumbnail,
                                        description = description,
                                        channelName = channeltitle,
                                        url = url,
                                        channelId = channelId
                                    )
                                )
                                Log.d("y_datas", "$youDatas")
                                adapter.items = youDatas
                                adapter.notifyDataSetChanged()
                            }
                        }
                    }

                }

                override fun onFailure(call: Call<VideoDTO>, t: Throwable) {
                    Log.d("test1", "fail")
                }

            })
        }

    }

    private fun infiniteScroll(search: String) {
        binding.rvSearchResult.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                val lastVisibleItemPosition =
                    (binding.rvSearchResult.layoutManager as LinearLayoutManager?)!!.findLastCompletelyVisibleItemPosition()
                val itemCount = adapter!!.itemCount - 1
                if (!binding.rvSearchResult.canScrollVertically(1) && lastVisibleItemPosition == itemCount) {
                    Log.d("endScroll", "endScroll")
                    setNextTest(search)

                }
            }
        })
    }

    fun modifyItemToAddFavorite(item: TubeDataModel) {
        adapter.modifyItemToAddFavorite(item)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}

