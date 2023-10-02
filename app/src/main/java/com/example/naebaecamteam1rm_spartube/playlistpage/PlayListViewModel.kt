package com.example.naebaecamteam1rm_spartube.playlistpage

import android.graphics.Insets.add
import androidx.lifecycle.ViewModel
import com.example.naebaecamteam1rm_spartube.mypage.MyPageModel

class PlayListViewModel: ViewModel() {

    fun addItem(item: MyPageModel?) {
        if (item == null) return
        val currentList = list.value.orEmpty().toMutableList()
        _list.value = currentList.apply {
            add(
                item
            )
        }
    }

    fun removeItem(item: MyPageModel?){
        if(item== null) return
        fun findIndex(item:MyPageModel?):Int{
            val currentList = list.value.orEmpty().toMutableList()
            val findPosition = currentList.find{
                it.thumbnail == item?.thumbnail
            }
            return currentList.indexOf(findPosition)
        }
        val findPosition = findIndex(item)
        if(findPosition<0){
            return
        }
        val currentList = list.value.orEmpty().toMutableList()
        currentList.removeAt(findPosition)
        _list.value = currentList

    }

    fun removeItem(item: MyPageModel?){
        if(item== null) return
        fun findIndex(item:MyPageModel?):Int{
            val currentList = list.value.orEmpty().toMutableList()
            val findPosition = currentList.find{
                it.thumbnail == item?.thumbnail
            }
            return currentList.indexOf(findPosition)
        }
        val findPosition = findIndex(item)
        if(findPosition<0){
            return
        }
        val currentList = list.value.orEmpty().toMutableList()
        currentList.removeAt(findPosition)
        _list.value = currentList

    }
    fun modifyItem(item:MyPageModel?){
        if(item== null) return
        fun findIndex(item:MyPageModel?):Int{
            val currentList = list.value.orEmpty().toMutableList()
            val findPosition = currentList.find{
                it.thumbnail == item?.thumbnail
            }
            return currentList.indexOf(findPosition)
        }
        val findPosition = findIndex(item)
        if(findPosition<0){
            return
        }
        val currentList = list.value.orEmpty().toMutableList()
        currentList[findPosition] = item
        _list.value = currentList
    }

}