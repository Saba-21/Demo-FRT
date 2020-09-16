package com.saba21.demo.movies.base.activity.viewModel.abstractHandlers.permission

interface IntermediaryPermissionHandler {

    fun <T> handlePermission(item: BasePermission<T>, callback: (T) -> Unit)

}