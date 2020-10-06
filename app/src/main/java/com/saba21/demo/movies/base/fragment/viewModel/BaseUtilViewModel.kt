package com.saba21.demo.movies.base.fragment.viewModel

import androidx.lifecycle.ViewModel
import com.saba21.demo.movies.base.activity.viewModel.abstractHandlers.alert.AlertCommandHandler
import com.saba21.demo.movies.base.activity.viewModel.abstractHandlers.error.ErrorCommandHandler
import com.saba21.demo.movies.base.activity.viewModel.abstractHandlers.loader.LoaderCommandHandler
import com.saba21.demo.movies.base.activity.viewModel.abstractHandlers.navigation.NavigationCommandHandler
import com.saba21.demo.movies.base.activity.viewModel.abstractHandlers.permission.PermissionCommandHandler
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import javax.inject.Inject

open class BaseUtilViewModel : ViewModel() {

    @Inject
    lateinit var errorCommandHandler: ErrorCommandHandler

    @Inject
    lateinit var navigationCommandHandler: NavigationCommandHandler

    @Inject
    lateinit var loaderCommandHandler: LoaderCommandHandler

    @Inject
    lateinit var alertCommandHandler: AlertCommandHandler

    @Inject
    lateinit var permissionCommandHandler: PermissionCommandHandler

    protected val compositeDisposable: CompositeDisposable = CompositeDisposable()

    protected fun Disposable.addSubscription() {
        compositeDisposable.add(this)
    }

    override fun onCleared() {
        compositeDisposable.dispose()
        compositeDisposable.clear()
        super.onCleared()
    }
}
