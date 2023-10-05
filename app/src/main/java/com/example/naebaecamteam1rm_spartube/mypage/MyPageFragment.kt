package com.example.naebaecamteam1rm_spartube.mypage

import android.app.AlertDialog
import android.content.Context
import android.icu.util.UniversalTimeScale
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.naebaecamteam1rm_spartube.Utils
import com.example.naebaecamteam1rm_spartube.UtilsImpl
import com.example.naebaecamteam1rm_spartube.data.TubeDataModel
import com.example.naebaecamteam1rm_spartube.databinding.FragmentMyPageBinding
import com.example.naebaecamteam1rm_spartube.main.MainActivity
import com.example.naebaecamteam1rm_spartube.videodetailpage.VideoDetailPageActivity

class MyPageFragment : Fragment() {
    companion object {
        fun newInstance() = MyPageFragment
    }

    private var _binding: FragmentMyPageBinding? = null
    private val binding get() = _binding!!
    private val mainActivity = MainActivity.newInstence()
    private val utils:Utils by lazy{
        UtilsImpl(requireContext())
    }


    private val viewModel: MyPageViewModel by lazy {//튜터님 보여드리기
        ViewModelProvider(
            this,
            MyPageModelFactory(requireContext())
        )[MyPageViewModel::class.java]
    }

    private val listAdapter by lazy {
        MyPageAdapter(requireContext(),
            onClickItem = { item ->
                startActivity(
                    VideoDetailPageActivity.VideoDetailPageNewIntent(
                        requireContext(),
                        item.toTubeData()
                    )
                )
            },
            onClickFav = { item ->
                Toast.makeText(requireContext(), "좋아요 해제", Toast.LENGTH_SHORT).show()
                mainActivity!!.removeFavoriteToMyPage(item)
                mainActivity.modifyFavoriteToHome(item.toTubeData())
                mainActivity.modifyFavoriteToSearch(item.toTubeData())
                utils.deletePrefItem(item.toTubeData().thumbnail!!)
            }
//            onLongClickItem = {item ->
//                AlertDialog.Builder(mContext).apply {
//                    setMessage("즐겨찾기를 해제하시겠습니까?")
//                    setPositiveButton(
//                        "삭제"
//                    ){_,_ ->
//                        mainActivity!!.removeFavoriteToMyPage(item)
//                        mainActivity.modifyFavoriteToHome(item.toTubeData())
//                        Utils.deletePrefItem(mContext, item.toTubeData().thumbnail!!)
//                    }
//                    setNegativeButton(
//                        "취소"
//                    ){_,_ ->}
//                }.create().show()
//                removeItem(item)
//            }
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMyPageBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        initViewModel()
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }

    private fun initView() = with(binding) {
//        gridmanager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
//        recyclerview.layoutManager =gridmanager
        recyclerview.adapter = listAdapter
//        recyclerview.addOnScrollListener(object : RecyclerView.OnScrollListener() {
//            //리사이클러뷰가 스크롤이 될 때 실행
//            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
//                super.onScrolled(recyclerView, dx, dy)
//                val lastVisibleItemPosition =
//                    (recyclerView.layoutManager as LinearLayoutManager?)!!.findLastCompletelyVisibleItemPosition()
//                val itemCount = listAdapter!!.itemCount - 1
//                Log.d("test5", "$lastVisibleItemPosition")
//                Log.d("test5", "$itemCount")
//
//                if (lastVisibleItemPosition == itemCount) {
//                    val item = viewModel.getViewModelList()
//                    addItems(item)
//                }
//
//
//            }
//        })
    }

    private fun initViewModel() {
        with(viewModel) {
            viewModel.getLikeItems()
            list.observe(viewLifecycleOwner) {
                listAdapter.submitList(it)
            }
        }
    }

    fun addItem(item: MyPageModel?) {
        viewModel.addItem(item)
    }

    fun removeItem(item: MyPageModel?) {
        viewModel.removeItem(item)
    }

    fun addItems(items: MutableList<MyPageModel>) {
        viewModel.addItems(items)
    }

}