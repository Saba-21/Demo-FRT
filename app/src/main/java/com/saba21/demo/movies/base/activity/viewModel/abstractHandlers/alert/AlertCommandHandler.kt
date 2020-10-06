package com.saba21.demo.movies.base.activity.viewModel.abstractHandlers.alert

interface AlertCommandHandler {

    fun <T> handleAlertCommand(item: AlertCommand<T>, callback: (T) -> Unit)

}
