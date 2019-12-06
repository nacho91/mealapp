package ar.com.nacho91.mealapp.di

import ar.com.nacho91.mealapp.data.api.ApiClient
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class ApiModule {

    @Singleton
    @Provides
    fun provideApiClient(): ApiClient {
        return ApiClient("https://www.themealdb.com/api/json/v1/1/")
    }
}