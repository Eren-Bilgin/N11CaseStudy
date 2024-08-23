package com.yourcompany.n11casestudy.model

import com.google.gson.annotations.SerializedName

data class User(
    val login: String,
    val id: Long,
    val url: String,
    val type: String,
    val score: Double,
    @SerializedName("node_id") val nodeId: String,
    @SerializedName("avatar_url") val avatarUrl: String,
    @SerializedName("gravatar_id") val gravatarId: String,
    @SerializedName("html_url") val htmlUrl: String,
    @SerializedName("followers_url") val followersUrl: String,
    @SerializedName("subscriptions_url") val subscriptionsUrl: String,
    @SerializedName("organizations_url") val organizationsUrl: String,
    @SerializedName("repos_url") val reposUrl: String,
    @SerializedName("received_events_url") val receivedEventsUrl: String,
    @SerializedName("following_url") val followingUrl: String,
    @SerializedName("gists_url") val gistsUrl: String,
    @SerializedName("starred_url") val starredUrl: String,
    @SerializedName("events_url") val eventsUrl: String,
    @SerializedName("site_admin") val siteAdmin: Boolean
)