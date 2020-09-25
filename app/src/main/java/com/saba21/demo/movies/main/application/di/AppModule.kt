package com.saba21.demo.movies.main.application.di

import android.app.Application
import android.content.Context
import com.saba21.demo.data.di.API_ADDRESS
import com.saba21.demo.data.di.API_KEY
import com.saba21.demo.data.di.DataModule
import com.saba21.demo.data.di.POSTER_ADDRESS
import com.saba21.demo.movies.R
import dagger.Module
import dagger.Provides
import javax.inject.Named
import javax.inject.Singleton

@Module(includes = [DataModule::class])
class AppModule {

    @Singleton
    @Provides
    fun provideAppContext(app: Application): Context = app.applicationContext

    @Singleton
    @Provides
    @Named(API_KEY)
    fun provideApiKey(appContext: Context): String {
        return appContext.getString(R.string.api_key)
    }

    @Singleton
    @Provides
    @Named(API_ADDRESS)
    fun provideApiAddress(appContext: Context): String {
        return appContext.getString(R.string.api_address)
    }

    @Singleton
    @Provides
    @Named(POSTER_ADDRESS)
    fun providePosterAddress(appContext: Context): String {
        return appContext.getString(R.string.poster_address)
    }
}
