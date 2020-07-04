package com.saba21.demo.movies.base.fragment

import android.view.View
import androidx.annotation.LayoutRes
import com.saba21.demo.movies.base.presentation.action.BaseAction
import com.saba21.demo.movies.base.presentation.state.BaseViewState
import com.saba21.demo.movies.base.presentation.state.BaseViewStateData
import com.saba21.demo.movies.base.viewModel.BaseViewModel
import kotlin.reflect.KClass

abstract class BaseFragment<Action : BaseAction, ViewState : BaseViewState<out BaseViewStateData>, ViewModel : BaseViewModel<Action, ViewState>>(
    @LayoutRes layoutRes: Int,
    viewModelClass: KClass<out ViewModel>
) : BaseMVIFragment<Action, ViewState, ViewModel>(layoutRes, viewModelClass) {

    fun View.onClick(action: () -> Action) {
        setOnClickListener {
            postAction(action())
        }
    }

}