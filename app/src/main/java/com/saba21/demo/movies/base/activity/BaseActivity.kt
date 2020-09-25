package com.saba21.demo.movies.base.activity

import android.os.Bundle
import androidx.activity.viewModels
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import com.saba21.demo.movies.main.activity.MainViewModel
import com.saba21.demo.movies.main.activity.di.ActivityComponent
import com.saba21.demo.movies.main.activity.di.MainViewModelFactory
import com.saba21.demo.movies.main.application.App
import javax.inject.Inject

abstract class BaseActivity(@LayoutRes layoutRes: Int) : AppCompatActivity(layoutRes),
    MainHandler {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        component.inject(this)
        viewModel.bindView(this)
    }

    private val component: ActivityComponent by lazy {
        (application as App).appComponent
            .getActivityComponentFactory()
            .create(this)
    }

    val activityComponent: ActivityComponent get() = component

    private val viewModel: MainViewModel by lazy {
        viewModels<MainViewModel> {
            viewModelFactory
        }.value
    }

    @Inject
    lateinit var viewModelFactory: MainViewModelFactory

    override fun onDestroy() {
        viewModel.unbindView()
        super.onDestroy()
    }
}
