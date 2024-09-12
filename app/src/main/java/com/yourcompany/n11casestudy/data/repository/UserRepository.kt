package com.yourcompany.n11casestudy.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.yourcompany.n11casestudy.data.datasource.PagingSource
import com.yourcompany.n11casestudy.data.model.User
import com.yourcompany.n11casestudy.data.network.UserService
import kotlinx.coroutines.flow.Flow
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class UserRepository @Inject constructor() {
    private val retrofit = Retrofit.Builder().baseUrl("https://api.github.com/").callFactory(
        OkHttpClient.Builder().readTimeout(20, TimeUnit.SECONDS)
            .connectTimeout(20, TimeUnit.SECONDS).build()
    ).addConverterFactory(GsonConverterFactory.create()).build().create(UserService::class.java)

    suspend fun getUsersDetail(query: String) = retrofit.usersProfileDetail(userName = query)
    fun getUsersByPage(query: String): Flow<PagingData<User>> {
        return Pager(PagingConfig(pageSize = 30), pagingSourceFactory = {
            PagingSource(retrofit, query)
        }).flow
    }

}