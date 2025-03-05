package com.betulesen.harrypotterapp.di

import com.betulesen.harrypotterapp.repository.HarryPotterRepository
import com.betulesen.harrypotterapp.service.HarryPotterAPI
import com.betulesen.harrypotterapp.util.Constants.BASE_URL
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

    @Singleton
    @Provides
    fun provideHarryPotterApi() : HarryPotterAPI{
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .build()
            .create(HarryPotterAPI::class.java)
    }

    @Singleton
    @Provides
    fun provideHarryPotterRepository(
        api : HarryPotterAPI
    ) = HarryPotterRepository(api)
}