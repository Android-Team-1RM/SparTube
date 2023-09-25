package com.example.naebaecamteam1rm_spartube.data

import retrofit2.http.GET
import retrofit2.http.QueryMap

interface VideoService {
    @GET("/youtube/v3/videos")
    suspend fun getVideo(@QueryMap param: HashMap<String, String>): VideoDTO
}