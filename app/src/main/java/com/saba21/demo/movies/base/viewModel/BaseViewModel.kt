package com.saba21.demo.movies.base.viewModel

import androidx.lifecycle.ViewModel
import com.saba21.demo.movies.base.presentation.action.BaseAction
import com.saba21.demo.movies.base.presentation.errorHandling.BaseError
import com.saba21.demo.movies.base.presentation.errorHandling.ErrorHandler
import com.saba21.demo.movies.base.presentation.errorHandling.CommonErrors
import com.saba21.demo.movies.base.presentation.navigationHandling.BaseNavigation
import com.saba21.demo.movies.base.presentation.navigationHandling.NavigationHandler
import com.saba21.demo.movies.base.presentation.state.BaseViewState
import com.saba21.demo.movies.base.presentation.state.BaseViewStateData
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.subjects.PublishSubject
import javax.inject.Inject

abstract class BaseViewModel<ViewAction : BaseAction, ViewState : BaseViewState<out BaseViewStateData>> :
    ViewModel() {

    abstract val initialViewState: ViewState

    private lateinit var currentViewState: ViewState

    val viewStateSubject = PublishSubject.create<ViewState>()

    val viewStateRestoreSubject = PublishSubject.create<ViewState>()

    private val actionSubject = PublishSubject.create<ViewAction>()

    private val compositeDisposable: CompositeDisposable = CompositeDisposable()

    @Inject
    lateinit var mainErrorHandler: ErrorHandler

    @Inject
    lateinit var mainNavigationHandler: NavigationHandler

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

    protected fun Disposable.addSubscription() {
        compositeDisposable.add(this)
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
                mainErrorHandler.handleError(viewAction)
                Observable.empty<ViewState>()
            }
            is BaseNavigation -> {
                mainNavigationHandler.handleNavigation(viewAction)
                Observable.empty<ViewState>()
            }
            else -> onActionReceived(viewAction)
                .onErrorResumeNext { throwable: Throwable ->
                    mainErrorHandler.handleError(CommonErrors.get(throwable))
                    Observable.empty()
                }
        }
    }

    protected open fun onActionReceived(action: ViewAction): Observable<ViewState> {
        return Observable.empty<ViewState>()
    }

    override fun onCleared() {
        compositeDisposable.dispose()
        compositeDisposable.clear()
        super.onCleared()
    }

}