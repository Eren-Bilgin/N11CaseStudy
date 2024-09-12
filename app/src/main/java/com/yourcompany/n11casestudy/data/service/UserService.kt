package com.yourcompany.n11casestudy.data.service

import com.yourcompany.n11casestudy.data.model.UserDetail
import com.yourcompany.n11casestudy.data.model.UserSearchResult
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface UserService {
    @GET("search/users")
    suspend fun searchUsersByPage(
        @Query("page") page: Int, @Query("q") query: String
    ): UserSearchResult

    @GET("users/{username}")
    suspend fun usersProfileDetail(
        @Path("username") userName: String
    ): UserDetail


}