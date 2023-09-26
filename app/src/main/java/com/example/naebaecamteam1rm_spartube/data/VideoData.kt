package com.example.naebaecamteam1rm_spartube.data

import de.hdodenhof.circleimageview.BuildConfig
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object VideoData {
    private const val VIDEO_BASE_URL = "https://www.googleapis.com"


    private fun createOkHttpClient(): OkHttpClient {
        val interceptor = HttpLoggingInterceptor()

        if (BuildConfig.DEBUG)
            interceptor.level = HttpLoggingInterceptor.Level.BODY
        else
            interceptor.level = HttpLoggingInterceptor.Level.NONE

        return OkHttpClient.Builder()
            .connectTimeout(20, TimeUnit.SECONDS)
            .readTimeout(20, TimeUnit.SECONDS)
            .writeTimeout(20, TimeUnit.SECONDS)
            .addNetworkInterceptor(interceptor)
            .build()
    }

    private val videoRetrofit = Retrofit.Builder()
        .baseUrl(VIDEO_BASE_URL).addConverterFactory(GsonConverterFactory.create()).client(
            createOkHttpClient()
        ).build()

    val videoNetWork: VideoService = videoRetrofit.create(VideoService::class.java)
}