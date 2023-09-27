package com.example.naebaecamteam1rm_spartube.mypage

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.naebaecamteam1rm_spartube.Utils

class MyPageViewModel:ViewModel() {
    private val _list: MutableLiveData<List<MyPageModel>> = MutableLiveData()
    val list: LiveData<List<MyPageModel>> get() = _list

    init{

    }
    fun getLikeItems(context:Context){
        _list.value = Utils.getPrefBookmarkItems(context)
    }
    fun addItem(item:MyPageModel?){//라이브데이터에 아이템 추가하는 기능
        if(item == null) return
        val currentList = list.value.orEmpty().toMutableList()
        _list.value = currentList.apply{
            add(
                item
            )
        }
    }
    fun removeItem(item: MyPageModel?){//라이브데이터에서 아이템 삭제하는 기능
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