package com.saba21.demo.movies.app.activity

import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelStore
import com.saba21.demo.movies.base.di.scopes.ActivityScope
import dagger.Module
import dagger.Provides

@Module
class ActivityModule {

    @Provides
    @ActivityScope
    fun provideViewModelStore(activity: AppCompatActivity): ViewModelStore {
        return activity.viewModelStore
    }

}