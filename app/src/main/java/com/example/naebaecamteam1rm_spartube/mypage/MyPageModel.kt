package com.example.naebaecamteam1rm_spartube.mypage

import com.example.naebaecamteam1rm_spartube.data.TubeDataModel

data class MyPageModel(
    val title: String?,
    val thumbnail: String?,
    val description: String?,
    val url: String? = null,
    val isLike: Boolean = true
)

fun MyPageModel.TubeDataModel(): TubeDataModel {
    return TubeDataModel(
        title = title,
        thumbnail = thumbnail,
        description = description,
        url = url,
        isLike = isLike
    )
}
