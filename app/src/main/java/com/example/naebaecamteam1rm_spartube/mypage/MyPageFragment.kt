package com.example.naebaecamteam1rm_spartube.mypage

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.naebaecamteam1rm_spartube.databinding.FragmentMyPageBinding

class MyPageFragment: Fragment() {
    companion object{
        fun newInstance() = MyPageFragment
    }
    private var _binding : FragmentMyPageBinding? =null
    private val binding get() = _binding!!
    private val listAdapter by lazy{
        MyPageAdapter()
    }
    private val viewModel: MyPageViewModel by lazy{
        ViewModelProvider(this)[MyPageViewModel::class.java]
    }
    private lateinit var gridmanager:StaggeredGridLayoutManager
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMyPageBinding.inflate(inflater,container,false)
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
    private fun initView() = with(binding){
        gridmanager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        recyclerview.layoutManager =gridmanager
        recyclerview.adapter = listAdapter

    }
    private fun initViewModel(){
        with(viewModel){
            list.observe(viewLifecycleOwner){
                listAdapter.submitList(it)
            }
        }
    }

}