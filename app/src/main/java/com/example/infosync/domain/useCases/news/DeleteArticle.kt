package com.example.infosync.domain.useCases.news

import com.example.infosync.data.local.NewsDao
import com.example.infosync.domain.model.Article

class DeleteArticle(
    private val newsDao: NewsDao
) {

    suspend operator fun invoke(article: Article) {
        newsDao.delete(article)
    }

}