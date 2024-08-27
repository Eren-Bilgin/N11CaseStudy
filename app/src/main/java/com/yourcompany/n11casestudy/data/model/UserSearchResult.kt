package com.yourcompany.n11casestudy.data.model

import com.google.gson.annotations.SerializedName

data class UserSearchResult(
    val items: List<User>,
    @SerializedName("total_count") val totalCount: Int,
    @SerializedName("incomplete_results") val incompleteResults: Boolean
)