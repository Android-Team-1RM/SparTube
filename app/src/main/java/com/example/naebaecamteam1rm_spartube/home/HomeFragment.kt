package com.example.naebaecamteam1rm_spartube.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import com.example.naebaecamteam1rm_spartube.R
import com.example.naebaecamteam1rm_spartube.data.TestData
import com.example.naebaecamteam1rm_spartube.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val view = binding.root

        val dataList = mutableListOf<TestData>()
        dataList.add(TestData(R.drawable.mypage_img_profile, "제목1", false))
        dataList.add(TestData(R.drawable.mypage_img_profile, "제목2", false))
        dataList.add(TestData(R.drawable.mypage_img_profile, "제목3", false))
        dataList.add(TestData(R.drawable.mypage_img_profile, "제목4", false))
        dataList.add(TestData(R.drawable.mypage_img_profile, "제목5", false))
        dataList.add(TestData(R.drawable.mypage_img_profile, "제목6", false))
        dataList.add(TestData(R.drawable.mypage_img_profile, "제목7", false))
        dataList.add(TestData(R.drawable.mypage_img_profile, "제목8", false))
        dataList.add(TestData(R.drawable.mypage_img_profile, "제목10", false))
        dataList.add(TestData(R.drawable.mypage_img_profile, "제목11", false))
        dataList.add(TestData(R.drawable.mypage_img_profile, "제목12", false))

        binding.recycler.adapter = HomeAdapter(dataList)

        val adapter = HomeAdapter(dataList)
        binding.recycler.adapter = adapter
        binding.recycler.layoutManager = GridLayoutManager(requireContext(),2)

        adapter.itemClick = object : HomeAdapter.ItemClick {
            override fun onClick(view: View, position: Int) {
            }
        }

        return view
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}