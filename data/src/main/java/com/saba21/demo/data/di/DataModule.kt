package com.saba21.demo.data.di

import android.content.Context
import androidx.room.Room
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import com.saba21.demo.data.database.MovieDatabase
import com.saba21.demo.data.repository.MovieRepositoryImpl
import com.saba21.demo.domain.repository.MovieRepository
import dagger.Module
import dagger.Provides
import kotlinx.serialization.UnstableDefault
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonBuilder
import okhttp3.MediaType
import okhttp3.OkHttpClient
import retrofit2.CallAdapter
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
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
    fun provideApiClient(): OkHttpClient {
        return OkHttpClient.Builder().build()
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
        return Json(JsonBuilder().buildConfiguration())
            .asConverterFactory(MediaType.parse("application/json")!!)
    }

    @Singleton
    @Provides
    fun provideRetrofit(
        converter: Converter.Factory,
        callAdapter: CallAdapter.Factory,
        client: OkHttpClient
    ): Retrofit {
        return Retrofit
            .Builder()
            .baseUrl("https://www.youtube.com/")
            .client(client)
            .addConverterFactory(converter)
            .addCallAdapterFactory(callAdapter)
            .build()
    }

}