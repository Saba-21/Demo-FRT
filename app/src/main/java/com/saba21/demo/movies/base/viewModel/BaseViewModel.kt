package com.saba21.demo.movies.base.viewModel

import androidx.lifecycle.ViewModel
import com.saba21.demo.movies.base.presentation.action.BaseAction
import com.saba21.demo.movies.base.presentation.errorHandling.BaseError
import com.saba21.demo.movies.base.presentation.errorHandling.ErrorHandler
import com.saba21.demo.movies.base.presentation.eventHandling.BaseEvent
import com.saba21.demo.movies.base.presentation.eventHandling.EventHandler
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

    abstract fun setInitialState(): ViewState

    lateinit var currentState: BaseViewStateData

    val viewStateSubject = PublishSubject.create<ViewState>()

    val viewStateRestoreSubject = PublishSubject.create<BaseViewStateData>()

    private val compositeDisposable: CompositeDisposable = CompositeDisposable()

    @Inject
    lateinit var mainErrorHandler: ErrorHandler

    @Inject
    lateinit var mainNavigationHandler: NavigationHandler

    @Inject
    lateinit var mainEventHandler: EventHandler

    private val actionSubject = PublishSubject.create<ViewAction>()

    open fun onBindView() {
        if (::currentState.isInitialized)
            viewStateRestoreSubject.onNext(currentState)
        else
            currentState = setInitialState().currentState
    }

    protected fun Disposable.addSubscription() {
        compositeDisposable.add(this)
    }

    protected fun postAction(action: ViewAction) {
        actionSubject.onNext(action)
    }

    fun onSubscribeViewAction(subject: Observable<ViewAction>) {
        compositeDisposable.add(
            Observable.merge(subject, actionSubject)
                .flatMap { viewAction ->
                    processAction(viewAction)
                }.subscribe {
                    currentState = it.currentState
                    viewStateSubject.onNext(it)
                }
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
            is BaseEvent -> {
                mainEventHandler.handleEvent(viewAction)
                Observable.empty<ViewState>()
            }
            else -> onActionReceived(viewAction)
                .onErrorResumeNext { _: Throwable ->
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