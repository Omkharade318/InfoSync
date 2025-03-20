package com.example.infosync.data.remote

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.infosync.domain.model.Article
import com.example.infosync.util.Constants.API_KEY

class NewsPagingSource(
    private val newsApi: NewsApi,
    private val sources: String
): PagingSource<Int, Article>() {


    private var totalNewsCount = 0

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Article> { // makes request to the API and returns the data
        val page = params.key ?: 1 //
        return try {

            val newsResponse = newsApi.getNews(sources = sources, page = page)
            totalNewsCount += newsResponse.articles.size
            val articles = newsResponse.articles.distinctBy { it.title } //removes duplicates

            LoadResult.Page(
                data = articles,
                nextKey = if (totalNewsCount == newsResponse.totalResults) null else page + 1,
                prevKey = null
            )

        }catch (e: Exception) {

            e.printStackTrace()
            LoadResult.Error(
                throwable = e
            )

        }
    }

    override fun getRefreshKey(state: PagingState<Int, Article>): Int? { // refreshes the data
        return state.anchorPosition?.let { anchorPosition -> // returns the position of the item closest to the anchor position (latest accessed page in the list)
            val anchorPage = state.closestPageToPosition(anchorPosition) // returns the page that contains the item closest to the anchor position
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1) // returns the previous or next page of the anchor page
        }
    }


}