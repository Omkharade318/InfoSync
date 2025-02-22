package com.example.infosync.di

import android.app.Application
import com.example.infosync.data.manager.LocalUserManagerImpl
import com.example.infosync.domain.manager.LocalUserManager
import com.example.infosync.domain.useCases.AppEntryUseCases
import com.example.infosync.domain.useCases.ReadAppEntry
import com.example.infosync.domain.useCases.SaveAppEntry
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
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

}