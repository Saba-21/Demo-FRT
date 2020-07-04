package com.saba21.demo.movies.base.viewModel

import androidx.lifecycle.ViewModel
import com.saba21.demo.movies.base.presentation.action.BaseAction
import com.saba21.demo.movies.base.presentation.errorHandling.BaseError
import com.saba21.demo.movies.base.presentation.errorHandling.ErrorHandler
import com.saba21.demo.movies.base.presentation.eventHandling.BaseEvent
import com.saba21.demo.movies.base.presentation.eventHandling.EventHandler
import com.saba21.demo.movies.base.presentation.navigationHandling.BaseNavigation
import com.saba21.demo.movies.base.presentation.navigationHandling.NavigationHandler
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.subjects.PublishSubject
import javax.inject.Inject

abstract class BaseViewModel<ViewAction : BaseAction> : ViewModel() {

    private val compositeDisposable: CompositeDisposable = CompositeDisposable()

    @Inject
    lateinit var mainErrorHandler: ErrorHandler

    @Inject
    lateinit var mainNavigationHandler: NavigationHandler

    @Inject
    lateinit var mainEventHandler: EventHandler

    private val actionSubject = PublishSubject.create<ViewAction>()

    open fun bindView() {

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
                    when (viewAction) {
                        is BaseError -> {
                            mainErrorHandler.handleError(viewAction)
                            Observable.empty<Unit>()
                        }
                        is BaseNavigation -> {
                            mainNavigationHandler.handleNavigation(viewAction)
                            Observable.empty<Unit>()
                        }
                        is BaseEvent -> {
                            mainEventHandler.handleAction(viewAction)
                            Observable.empty<Unit>()
                        }
                        else -> onActionReceived(viewAction)
                            .onErrorResumeNext { _: Throwable ->
                                Observable.empty()
                            }
                    }
                }.subscribe {

                }
        )
    }

    protected open fun onActionReceived(action: ViewAction): Observable<Unit> {
        return Observable.empty<Unit>()
    }

    override fun onCleared() {
        compositeDisposable.dispose()
        compositeDisposable.clear()
        super.onCleared()
    }

}