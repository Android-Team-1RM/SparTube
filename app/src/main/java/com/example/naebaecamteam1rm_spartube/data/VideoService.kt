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
    ): Call<VideoDTO>

    @GET("/youtube/v3/search")
    fun getNextList(
        @Query("key") key: String?,
        @Query("part") part: String?,
        @Query("q") q: String?,
        @Query("type") type: String?,
        @Query("pageToken") pageToken: String?,
        @Query("maxResults") maxResults: Int
    ): Call<VideoDTO>

    @GET("/youtube/v3/search")
    fun getShortsList(
        @Query("key") key: String?,
        @Query("part") part: String?,
        @Query("q") q: String?,
        @Query("videoDuration") videoDuration: String?,
        @Query("type") type: String?,
        @Query("maxResults") maxResults: Int,
    ): Call<VideoDTO>

    @GET("/youtube/v3/search")
    fun getNextShortsList(
        @Query("key") key: String?,
        @Query("part") part: String?,
        @Query("q") q: String?,
        @Query("videoDuration") videoDuration: String?,
        @Query("type") type: String?,
        @Query("pageToken") pageToken: String?,
        @Query("maxResults") maxResults: Int,
    ): Call<VideoDTO>

    @GET("/youtube/v3/search")
    fun getchannelList(
        @Query("key") key: String?,
        @Query("part") part: String?,
        @Query("q") q: String?,
        @Query("type") type: String?,
        //@Query("channelId") channelId: String?,
        @Query("maxResults") maxResults: Int
    ): Call<VideoDTO>

    @GET("/youtube/v3/search")
    fun getNextChannelList(
        @Query("key") key: String?,
        @Query("part") part: String?,
        @Query("q") q: String?,
        @Query("type") type: String?,
        //@Query("channelId") channelId: String?,
        @Query("pageToken") pageToken: String?,
        @Query("maxResults") maxResults: Int
    ): Call<VideoDTO>

    @GET("/youtube/v3/channels")
    fun getChannelThumbnail(
        @Query("key") key: String?,
        @Query("part") part: String?,
        //@Query("q") q: String?,
        //@Query("type") type: String?,
        @Query("id") id: String?,
//        @Query("maxResults") maxResults: Int
    ): Call<ChannelDTO>

    companion object {
        const val YOUTUBE_URL = "https://www.googleapis.com"
    }
}

// GET https://www.googleapis.com/youtube/v3/videos -> 비디오
// GET https://www.googleapis.com/youtube/v3/channels -> 채널
// GET https://www.googleapis.com/youtube/v3/search -> 검색