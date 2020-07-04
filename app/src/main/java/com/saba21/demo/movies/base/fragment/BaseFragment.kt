package com.saba21.demo.movies.base.fragment

import android.os.Bundle
import android.view.View
import androidx.annotation.LayoutRes
import androidx.lifecycle.ViewModel
import com.saba21.demo.movies.base.viewModel.BaseViewModel
import kotlin.reflect.KClass

abstract class BaseFragment<VM : BaseViewModel>(
    @LayoutRes layoutRes: Int,
    viewModelClass: KClass<out ViewModel>
) : BaseDaggerFragment<VM>(layoutRes, viewModelClass) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        onDraw(view)
        viewModel.bindView()
    }

    abstract fun onDraw(view: View)

}