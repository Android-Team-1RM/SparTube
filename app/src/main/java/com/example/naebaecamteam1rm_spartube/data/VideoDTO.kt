package com.example.naebaecamteam1rm_spartube.data

import kotlinx.serialization.*
import kotlinx.serialization.json.*
import kotlinx.serialization.descriptors.*
import kotlinx.serialization.encoding.*

@Serializable
data class VideoDTO (
    val kind: String,
    val etag: String,
    val items: List<Item>,
    val nextPageToken: String,
    val pageInfo: PageInfo
)

@Serializable
data class Item (
    val kind: String,
    val etag: String,
    val id: String,
    val snippet: Snippet
)

@Serializable
data class Snippet (
    val publishedAt: String,

    @SerialName("channelId")
    val channelID: String,

    val title: String,
    val description: String,
    val thumbnails: Thumbnails,
    val channelTitle: String,
    val tags: List<String>,

    @SerialName("categoryId")
    val categoryID: String,

    val liveBroadcastContent: String,
    val localized: Localized,
    val defaultAudioLanguage: String
)

@Serializable
data class Localized (
    val title: String,
    val description: String
)

@Serializable
data class Thumbnails (
    val default: Default,
    val medium: Default,
    val high: Default,
    val standard: Default,
    val maxres: Default
)

@Serializable
data class Default (
    val url: String,
    val width: Long,
    val height: Long
)

@Serializable
data class PageInfo (
    val totalResults: Long,
    val resultsPerPage: Long
)



