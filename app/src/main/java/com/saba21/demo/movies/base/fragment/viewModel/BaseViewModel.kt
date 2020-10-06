package com.saba21.demo.movies.base.fragment.viewModel

import com.saba21.demo.movies.base.activity.viewModel.abstractHandlers.alert.AlertCommand
import com.saba21.demo.movies.base.activity.viewModel.abstractHandlers.error.ErrorCommand
import com.saba21.demo.movies.base.activity.viewModel.abstractHandlers.error.CommonErrors
import com.saba21.demo.movies.base.activity.viewModel.abstractHandlers.loader.LoaderCommand
import com.saba21.demo.movies.base.activity.viewModel.abstractHandlers.navigation.NavigationCommand
import com.saba21.demo.movies.base.activity.viewModel.abstractHandlers.permission.PermissionCommand
import com.saba21.demo.movies.base.presentation.action.BaseAction
import com.saba21.demo.movies.base.presentation.state.BaseViewState
import com.saba21.demo.movies.base.presentation.state.BaseViewStateData
import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject

abstract class BaseViewModel<ViewAction : BaseAction, ViewState : BaseViewState<out BaseViewStateData>> :
    BaseUtilViewModel() {

    private var currentViewState: ViewState? = null

    val stateSubject = PublishSubject.create<ViewState>()

    val restoreStateSubject = PublishSubject.create<ViewState>()

    private val actionSubject = PublishSubject.create<ViewAction>()

    fun initializeViewState(): Boolean {
        val isInitialized = currentViewState != null
        if (isInitialized)
            restoreStateSubject.onNext(currentViewState!!)
        return !isInitialized
    }

    open fun onBindView(initial: Boolean) {}

    protected fun postAction(action: ViewAction) {
        actionSubject.onNext(action)
    }

    @Suppress("UNCHECKED_CAST")
    protected fun postState(state: ViewState) {
        currentViewState = state.updateCurrentState(currentViewState ?: state) as ViewState
        stateSubject.onNext(state)
    }

    fun onSubscribeViewAction(subject: Observable<ViewAction>) {
        compositeDisposable.add(
            Observable.merge(subject, actionSubject)
                .flatMap(this::processAction)
                .subscribe(this::postState)
        )
    }

    @Suppress("UNCHECKED_CAST")
    private fun processAction(viewAction: ViewAction): Observable<ViewState> {
        return when (viewAction) {
            is ErrorCommand -> {
                errorCommandHandler.handleErrorCommand(viewAction)
                Observable.empty<ViewState>()
            }
            is NavigationCommand -> {
                navigationCommandHandler.handleNavigationCommand(viewAction)
                Observable.empty<ViewState>()
            }
            is LoaderCommand -> {
                loaderCommandHandler.handleLoaderCommand(viewAction)
                Observable.empty<ViewState>()
            }
            is AlertCommand<*> -> {
                alertCommandHandler.handleAlertCommand(viewAction) {
                    postAction(it as ViewAction)
                }
                Observable.empty<ViewState>()
            }
            is PermissionCommand<*> -> {
                permissionCommandHandler.handlePermissionCommand(viewAction) {
                    postAction(it as ViewAction)
                }
                Observable.empty<ViewState>()
            }
            else -> onActionReceived(viewAction)
                .onErrorResumeNext { throwable: Throwable ->
                    errorCommandHandler.handleErrorCommand(CommonErrors.get(throwable))
                    Observable.empty()
                }
        }
    }

    protected open fun onActionReceived(action: ViewAction): Observable<ViewState> {
        return Observable.empty<ViewState>()
    }
}
