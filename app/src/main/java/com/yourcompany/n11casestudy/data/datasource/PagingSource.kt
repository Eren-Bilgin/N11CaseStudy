package com.yourcompany.n11casestudy.data.datasource

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.yourcompany.n11casestudy.data.model.User
import com.yourcompany.n11casestudy.data.network.UserService

class PagingSource(
    private val service: UserService, private val query: String
) : PagingSource<Int, User>() {
    override suspend fun load(
        params: LoadParams<Int>
    ): LoadResult<Int, User> {
        return try {
            val nextPageNumber = params.key ?: 1
            val response = service.searchUsersByPage(nextPageNumber, query)
            return LoadResult.Page(
                data = response.items.orEmpty(),
                prevKey = if (nextPageNumber == 1) null else nextPageNumber - 1,
                nextKey = if (response.items.isNullOrEmpty()) null else nextPageNumber + 1
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, User>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }
}