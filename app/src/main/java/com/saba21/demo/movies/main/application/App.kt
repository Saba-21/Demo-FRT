package com.saba21.demo.movies.main.application

import android.app.Application
import com.saba21.demo.movies.main.application.di.AppComponent
import com.saba21.demo.movies.main.application.di.DaggerAppComponent

class App : Application() {

    private val component: AppComponent by lazy {
        DaggerAppComponent
            .factory()
            .create(this)
    }

    val appComponent: AppComponent get() = component

    override fun onCreate() {
        super.onCreate()
        component.inject(this)
    }
}
