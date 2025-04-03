package com.example.infosync.news.domain.useCases.news

import com.example.infosync.news.domain.model.Article
import com.example.infosync.news.domain.repository.NewsRepository

class DeleteArticle(
    private val newsRepository: NewsRepository
) {

    suspend operator fun invoke(article: Article) {
        newsRepository.deleteArticle(article)
    }

}