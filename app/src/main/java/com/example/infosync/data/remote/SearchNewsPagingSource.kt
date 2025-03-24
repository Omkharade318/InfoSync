package com.example.infosync.data.remote

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.infosync.domain.model.Article

class SearchNewsPagingSource(
    private val newsApi: NewsApi,
    private val searchQuery: String,
    private val sources: String
): PagingSource<Int, Article>() {

    override fun getRefreshKey(state: PagingState<Int, Article>): Int? {
        return state.anchorPosition?.let { anchorPosition -> // returns the position of the item closest to the anchor position (latest accessed page in the list)
            val anchorPage = state.closestPageToPosition(anchorPosition) // returns the page that contains the item closest to the anchor position
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1) // returns the previous or next page of the anchor page
        }
    }

    private var totalNewsCount = 0

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Article> {
        val page = params.key ?: 1
        return try {
            val newsResponse = newsApi.searchNews(sources = sources, searchQuery = searchQuery, page = page)
            Log.d("SearchNewsPagingSource", "API Response: ${newsResponse.articles.size} articles found")
            val articles = newsResponse.articles.distinctBy { it.title }
            LoadResult.Page(
                data = articles,
                nextKey = if (articles.isEmpty()) null else page + 1,
                prevKey = if (page == 1) null else page - 1
            )
        } catch (e: Exception) {
            e.printStackTrace()
            LoadResult.Error(throwable = e)
        }
    }
}