package com.example.infosync.data.remote.dto

import com.example.infosync.domain.model.Article

data class NewsResponse(
    val articles: List<Article>,
    val status: String,
    val totalResults: Int
)