package com.saba21.demo.data.di

import android.content.Context
import androidx.room.Room
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import com.saba21.demo.data.api.MovieService
import com.saba21.demo.data.api.interceptor.ApiKeyInterceptor
import com.saba21.demo.data.database.MovieDatabase
import com.saba21.demo.data.repository.MovieRepositoryImpl
import com.saba21.demo.domain.repository.MovieRepository
import dagger.Module
import dagger.Provides
import kotlinx.serialization.UnstableDefault
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonBuilder
import okhttp3.Interceptor
import okhttp3.MediaType
import okhttp3.OkHttpClient
import retrofit2.CallAdapter
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import javax.inject.Named
import javax.inject.Singleton

@Module
class DataModule {

    @Singleton
    @Provides
    fun provideRepository(repository: MovieRepositoryImpl): MovieRepository {
        return repository
    }

    @Singleton
    @Provides
    fun provideDatabase(appContext: Context): MovieDatabase {
        return Room
            .databaseBuilder(
                appContext,
                MovieDatabase::class.java,
                "movie_database"
            )
            .fallbackToDestructiveMigration()
            .build()
    }

    @Singleton
    @Provides
    fun provideInterceptor(@Named(API_KEY) apiKey: String): Interceptor {
        return ApiKeyInterceptor(apiKey)
    }

    @Singleton
    @Provides
    fun provideApiClient(interceptor: Interceptor): OkHttpClient {
        return OkHttpClient
            .Builder()
            .addInterceptor(interceptor)
            .build()
    }

    @Singleton
    @Provides
    fun provideCallAdapter(): CallAdapter.Factory {
        return RxJava2CallAdapterFactory.create()
    }

    @UnstableDefault
    @Singleton
    @Provides
    fun provideConverterFactory(): Converter.Factory {
        return Json(
            JsonBuilder()
            .apply {
                ignoreUnknownKeys = true
                isLenient = true
            }.buildConfiguration()
        ).asConverterFactory(MediaType.parse("application/json")!!)
    }

    @Singleton
    @Provides
    fun provideRetrofit(
        converter: Converter.Factory,
        callAdapter: CallAdapter.Factory,
        client: OkHttpClient,
        @Named(API_ADDRESS) apiAddress: String
    ): Retrofit {
        return Retrofit
            .Builder()
            .baseUrl(apiAddress)
            .client(client)
            .addConverterFactory(converter)
            .addCallAdapterFactory(callAdapter)
            .build()
    }

    @Singleton
    @Provides
    fun provideApiService(retrofit: Retrofit): MovieService {
        return retrofit.create(MovieService::class.java)
    }

}