package com.example.infosync.domain.useCases.news

import androidx.paging.PagingData
import com.example.infosync.domain.model.Article
import com.example.infosync.domain.repository.NewsRepository
import kotlinx.coroutines.flow.Flow

class GetNews(
    private val newsRepository: NewsRepository
) {
    operator fun invoke(sources: List<String>): Flow<PagingData<Article>> {
        return newsRepository.getNews(sources = sources)
    }
}