package com.example.naebaecamteam1rm_spartube.data

data class TubeDataModel(
    val title: String?, //제목
    val thumbnail: String?, // 썸네일 이미지 URL
    val description: String?,
    val url: String? = null,
    val isLike: Boolean = false,
    )
fun TubeDataModel.toMyPageModel():MyPageModel{//MyPageModel로 모델 변환함수 작성
    return MyPageModel(
        title = title,
        thumbnail = thumbnail,
        description = description,
        url = url,
        isLike =  isLike
    )
}