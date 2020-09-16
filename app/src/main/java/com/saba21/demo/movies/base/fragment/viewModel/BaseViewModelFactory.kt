package com.saba21.demo.movies.base.fragment.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.saba21.demo.movies.base.di.scopes.FragmentScope
import dagger.Lazy
import javax.inject.Inject

@FragmentScope
class BaseViewModelFactory<VM : BaseViewModel<*, *>>
@Inject constructor(
    private val viewModel: Lazy<VM>
) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        @Suppress("UNCHECKED_CAST")
        return viewModel.get() as T
    }

}