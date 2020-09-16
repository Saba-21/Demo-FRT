package com.saba21.demo.movies.base.activity.viewModel

import androidx.lifecycle.ViewModel
import com.saba21.demo.movies.base.activity.MainHandler
import com.saba21.demo.movies.base.activity.viewModel.abstractHandlers.alert.IntermediaryAlertHandler
import com.saba21.demo.movies.base.activity.viewModel.abstractHandlers.error.IntermediaryErrorHandler
import com.saba21.demo.movies.base.activity.viewModel.abstractHandlers.loader.IntermediaryLoaderHandler
import com.saba21.demo.movies.base.activity.viewModel.abstractHandlers.navigation.IntermediaryNavigationHandler
import com.saba21.demo.movies.base.activity.viewModel.abstractHandlers.permission.IntermediaryPermissionHandler
import java.lang.ref.WeakReference

abstract class BaseViewModel : ViewModel(),
    IntermediaryNavigationHandler,
    IntermediaryErrorHandler,
    IntermediaryLoaderHandler,
    IntermediaryAlertHandler,
    IntermediaryPermissionHandler {

    private var mainHandler: WeakReference<MainHandler>? = null

    fun bindView(mainHandler: MainHandler) {
        this.mainHandler = WeakReference(mainHandler)
    }

    fun unbindView() {
        mainHandler = null
    }

    protected fun requireHandler(callback: (mainHandler: MainHandler) -> Unit) {
        mainHandler?.get()?.run {
            callback.invoke(this)
        }
    }

}