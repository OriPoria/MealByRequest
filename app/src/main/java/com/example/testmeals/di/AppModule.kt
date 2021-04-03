package com.example.testmeals.di

import com.example.meals.network.ConnectivityInterceptorImpl
import com.example.meals.network.TheMealDBApiService
import com.example.testmeals.repository.MealsRepository
import com.example.testmeals.repository.MealsRepositoryImpl
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
    fun provideMealApi() :TheMealDBApiService =
        TheMealDBApiService(ConnectivityInterceptorImpl())

    @Provides
    @Singleton
    fun provideMealRepository(api: TheMealDBApiService) : MealsRepository =
        MealsRepositoryImpl(api)



}