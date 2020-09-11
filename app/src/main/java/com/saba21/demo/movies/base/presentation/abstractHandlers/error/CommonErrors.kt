package com.saba21.demo.movies.base.presentation.abstractHandlers.error

import androidx.annotation.StringRes
import com.saba21.demo.movies.R
import retrofit2.HttpException
import java.net.SocketTimeoutException
import java.net.UnknownHostException

enum class CommonErrors(@StringRes val messageRes: Int) : BaseError {
    FAIL(R.string.error_message_general),
    API_ERROR(R.string.error_message_api),
    OFFLINE(R.string.error_message_offline),
    POOR_CONNECTION(R.string.error_message_poor_network);

    companion object {
        fun get(throwable: Throwable): CommonErrors {
            return when (throwable) {
                is HttpException -> API_ERROR
                is UnknownHostException -> OFFLINE
                is SocketTimeoutException -> POOR_CONNECTION
                else -> FAIL
            }
        }
    }

}