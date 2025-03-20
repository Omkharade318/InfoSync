package com.example.infosync.domain.repository

import androidx.paging.PagingData
import com.example.infosync.domain.model.Article
import kotlinx.coroutines.flow.Flow

interface NewsRepository {

    fun getNews(sources: List<String>): Flow<PagingData<Article>>

}