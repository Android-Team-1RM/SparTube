package com.example.naebaecamteam1rm_spartube.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.naebaecamteam1rm_spartube.R
import com.example.naebaecamteam1rm_spartube.data.TubeDataModel
import com.example.naebaecamteam1rm_spartube.databinding.ActivityMainBinding
import com.example.naebaecamteam1rm_spartube.mypage.MyPageModel
import com.google.android.material.tabs.TabLayoutMediator

class MainActivity : AppCompatActivity() {
    init{
        instance = this
    }
    companion object{
        private var instance: MainActivity? =null
        fun newInstence(): MainActivity?{
            return instance
        }

    }
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
        binding.viewPagerMain.offscreenPageLimit = viewPagerAdapter.itemCount

        // TabLayout과 ViewPager2를 연결
        // 1. 탭메뉴의 이름을 리스트로 생성
//        val tabTitles = listOf<String>("HOME", "SEARCH", "PLAYLIST", "FAVORITE")
        val tabIcons = listOf<Int>(R.drawable.tab_item_btn_ic_home,R.drawable.tab_item_btn_ic_search,R.drawable.tab_item_btn_ic_playlist,R.drawable.tab_item_btn_ic_thumb_up)
        // 2. TabLayout과 ViewPager2를 연결하고, TabItem의 메뉴명을 설정
//        TabLayoutMediator(binding.tabLayoutMain, binding.viewPagerMain, {tab, position -> tab.text = tabTitles[position]}).attach()
        TabLayoutMediator(binding.tabLayoutMain, binding.viewPagerMain) { tab, position ->
            tab.setIcon(
                tabIcons[position]
            )
        }.attach()

        //9.27 21:43 1차 PR


    }

    fun addFavorite(item: MyPageModel?){
        val myPageFragment = viewPagerAdapter.getMyPageFragment()
        myPageFragment.addItem(item)
    }
    fun modifyFavoriteToHome(item:TubeDataModel){
        val homeFragment =viewPagerAdapter.getHomeFragment()
        homeFragment.modifyItemToAddFavorite(item)
    }
    fun removeFavoriteToMyPage(item:MyPageModel?){
        val myPageFragment = viewPagerAdapter.getMyPageFragment()
        myPageFragment.removeItem(item)
    }
    fun modifyFavoriteToSearch(item:TubeDataModel){
        val searchFragment = viewPagerAdapter.getSearchFragment()
        searchFragment.modifyItemToAddFavorite(item)
    }

//    fun addPlayList(item: TubeDataModel?){
//        val playListFragment = viewPagerAdapter.getPlayListFragment()
//        playListFragment.addItem(item)
//    }


}