package com.example.naebaecamteam1rm_spartube.mypage

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.naebaecamteam1rm_spartube.UtilsImpl

class MyPageViewModel(private val Utils: UtilsImpl) : ViewModel() {
    private val _list: MutableLiveData<List<MyPageModel>> = MutableLiveData()
    val list: LiveData<List<MyPageModel>> get() = _list

    init {

    }

    fun getLikeItems() {
        _list.value = Utils.getPrefBookmarkItems()
    }

    fun getViewModelList(): MutableList<MyPageModel> {
        val currentList = list.value.orEmpty().toMutableList()
        return currentList
    }

    fun addItem(item: MyPageModel?) {//라이브데이터에 아이템 추가하는 기능
        if (item == null) return
        val currentList = list.value.orEmpty().toMutableList()
        _list.value = currentList.apply {
            add(
                item
            )
        }
    }

    fun addItems(addlist: MutableList<MyPageModel>) {
        val curentList = list.value.orEmpty().toMutableList()
        Log.d("ifaddList", "$_list.value")
        _list.value = curentList.apply {
            addAll(addlist)
        }
    }

    fun removeItem(item: MyPageModel?) {//라이브데이터에서 아이템 삭제하는 기능
        if (item == null) return
        fun findIndex(item: MyPageModel?): Int {
            val currentList = list.value.orEmpty().toMutableList()
            val findPosition = currentList.find {
                it.thumbnail == item?.thumbnail
            }
            return currentList.indexOf(findPosition)
        }

        val findPosition = findIndex(item)
        if (findPosition < 0) {
            return
        }
        val currentList = list.value.orEmpty().toMutableList()
        currentList.removeAt(findPosition)
        _list.value = currentList

    }

    fun modifyItem(item: MyPageModel?) {
        if (item == null) return
        fun findIndex(item: MyPageModel?): Int {
            val currentList = list.value.orEmpty().toMutableList()
            val findPosition = currentList.find {
                it.thumbnail == item?.thumbnail
            }
            return currentList.indexOf(findPosition)
        }

        val findPosition = findIndex(item)
        if (findPosition < 0) {
            return
        }
        val currentList = list.value.orEmpty().toMutableList()
        currentList[findPosition] = item
        _list.value = currentList
    }

}
//팩토리를 만들고, Context를 파라미터로 받고 여기서 Sharedpreference instance 추가

class MyPageModelFactory(private val context: Context) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MyPageViewModel::class.java)) {
            return MyPageViewModel(UtilsImpl(context)) as T
        } else {
            throw IllegalArgumentException("Not found ViewModel class.")
        }

    }
}
