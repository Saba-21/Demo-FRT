package com.saba21.demo.movies.base.activity.viewModel

import androidx.lifecycle.ViewModel
import com.saba21.demo.movies.base.activity.MainHandler
import com.saba21.demo.movies.base.activity.viewModel.abstractHandlers.alert.AlertCommandHandler
import com.saba21.demo.movies.base.activity.viewModel.abstractHandlers.error.ErrorCommandHandler
import com.saba21.demo.movies.base.activity.viewModel.abstractHandlers.loader.LoaderCommandHandler
import com.saba21.demo.movies.base.activity.viewModel.abstractHandlers.navigation.NavigationCommandHandler
import com.saba21.demo.movies.base.activity.viewModel.abstractHandlers.permission.PermissionCommandHandler
import java.lang.ref.WeakReference

abstract class BaseViewModel : ViewModel(),
    NavigationCommandHandler,
    ErrorCommandHandler,
    LoaderCommandHandler,
    AlertCommandHandler,
    PermissionCommandHandler {

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
