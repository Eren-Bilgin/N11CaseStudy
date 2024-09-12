package com.yourcompany.n11casestudy.data.model

import com.google.gson.annotations.SerializedName

data class UserDetail(
    val login: String?,
    val id: Long?,
    val url: String?,
    val type: String?,
    val name: String?,
    val company: String?,
    val blog: String?,
    val location: String?,
    val email: String?,
    val hireable: Boolean?,
    val bio: String?,
    val followers: Long?,
    val following: Long?,
    @SerializedName("node_id") val nodeId: String?,
    @SerializedName("avatar_url") val avatarUrl: String?,
    @SerializedName("gravatar_id") val gravatarId: String?,
    @SerializedName("html_url") val htmlUrl: String?,
    @SerializedName("followers_url") val followersUrl: String?,
    @SerializedName("following_url") val followingUrl: String?,
    @SerializedName("gists_url") val gistsUrl: String?,
    @SerializedName("starred_url") val starredUrl: String?,
    @SerializedName("subscriptions_url") val subscriptionsUrl: String?,
    @SerializedName("organizations_url") val organizationsUrl: String?,
    @SerializedName("repos_url") val reposUrl: String?,
    @SerializedName("events_url") val eventsUrl: String?,
    @SerializedName("received_events_url") val receivedEventsUrl: String?,
    @SerializedName("site_admin") val siteAdmin: Boolean?,
    @SerializedName("twitter_username") val twitterUsername: String?,
    @SerializedName("public_repos") val publicRepos: Long?,
    @SerializedName("public_gists") val publicGists: Long?,
    @SerializedName("created_at") val createdAt: String?,
    @SerializedName("updated_at") val updatedAt: String?
)