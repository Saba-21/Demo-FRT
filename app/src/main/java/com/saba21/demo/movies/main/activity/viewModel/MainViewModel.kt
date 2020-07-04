package com.saba21.demo.movies.main.activity.viewModel

import androidx.lifecycle.ViewModel
import com.saba21.demo.movies.base.presentation.actionHandling.ActionHandler
import com.saba21.demo.movies.base.presentation.actionHandling.BaseAction
import com.saba21.demo.movies.base.presentation.errorHandling.BaseError
import com.saba21.demo.movies.base.presentation.errorHandling.ErrorHandler
import com.saba21.demo.movies.base.presentation.navigationHandling.BaseNavigation
import com.saba21.demo.movies.base.presentation.navigationHandling.NavigationHandler

class MainViewModel : ViewModel(),
    NavigationHandler,
    ErrorHandler,
    ActionHandler {

    override fun handleNavigation(navigation: BaseNavigation) {

    }

    override fun handleError(error: BaseError) {

    }

    override fun handleAction(action: BaseAction) {

    }

}