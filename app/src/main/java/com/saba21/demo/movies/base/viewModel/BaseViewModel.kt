package com.saba21.demo.movies.base.viewModel

import com.saba21.demo.movies.base.presentation.abstractHandlers.alert.BaseAlert
import com.saba21.demo.movies.base.presentation.abstractHandlers.error.BaseError
import com.saba21.demo.movies.base.presentation.abstractHandlers.error.CommonErrors
import com.saba21.demo.movies.base.presentation.abstractHandlers.loader.BaseLoader
import com.saba21.demo.movies.base.presentation.abstractHandlers.navigation.BaseNavigation
import com.saba21.demo.movies.base.presentation.action.BaseAction
import com.saba21.demo.movies.base.presentation.state.BaseViewState
import com.saba21.demo.movies.base.presentation.state.BaseViewStateData
import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject

abstract class BaseViewModel<ViewAction : BaseAction, ViewState : BaseViewState<out BaseViewStateData>> :
    BaseUtilViewModel() {

    abstract val initialViewState: ViewState

    private lateinit var currentViewState: ViewState

    val viewStateSubject = PublishSubject.create<ViewState>()

    val viewStateRestoreSubject = PublishSubject.create<ViewState>()

    private val actionSubject = PublishSubject.create<ViewAction>()

    fun initializeViewState(): Boolean {
        val isInitial = !::currentViewState.isInitialized
        if (isInitial)
            currentViewState = initialViewState
        else
            viewStateRestoreSubject.onNext(currentViewState)
        return isInitial
    }

    open fun onBindView(initial: Boolean) {

    }

    protected fun postAction(action: ViewAction) {
        actionSubject.onNext(action)
    }

    protected fun postState(state: ViewState) {
        @Suppress("UNCHECKED_CAST")
        currentViewState = state.updateCurrentState(currentViewState) as ViewState
        viewStateSubject.onNext(state)
    }

    fun onSubscribeViewAction(subject: Observable<ViewAction>) {
        compositeDisposable.add(
            Observable.merge(subject, actionSubject)
                .flatMap(this::processAction)
                .subscribe(this::postState)
        )
    }

    private fun processAction(viewAction: ViewAction): Observable<ViewState> {
        return when (viewAction) {
            is BaseError -> {
                intermediaryErrorHandler.handleError(viewAction)
                Observable.empty<ViewState>()
            }
            is BaseNavigation -> {
                intermediaryNavigationHandler.handleNavigation(viewAction)
                Observable.empty<ViewState>()
            }
            is BaseLoader -> {
                intermediaryLoaderHandler.handleLoader(viewAction)
                Observable.empty<ViewState>()
            }
            is BaseAlert<*> -> {
                intermediaryAlertHandler.handleAlert(viewAction) {
                    @Suppress("UNCHECKED_CAST")
                    postAction(it as ViewAction)
                }
                Observable.empty<ViewState>()
            }
            else -> onActionReceived(viewAction)
                .onErrorResumeNext { throwable: Throwable ->
                    intermediaryErrorHandler.handleError(CommonErrors.get(throwable))
                    Observable.empty()
                }
        }
    }

    protected open fun onActionReceived(action: ViewAction): Observable<ViewState> {
        return Observable.empty<ViewState>()
    }

}