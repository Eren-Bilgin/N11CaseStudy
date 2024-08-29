package com.yourcompany.n11casestudy.data.network

import com.yourcompany.n11casestudy.data.model.UserSearchResult
import retrofit2.http.GET
import retrofit2.http.Query

interface UserService {
    @GET("search/users")
    suspend fun searchUsers(
        @Query("q") query: String
    ): UserSearchResult
}