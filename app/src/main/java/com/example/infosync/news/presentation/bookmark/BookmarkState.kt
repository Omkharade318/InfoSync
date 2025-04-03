package com.example.infosync.news.presentation.bookmark

import com.example.infosync.news.domain.model.Article

data class BookmarkState(
    val articles: List<Article> = emptyList()
)