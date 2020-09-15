package com.saba21.demo.movies.base.fragment

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.annotation.LayoutRes
import com.saba21.demo.movies.base.presentation.action.BaseAction
import com.saba21.demo.movies.base.presentation.state.BaseViewState
import com.saba21.demo.movies.base.presentation.state.BaseViewStateData
import com.saba21.demo.movies.base.fragment.viewModel.BaseViewModel
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.subjects.PublishSubject
import kotlin.reflect.KClass

abstract class BaseMVIFragment<Action : BaseAction, ViewState : BaseViewState<out BaseViewStateData>, ViewModel : BaseViewModel<Action, ViewState>>(
    @LayoutRes layoutRes: Int,
    viewModelClass: KClass<out ViewModel>
) : BaseDaggerFragment<ViewModel>(layoutRes, viewModelClass) {

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
        if (!viewModel.viewStateSubject.hasObservers())
            viewModel.viewStateSubject
                .filter {
                    view != null
                }.subscribe {
                    reflectState(it)
                }.addDisposable()
        if (!viewModel.viewStateRestoreSubject.hasObservers())
            viewModel.viewStateRestoreSubject
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