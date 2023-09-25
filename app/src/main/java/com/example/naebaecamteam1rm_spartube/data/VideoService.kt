package com.example.naebaecamteam1rm_spartube.data

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface VideoService {
    @GET("/youtube/v3/search")
    fun getList(
        @Query("key") key: String?,
        @Query("part") part: String?,
        @Query("q") q: String?,
        @Query("type") type: String?,
        @Query("maxResults") maxResults: Int
    ): Call<VideoDTO?>?

    companion object {
        const val YOUTUBE_URL = "https://www.googleapis.com"
    }
}