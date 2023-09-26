package com.example.naebaecamteam1rm_spartube

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface RetrofitInterface {
    //video resource endpoint
    @GET("https://www.googleapis.com/youtube/v3/search")
     fun loadVideo(
        @Query("part") part: String = "snippet",
        @Query("key") key: String,
        @Query("maxResults") page: Int,
        @Query("q") query:String,
        @Query("type") type: String = "video"

    ): Call<ImageModel?>?
}