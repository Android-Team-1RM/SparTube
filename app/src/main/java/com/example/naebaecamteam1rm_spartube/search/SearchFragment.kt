package com.example.naebaecamteam1rm_spartube.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.naebaecamteam1rm_spartube.databinding.FragmentSearchBinding

class SearchFragment : Fragment() {
    private lateinit var binding: FragmentSearchBinding
    private val tag = "SearchFragment"


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
    }


}