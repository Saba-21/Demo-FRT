package com.saba21.demo.movies.main.activity

import com.saba21.demo.movies.base.activity.viewModel.BaseViewModel
import com.saba21.demo.movies.base.activity.viewModel.abstractHandlers.alert.BaseAlert
import com.saba21.demo.movies.base.activity.viewModel.abstractHandlers.error.BaseError
import com.saba21.demo.movies.base.activity.viewModel.abstractHandlers.error.CommonErrors
import com.saba21.demo.movies.base.activity.viewModel.abstractHandlers.loader.BaseLoader
import com.saba21.demo.movies.base.activity.viewModel.abstractHandlers.navigation.BaseNavigation
import com.saba21.demo.movies.presentation.movieDetails.MovieDetailsActions
import com.saba21.demo.movies.presentation.movieList.MovieListActions
import javax.inject.Inject

class MainViewModel @Inject constructor() : BaseViewModel() {

    override fun handleNavigation(navigation: BaseNavigation) {
        requireHandler {
            when (navigation) {
                is MovieListActions.Navigation.GoToDetails ->
                    it.goToMovieDetails(navigation.movieItem)
                is MovieDetailsActions.Navigation.GoBack ->
                    it.popBackStack()
            }
        }
    }

    override fun handleError(error: BaseError) {
        requireHandler {
            when (error) {
                is CommonErrors -> {
                    it.showAlert(error.messageRes)
                }
            }
        }
    }

    override fun handleLoader(item: BaseLoader) {
        requireHandler {
            if (item.visible)
                it.showLoader()
            else
                it.hideLoader()
        }
    }

    override fun <T> handleAlert(item: BaseAlert<T>, callback: (T) -> Unit) {
        requireHandler {
            it.showAlertForResult(android.R.string.untitled) {
                callback.invoke(item.pendingAction)
            }
        }
    }

}