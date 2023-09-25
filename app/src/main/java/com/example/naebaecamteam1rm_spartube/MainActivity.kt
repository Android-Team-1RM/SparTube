package com.example.naebaecamteam1rm_spartube

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.naebaecamteam1rm_spartube.databinding.ActivityMainBinding
import com.google.android.material.tabs.TabLayoutMediator

class MainActivity : AppCompatActivity() {
    private val binding by lazy { ActivityMainBinding. inflate(layoutInflater) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        // FragmentStateAdapter 생성
        val viewpagerAdapter = ViewPagerAdapter(this)

        // ViewPager2의 adapter 설정
        binding.viewPagerMain.adapter = viewpagerAdapter


        // TabLayout과 ViewPager2를 연결
        // 1. 탭메뉴의 이름을 리스트로 생성
        val tabTitles = listOf<String>("첫번째", "두번째", "세번째")

        // 2. TabLayout과 ViewPager2를 연결하고, TabItem의 메뉴명을 설정
        TabLayoutMediator(binding.tabLayoutMain, binding.viewPagerMain, {tab, position -> tab.text = tabTitles[position]}).attach()
    }
}