package com.saba21.demo.movies.main.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.saba21.demo.movies.R
import com.saba21.demo.movies.main.activity.di.ActivityComponent
import com.saba21.demo.movies.main.application.App

class MainActivity : AppCompatActivity(R.layout.activity_main) {

    private val component: ActivityComponent by lazy {
        (application as App).appComponent
            .getActivityComponentFactory()
            .create(this)
    }

    val activityComponent: ActivityComponent get() = component

    lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        component.inject(this)
    }

}