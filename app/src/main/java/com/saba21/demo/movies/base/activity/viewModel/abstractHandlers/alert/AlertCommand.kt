package com.saba21.demo.movies.base.activity.viewModel.abstractHandlers.alert

interface AlertCommand<T> {
    val pendingAction: T
}
