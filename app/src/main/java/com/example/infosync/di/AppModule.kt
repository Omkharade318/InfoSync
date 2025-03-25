package com.example.infosync.di

import android.app.Application
import androidx.room.Room
import com.example.infosync.data.local.NewsDao
import com.example.infosync.data.local.NewsDatabase
import com.example.infosync.data.local.NewsTypeConvertor
import com.example.infosync.data.manager.LocalUserManagerImpl
import com.example.infosync.data.remote.NewsApi
import com.example.infosync.data.repository.NewsRepositoryImpl
import com.example.infosync.domain.manager.LocalUserManager
import com.example.infosync.domain.repository.NewsRepository
import com.example.infosync.domain.useCases.app_entry.AppEntryUseCases
import com.example.infosync.domain.useCases.app_entry.ReadAppEntry
import com.example.infosync.domain.useCases.app_entry.SaveAppEntry
import com.example.infosync.domain.useCases.news.GetNews
import com.example.infosync.domain.useCases.news.NewsUseCases
import com.example.infosync.domain.useCases.news.SearchNews
import com.example.infosync.util.Constants.BASE_URL
import com.example.infosync.util.Constants.NEWS_DATABASE_NAME
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideLocalUserManager(
        application: Application
    ): LocalUserManager = LocalUserManagerImpl(application)

    @Provides
    @Singleton
    fun provideAppEntryUseCases(localUserManager: LocalUserManager) = AppEntryUseCases (
        readAppEntry = ReadAppEntry(localUserManager),
        saveAppEntry = SaveAppEntry(localUserManager)
    )

    @Provides
    @Singleton
    fun provideNewsApi(): NewsApi{
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(NewsApi::class.java)
    }

    @Provides
    @Singleton
    fun provideNewsRepository(
        newsApi: NewsApi
    ): NewsRepository = NewsRepositoryImpl(newsApi = newsApi)

    @Provides
    @Singleton
    fun providesNewsCases(
        newsRepository: NewsRepository
    ): NewsUseCases {
        return NewsUseCases(
            getNews = GetNews(newsRepository = newsRepository),
            searchNews = SearchNews(newsRepository = newsRepository)
        )
    }

    @Provides
    @Singleton
    fun provideNewsDatabase(
        application: Application
    ): NewsDatabase {
        return Room.databaseBuilder(
            context = application,
            klass = NewsDatabase::class.java,
            name = NEWS_DATABASE_NAME
        ).addTypeConverter(NewsTypeConvertor())
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    @Singleton
    fun provideNewsDao(
        newsDatabase: NewsDatabase
    ): NewsDao = newsDatabase.newsDao

}