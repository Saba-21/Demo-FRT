package com.saba21.demo.movies.main.application.di

import android.app.Application
import android.content.Context
import com.saba21.demo.data.di.DataModule
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module(includes = [DataModule::class])
class AppModule {

    @Singleton
    @Provides
    fun provideAppContext(app: Application): Context = app.applicationContext

}