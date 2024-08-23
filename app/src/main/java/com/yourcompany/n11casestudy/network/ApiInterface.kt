package com.yourcompany.n11casestudy.network

import com.yourcompany.n11casestudy.model.UserSearchResult
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiInterface {
    @GET("search/users")
    fun searchUsers(
        @Query("q") query: String
    ): Response<UserSearchResult>
}