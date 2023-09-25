package com.example.naebaecamteam1rm_spartube.data

import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {
    val api: VideoService
        get() = retrofit.create(VideoService::class.java)
    private val retrofit:Retrofit
        private get(){
            val gson = GsonBuilder().setLenient().create()

            return Retrofit.Builder()
                .baseUrl(VideoService.YOUTUBE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build()
        }
}