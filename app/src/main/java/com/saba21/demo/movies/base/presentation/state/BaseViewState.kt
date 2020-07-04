package com.saba21.demo.movies.base.presentation.state

interface BaseViewState<Data : BaseViewStateData> {

    val currentState: Data

}