package com.saba21.demo.movies.app.application

import android.app.Application
import com.saba21.demo.movies.app.activity.ActivityComponent
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class])
interface AppComponent {

    @Component.Factory
    interface Factory {

        fun create(@BindsInstance app: Application): AppComponent

    }

    fun inject(app: App)

    fun getActivityComponentFactory(): ActivityComponent.Factory

}