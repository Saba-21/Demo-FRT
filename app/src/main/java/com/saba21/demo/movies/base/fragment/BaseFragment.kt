package com.saba21.demo.movies.base.fragment

import android.os.Bundle
import android.view.View
import androidx.annotation.LayoutRes
import androidx.lifecycle.ViewModel
import kotlin.reflect.KClass

abstract class BaseFragment<VM>(@LayoutRes layoutRes: Int, viewModelClass: KClass<out ViewModel>) :
    BaseDaggerFragment<VM>(layoutRes, viewModelClass) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        onDraw(view)
    }

    abstract fun onDraw(view: View)

}