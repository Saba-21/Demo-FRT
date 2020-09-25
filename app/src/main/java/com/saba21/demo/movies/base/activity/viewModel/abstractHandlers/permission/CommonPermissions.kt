package com.saba21.demo.movies.base.activity.viewModel.abstractHandlers.permission

import android.Manifest

enum class CommonPermissions(val key: String, val code: Int) {
    CALL(Manifest.permission.CALL_PHONE, 100)
}
