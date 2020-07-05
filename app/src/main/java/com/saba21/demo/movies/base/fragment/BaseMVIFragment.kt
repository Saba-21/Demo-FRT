package com.saba21.demo.movies.base.fragment

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.annotation.LayoutRes
import com.saba21.demo.movies.base.presentation.action.BaseAction
import com.saba21.demo.movies.base.presentation.state.BaseViewState
import com.saba21.demo.movies.base.presentation.state.BaseViewStateData
import com.saba21.demo.movies.base.viewModel.BaseViewModel
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.subjects.PublishSubject
import kotlin.reflect.KClass

abstract class BaseMVIFragment<Action : BaseAction, ViewState : BaseViewState<out BaseViewStateData>, ViewModel : BaseViewModel<Action, ViewState>>(
    @LayoutRes layoutRes: Int,
    viewModelClass: KClass<out ViewModel>
) : BaseDaggerFragment<ViewModel>(layoutRes, viewModelClass) {

    private val viewActionSubject: PublishSubject<Action> = PublishSubject.create()

    private val compositeDisposable = CompositeDisposable()

    protected fun postAction(action: Action) {
        viewActionSubject.onNext(action)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (!viewActionSubject.hasObservers())
            viewModel.onSubscribeViewAction(viewActionSubject)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (!viewModel.viewStateSubject.hasObservers())
            compositeDisposable.add(
                viewModel.viewStateSubject
                    .filter {
                        isAdded
                    }.subscribe {
                        reflectState(it)
                    }
            )
        if (!viewModel.viewStateRestoreSubject.hasObservers())
            compositeDisposable.add(
                viewModel.viewStateRestoreSubject
                    .filter {
                        isAdded
                    }.subscribe {
                        restoreState(it)
                    }
            )
        viewModel.onBindView()
        onDraw(view)
    }

    open fun restoreState(lastState: ViewState) {

    }

    open fun reflectState(state: ViewState) {

    }

    open fun onDraw(view: View) {

    }

    override fun onDestroy() {
        compositeDisposable.dispose()
        compositeDisposable.clear()
        super.onDestroy()
    }

}