package com.example.stravaapi.di

import com.example.stravaapi.data.network.StravaAuthService
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule {

    @Singleton
    @Provides
    fun provideAuthService(): StravaAuthService {
        return StravaAuthService.create()
    }
}