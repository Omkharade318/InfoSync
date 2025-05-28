package com.example.infosync.news.domain.useCases.news

import com.example.infosync.news.domain.model.Article
import com.example.infosync.news.domain.repository.NewsRepository

class SelectArticle(
    private val newsRepository: NewsRepository
) {

    suspend operator fun invoke(url: String): Article? {
       return newsRepository.selectArticle(url)
    }

}