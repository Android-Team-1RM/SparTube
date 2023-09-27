package com.example.naebaecamteam1rm_spartube.data

import kotlinx.serialization.*
import kotlinx.serialization.json.*
import kotlinx.serialization.descriptors.*
import kotlinx.serialization.encoding.*

@Serializable
data class ChannelDTO (
    val kind: String,
    val etag: String,
    val pageInfo: ChannelPageInfo,
    val items: List<ChannelItem>
)

@Serializable
data class ChannelItem (
    val kind: String,
    val etag: String,
    val id: String,
    val snippet: ChannelSnippet
)

@Serializable
data class ChannelSnippet (
    val title: String,
    val description: String,

    @SerialName("customUrl")
    val customURL: String,

    val publishedAt: String,
    val thumbnails: ChannelThumbnails,
    val localized: ChannelLocalized,
    val country: String
)

@Serializable
data class ChannelLocalized (
    val title: String,
    val description: String
)

@Serializable
data class ChannelThumbnails (
    val default: ChannelDefault,
    val medium: ChannelDefault,
    val high: ChannelDefault
)

@Serializable
data class ChannelDefault (
    val url: String,
    val width: Long,
    val height: Long
)

@Serializable
data class ChannelPageInfo (
    val totalResults: Long,
    val resultsPerPage: Long
)

