package com.saba21.demo.movies.base.activity.viewModel.abstractHandlers.permission

interface PermissionCommand<T> {
    val key: CommonPermissions
    val pendingPositiveAction: T
    val pendingNegativeAction: T
}
