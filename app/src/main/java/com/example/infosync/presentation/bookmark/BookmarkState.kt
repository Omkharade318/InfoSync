package com.example.infosync.presentation.bookmark

import com.example.infosync.domain.model.Article

data class BookmarkState(
    val articles: List<Article> = emptyList()
)