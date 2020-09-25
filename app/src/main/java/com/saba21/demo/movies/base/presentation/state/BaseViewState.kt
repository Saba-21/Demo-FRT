package com.saba21.demo.movies.base.presentation.state

abstract class BaseViewState<Data : BaseViewStateData>(initialState: Data) {

    var currentState: Data = initialState

    fun updateCurrentState(newViewState: BaseViewState<*>) = this.apply {
        @Suppress("UNCHECKED_CAST")
        this.currentState = stateReducer(newViewState.currentState as Data)
    }

    protected abstract val stateReducer: ((Data) -> Data)
}
