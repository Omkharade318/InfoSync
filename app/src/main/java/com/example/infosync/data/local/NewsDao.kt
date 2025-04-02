package com.example.infosync.data.local

import androidx.room.*
import com.example.infosync.domain.model.Article
import kotlinx.coroutines.flow.Flow

@Dao
interface NewsDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsert(article: Article)

    @Delete
    suspend fun delete(article: Article)

    @Query("SELECT * FROM articles")
    fun getArticles(): Flow<List<Article>>

    @Query("SELECT * FROM articles WHERE url = :url")
    suspend fun getArticle(url: String): Article?
}