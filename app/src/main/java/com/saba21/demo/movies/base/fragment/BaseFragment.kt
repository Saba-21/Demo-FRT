package com.saba21.demo.movies.base.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.saba21.demo.movies.R
import com.saba21.demo.movies.base.fragment.viewModel.BaseViewModel
import com.saba21.demo.movies.base.presentation.action.BaseAction
import com.saba21.demo.movies.base.presentation.state.BaseViewState
import com.saba21.demo.movies.base.presentation.state.BaseViewStateData

abstract class BaseFragment<Action : BaseAction, ViewState : BaseViewState<out BaseViewStateData>, ViewModel : BaseViewModel<Action, ViewState>> :
    BaseMVIFragment<Action, ViewState, ViewModel>() {

    abstract val layoutRes: Int

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(layoutRes, container, false)
    }

    fun View.onClick(action: () -> Action) {
        setOnClickListener {
            postAction(action())
        }
    }

}