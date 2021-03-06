package com.saba21.demo.movies.base.fragment

import android.content.Context
import android.os.Bundle
import android.view.View
import com.saba21.demo.movies.base.fragment.viewModel.BaseViewModel
import com.saba21.demo.movies.base.presentation.action.BaseAction
import com.saba21.demo.movies.base.presentation.state.BaseViewState
import com.saba21.demo.movies.base.presentation.state.BaseViewStateData
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.subjects.PublishSubject

abstract class BaseMVIFragment<Action : BaseAction, ViewState : BaseViewState<out BaseViewStateData>, ViewModel : BaseViewModel<Action, ViewState>> :
    BaseDaggerFragment<ViewModel>() {

    private val viewActionSubject: PublishSubject<Action> = PublishSubject.create()

    private val compositeDisposable = CompositeDisposable()

    private fun Disposable.addDisposable() {
        compositeDisposable.add(this)
    }

    protected fun postAction(action: Action) {
        viewActionSubject.onNext(action)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (!viewActionSubject.hasObservers())
            viewModel.onSubscribeViewAction(viewActionSubject)
        if (!viewModel.stateSubject.hasObservers())
            viewModel.stateSubject
                .filter {
                    view != null
                }.subscribe {
                    reflectState(it)
                }.addDisposable()
        if (!viewModel.restoreStateSubject.hasObservers())
            viewModel.restoreStateSubject
                .filter {
                    view != null
                }.subscribe {
                    onDraw(requireView(), it)
                }.addDisposable()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val isInitial = viewModel.initializeViewState()
        viewModel.onBindView(isInitial)
        if (isInitial)
            onDraw(view, null)
    }

    open fun reflectState(state: ViewState) {
    }

    open fun onDraw(view: View, lastState: ViewState?) {
    }

    override fun onDestroy() {
        compositeDisposable.dispose()
        compositeDisposable.clear()
        super.onDestroy()
    }
}
