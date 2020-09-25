package com.saba21.demo.movies.base.di

import com.saba21.demo.movies.base.fragment.BaseDaggerFragment

interface BaseFragmentComponent<A : BaseDaggerFragment<*>> {

    fun inject(fragment: A)
}
