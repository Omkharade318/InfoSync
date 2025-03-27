package com.example.infosync.domain.useCases.news

import com.example.infosync.data.local.NewsDao
import com.example.infosync.domain.model.Article
import com.example.infosync.domain.repository.NewsRepository

class SelectArticle(
    private val newsRepository: NewsRepository
) {

    suspend operator fun invoke(url: String): Article? {
       return newsRepository.selectArticle(url)
    }

}