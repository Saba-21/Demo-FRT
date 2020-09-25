package com.saba21.demo.movies.base.activity.viewModel.abstractHandlers.permission

interface BasePermission<T> {
    val key: CommonPermissions
    val pendingPositiveAction: T
    val pendingNegativeAction: T
}
