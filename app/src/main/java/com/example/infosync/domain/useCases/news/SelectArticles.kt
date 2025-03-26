package com.example.infosync.domain.useCases.news

import com.example.infosync.data.local.NewsDao
import com.example.infosync.domain.model.Article
import kotlinx.coroutines.flow.Flow

class SelectArticles(
    private val newsDao: NewsDao
) {

    operator fun invoke(): Flow<List<Article>> {
        return newsDao.getArticles()
    }

}