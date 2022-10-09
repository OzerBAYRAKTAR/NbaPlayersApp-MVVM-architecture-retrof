package com.ozerbayraktar.nbaplayersapp.data.di

import com.ozerbayraktar.nbaplayersapp.data.service.NbaPlayersApi
import com.ozerbayraktar.nbaplayersapp.util.Constants.BASE_URL
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
    fun provideNbaPlayersApi(): NbaPlayersApi {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .build()
            .create(NbaPlayersApi::class.java)
    }
}