package com.example.infosync.news.data.remote.dto

import com.example.infosync.news.domain.model.Article

data class NewsResponse(
    val articles: List<Article>,
    val status: String,
    val totalResults: Int
)