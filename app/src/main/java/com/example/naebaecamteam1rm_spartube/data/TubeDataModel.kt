package com.example.naebaecamteam1rm_spartube.data


data class TubeDataModel(
    val title: String?, //제목
    val thumbnail: String?, // 썸네일 이미지 URL
    val description: String?,
    val url: String? = null,
    var isLike: Boolean = false,
    )
