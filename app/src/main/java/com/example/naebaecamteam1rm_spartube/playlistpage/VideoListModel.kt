package com.example.naebaecamteam1rm_spartube.playlistpage

import com.example.naebaecamteam1rm_spartube.data.TubeDataModel

data class VideoListModel(
    val title: String?,
    val thumbnail: String?,
    val description: String?,
)

fun VideoListModel.TubeDataModel(): TubeDataModel {
    return TubeDataModel(
        title = title,
        thumbnail = thumbnail,
        description = description,

        )
}