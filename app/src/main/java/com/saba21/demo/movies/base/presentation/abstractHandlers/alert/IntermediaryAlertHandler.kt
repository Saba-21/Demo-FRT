package com.saba21.demo.movies.base.presentation.abstractHandlers.alert

interface IntermediaryAlertHandler {

    fun <T> handleAlert(item: BaseAlert<T>, callback: (T) -> Unit)

}