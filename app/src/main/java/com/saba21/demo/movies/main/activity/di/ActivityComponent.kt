package com.saba21.demo.movies.main.activity.di

import androidx.appcompat.app.AppCompatActivity
import com.saba21.demo.movies.base.activity.BaseActivity
import com.saba21.demo.movies.base.di.scopes.ActivityScope
import dagger.BindsInstance
import dagger.Subcomponent

@ActivityScope
@Subcomponent(modules = [ActivityModule::class])
interface ActivityComponent : FragmentSubComponentProvider {

    @Subcomponent.Factory
    interface Factory {
        fun create(@BindsInstance activity: AppCompatActivity): ActivityComponent
    }

    fun inject(activity: BaseActivity)
}
