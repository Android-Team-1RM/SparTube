package com.example.naebaecamteam1rm_spartube.data


import com.example.naebaecamteam1rm_spartube.mypage.MyPageModel

data class TubeDataModel(
    val title: String?= null, //제목
    val thumbnail: String?= null, // 썸네일 이미지 URL
    val description: String?= null,
    val videoId: String? = null,
    val url: String? = null,
    val channelId: String? = null,
    var isLike: Boolean = false,
    )
fun TubeDataModel.toMyPageModel():MyPageModel{//MyPageModel로 모델 변환함수 작성
    return MyPageModel(
        title = title,
        thumbnail = thumbnail,
        description = description,
        videoId = videoId,
        url = url,
        channelId = channelId,
        isLike =  isLike
    )
}