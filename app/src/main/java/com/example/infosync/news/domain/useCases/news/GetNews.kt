package com.example.infosync.news.domain.useCases.news

import androidx.paging.PagingData
import com.example.infosync.news.domain.model.Article
import com.example.infosync.news.domain.repository.NewsRepository
import kotlinx.coroutines.flow.Flow

class GetNews(
    private val newsRepository: NewsRepository
) {
    operator fun invoke(sources: List<String>): Flow<PagingData<Article>> {
        return newsRepository.getNews(sources = sources)
    }
}