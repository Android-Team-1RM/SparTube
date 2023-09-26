package com.example.naebaecamteam1rm_spartube.main

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.naebaecamteam1rm_spartube.PlaylistFragment
import com.example.naebaecamteam1rm_spartube.home.HomeFragment
import com.example.naebaecamteam1rm_spartube.mypage.MyPageFragment
import com.example.naebaecamteam1rm_spartube.search.SearchFragment

class ViewPagerAdapter(fragmentActivity: FragmentActivity) :
    FragmentStateAdapter(fragmentActivity) {

    // 1. ViewPager2에 연결할 Fragment 들을 생성
    val fragmentList = listOf<Fragment>(HomeFragment(), SearchFragment(), PlaylistFragment(),
        MyPageFragment()
    )


    // 2. ViesPager2에서 노출시킬 Fragment 의 갯수 설정
    override fun getItemCount(): Int {
        return fragmentList.size
    }

    // 3. ViewPager2의 각 페이지에서 노출할 Fragment 설정
    override fun createFragment(position: Int): Fragment {
        return fragmentList[position]
    }
    fun getMyPageFragment():MyPageFragment{
        return fragmentList[3] as MyPageFragment
    }
    fun getHomeFragment():HomeFragment{
        return fragmentList[0] as HomeFragment
    }
}