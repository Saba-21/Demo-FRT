package com.saba21.demo.movies.base.viewModel

import androidx.lifecycle.ViewModel
import com.saba21.demo.movies.base.presentation.actionHandling.ActionHandler
import com.saba21.demo.movies.base.presentation.errorHandling.ErrorHandler
import com.saba21.demo.movies.base.presentation.navigationHandling.NavigationHandler
import javax.inject.Inject

open class BaseViewModel : ViewModel() {

    @Inject
    lateinit var mainErrorHandler: ErrorHandler

    @Inject
    lateinit var mainNavigationHandler: NavigationHandler

    @Inject
    lateinit var mainActionHandler: ActionHandler

    open fun bindView() {

    }

    override fun onCleared() {
        super.onCleared()
    }

}