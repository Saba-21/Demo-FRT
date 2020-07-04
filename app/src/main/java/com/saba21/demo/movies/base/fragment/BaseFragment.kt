package com.saba21.demo.movies.base.fragment

import android.os.Bundle
import android.view.View
import androidx.annotation.LayoutRes
import com.saba21.demo.movies.base.presentation.action.BaseAction
import com.saba21.demo.movies.base.viewModel.BaseViewModel
import io.reactivex.subjects.PublishSubject
import kotlin.reflect.KClass

abstract class BaseFragment<Action : BaseAction, ViewModel : BaseViewModel<Action>>(
    @LayoutRes layoutRes: Int,
    viewModelClass: KClass<out ViewModel>
) : BaseDaggerFragment<ViewModel>(layoutRes, viewModelClass) {

    private val viewActionSubject: PublishSubject<Action> = PublishSubject.create()

    protected fun postAction(action: Action) {
        viewActionSubject.onNext(action)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        onDraw(view)
        viewModel.onSubscribeViewAction(viewActionSubject)
        viewModel.bindView()
    }

    abstract fun onDraw(view: View)

}