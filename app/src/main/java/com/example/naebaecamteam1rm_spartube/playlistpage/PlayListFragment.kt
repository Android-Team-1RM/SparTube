package com.example.naebaecamteam1rm_spartube.playlistpage

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.naebaecamteam1rm_spartube.R
import com.example.naebaecamteam1rm_spartube.mypage.MyPageViewModel


class PlayListFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_playlist, container, false)
    }

//    fun addItem(item: TubeDataModel?) {
//
//    }


}