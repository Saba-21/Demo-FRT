package com.saba21.demo.movies.presentation.splash.di

import androidx.fragment.app.Fragment
import com.saba21.demo.movies.base.di.BaseFragmentComponent
import com.saba21.demo.movies.base.di.scopes.FragmentScope
import com.saba21.demo.movies.presentation.splash.SplashFragment
import dagger.BindsInstance
import dagger.Subcomponent

@FragmentScope
@Subcomponent(modules = [SplashModule::class])
interface SplashComponent :
    BaseFragmentComponent<SplashFragment> {

    @Subcomponent.Factory
    interface Factory {
        fun create(@BindsInstance fragment: Fragment): SplashComponent
    }

    override fun inject(fragment: SplashFragment)

}