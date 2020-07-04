package com.saba21.demo.movies.app.application

import android.app.Application

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