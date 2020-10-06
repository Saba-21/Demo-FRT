package com.saba21.demo.movies.base.activity.viewModel.abstractHandlers.permission

interface PermissionCommandHandler {

    fun <T> handlePermissionCommand(item: PermissionCommand<T>, callback: (T) -> Unit)
}
