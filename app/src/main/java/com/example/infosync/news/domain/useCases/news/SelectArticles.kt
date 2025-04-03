package com.example.infosync.news.domain.useCases.news

import com.example.infosync.news.domain.model.Article
import com.example.infosync.news.domain.repository.NewsRepository
import kotlinx.coroutines.flow.Flow

class SelectArticles(
    private val newsRepository: NewsRepository
) {

    operator fun invoke(): Flow<List<Article>> {
        return newsRepository.selectArticles()
    }

}