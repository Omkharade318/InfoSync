package com.example.infosync.domain.useCases.news

import com.example.infosync.domain.model.Article
import com.example.infosync.domain.repository.NewsRepository

class UpsertArticle(
    private val newsRepository: NewsRepository
) {

    suspend operator fun invoke(article: Article) {
        newsRepository.upsertArticle(article)
    }

}