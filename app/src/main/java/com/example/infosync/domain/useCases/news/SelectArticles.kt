package com.example.infosync.domain.useCases.news

import com.example.infosync.data.local.NewsDao
import com.example.infosync.domain.model.Article
import com.example.infosync.domain.repository.NewsRepository
import kotlinx.coroutines.flow.Flow

class SelectArticles(
    private val newsRepository: NewsRepository
) {

    operator fun invoke(): Flow<List<Article>> {
        return newsRepository.selectArticles()
    }

}