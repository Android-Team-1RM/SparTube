package com.example.naebaecamteam1rm_spartube.data

import kotlinx.serialization.*
import kotlinx.serialization.descriptors.*
import kotlinx.serialization.encoding.*
import kotlinx.serialization.json.*

@Serializable
data class VideoDTO(
    val kind: String,
    val etag: String,
    val nextPageToken: String,
    val regionCode: String,
    val pageInfo: PageInfo,
    val items: List<Item>
)

@Serializable
data class Item(
    val kind: String,
    val etag: String,
    val id: ID,
    val snippet: Snippet
)

@Serializable
data class ID(
    val kind: String,
    @SerialName("videoId")
    val videoId: String
)

@Serializable

data class Snippet(
    val publishedAt: String,

    @SerialName("channelId")
    val channelId: String,

    val title: String,
    val description: String,
    val thumbnails: Thumbnails,
    val channelTitle: String,
    val liveBroadcastContent: String,
    val publishTime: String
)

@Serializable
data class Thumbnails(
    val default: Default,
    val medium: Default,
    val high: Default
)

@Serializable
data class Default(
    val url: String,
    val width: Long,
    val height: Long
)

@Serializable
data class PageInfo(
    val totalResults: Long,
    val resultsPerPage: Long
)


