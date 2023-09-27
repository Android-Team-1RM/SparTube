package com.example.naebaecamteam1rm_spartube

import android.app.Activity
import android.content.Context
import com.example.naebaecamteam1rm_spartube.mypage.MyPageModel
import com.google.gson.GsonBuilder

object Utils {
    fun addPrefItem(context: Context, item: MyPageModel) {
        val prefs = context.getSharedPreferences("pref", Activity.MODE_PRIVATE)
        val editor = prefs.edit()
        val gson = GsonBuilder().create()
        editor.putString(item.thumbnail, gson.toJson(item))
        editor.apply()
    }
    fun deletePrefItem(context: Context, thumbnail: String) {
        val prefs = context.getSharedPreferences("pref", Activity.MODE_PRIVATE)
        val editor = prefs.edit()
        editor.remove(thumbnail)
        editor.apply()
    }
    fun getPrefBookmarkItems(context: Context): ArrayList<MyPageModel> {
        val prefs = context.getSharedPreferences("pref", Activity.MODE_PRIVATE)
        val allEntries: Map<String, *> = prefs.all
        val bookmarkItems = ArrayList<MyPageModel>()
        val gson = GsonBuilder().create()
        for ((key, value) in allEntries) {
            val item = gson.fromJson(value as String, MyPageModel::class.java)
            bookmarkItems.add(item)
        }
        return bookmarkItems
    }
}