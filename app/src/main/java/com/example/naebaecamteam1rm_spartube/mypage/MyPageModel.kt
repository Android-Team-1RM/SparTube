package com.example.naebaecamteam1rm_spartube.mypage

import com.example.naebaecamteam1rm_spartube.data.TubeDataModel

data class MyPageModel(
    val title: String?,
    val thumbnail: String?,
    val description: String?,
    val url: String?,
    val videoId: String?,
    val channelId: String?,
    var isLike: Boolean = true
)

fun MyPageModel.toTubeData(): TubeDataModel {
    return TubeDataModel(
        title = title,
        thumbnail = thumbnail,
        description = description,
        videoId = videoId,
        url = url,
        isLike = isLike
    )
}