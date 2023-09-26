package com.example.naebaecamteam1rm_spartube.mypage

import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MyPageViewModel:ViewModel() {
    private val _list: MutableLiveData<List<MyPageModel>> = MutableLiveData()
    val list: LiveData<List<MyPageModel>> get() = _list

    init{
        _list.value = mutableListOf<MyPageModel>().apply{
            for(i in 0 until 3){
                add(
                    MyPageModel(
                        "test $i",
                        "test1 $i"
                    )
                )
            }

        }
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
    fun removeItem(position:Int?){//라이브데이터에서 아이템 삭제하는 기능
        if(position == null || position<0) return
        val currentList = list.value.orEmpty().toMutableList()
        currentList.removeAt(position)
        _list.value = currentList

    }
    fun modifyItem(item:MyPageModel?){
        if(item== null) return
        fun findIndex(item:MyPageModel?):Int{
            val currentList = list.value.orEmpty().toMutableList()
            val findPosition = currentList.find{
                it.thumbnails == item?.thumbnails
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