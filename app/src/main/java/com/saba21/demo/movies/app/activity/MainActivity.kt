package com.saba21.demo.movies.app.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.saba21.demo.movies.R
import com.saba21.demo.movies.app.application.App

class MainActivity : AppCompatActivity(R.layout.activity_main) {

    private val component: ActivityComponent by lazy {
        (application as App).appComponent
            .getActivityComponentFactory()
            .create(this)
    }

    val activityComponent: ActivityComponent get() = component

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        component.inject(this)
    }

}