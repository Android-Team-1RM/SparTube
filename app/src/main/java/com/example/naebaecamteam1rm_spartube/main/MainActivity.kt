package com.example.naebaecamteam1rm_spartube.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.naebaecamteam1rm_spartube.databinding.ActivityMainBinding
import com.google.android.material.tabs.TabLayoutMediator

class MainActivity : AppCompatActivity() {
    private val binding by lazy { ActivityMainBinding. inflate(layoutInflater) }

    private val viewPagerAdapter by lazy {
        ViewPagerAdapter(this@MainActivity)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)


        // ViewPager2의 adapter 설정

        binding.viewPagerMain.adapter = viewPagerAdapter

        binding.viewPagerMain.run{
            isUserInputEnabled= false
        }


        // TabLayout과 ViewPager2를 연결
        // 1. 탭메뉴의 이름을 리스트로 생성
        val tabTitles = listOf<String>("홈", "검색", "재생목록", "마이페이지")

        // 2. TabLayout과 ViewPager2를 연결하고, TabItem의 메뉴명을 설정
        TabLayoutMediator(binding.tabLayoutMain, binding.viewPagerMain, {tab, position -> tab.text = tabTitles[position]}).attach()


    }



}