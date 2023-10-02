package com.example.naebaecamteam1rm_spartube.playlistpage

import com.example.naebaecamteam1rm_spartube.data.TubeDataModel

data class PlayListModel(
    val title: String?,
    val thumbnail: String?,
    val description: String?,
)

fun PlayListModel.TubeDataModel(): TubeDataModel {
    return TubeDataModel(
        title = title,
        thumbnail = thumbnail,
        description = description,

    )
}
