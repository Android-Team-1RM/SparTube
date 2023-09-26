package com.example.naebaecamteam1rm_spartube

import com.google.gson.annotations.SerializedName

data class ImageModel(
    @SerializedName("kind")
    val documents: ArrayList<Video>,
    @SerializedName("part")
    val part: String

) {
    /**
     * 이미지 검색 응답에서 단일 문서 혹은 결과를 나타내는 클래스.
     */
    data class Video(

        @SerializedName("title")
        val thumbnailUrl: String,

    )

}