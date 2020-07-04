package com.saba21.demo.movies.base.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dagger.Lazy
import javax.inject.Inject

class BaseViewModelFactory<VM : BaseViewModel<*, *>>
@Inject constructor(
    private val viewModel: Lazy<VM>
) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        @Suppress("UNCHECKED_CAST")
        return viewModel.get() as T
    }

}