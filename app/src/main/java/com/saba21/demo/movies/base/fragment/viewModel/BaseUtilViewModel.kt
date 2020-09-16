package com.saba21.demo.movies.base.fragment.viewModel

import androidx.lifecycle.ViewModel
import com.saba21.demo.movies.base.activity.viewModel.abstractHandlers.alert.IntermediaryAlertHandler
import com.saba21.demo.movies.base.activity.viewModel.abstractHandlers.error.IntermediaryErrorHandler
import com.saba21.demo.movies.base.activity.viewModel.abstractHandlers.loader.IntermediaryLoaderHandler
import com.saba21.demo.movies.base.activity.viewModel.abstractHandlers.navigation.IntermediaryNavigationHandler
import com.saba21.demo.movies.base.activity.viewModel.abstractHandlers.permission.IntermediaryPermissionHandler
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import javax.inject.Inject

open class BaseUtilViewModel : ViewModel() {

    @Inject
    lateinit var intermediaryErrorHandler: IntermediaryErrorHandler

    @Inject
    lateinit var intermediaryNavigationHandler: IntermediaryNavigationHandler

    @Inject
    lateinit var intermediaryLoaderHandler: IntermediaryLoaderHandler

    @Inject
    lateinit var intermediaryAlertHandler: IntermediaryAlertHandler

    @Inject
    lateinit var intermediaryPermissionHandler: IntermediaryPermissionHandler

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