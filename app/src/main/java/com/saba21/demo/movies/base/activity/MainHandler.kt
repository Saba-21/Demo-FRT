package com.saba21.demo.movies.base.activity

import com.saba21.demo.domain.models.MovieModel
import com.saba21.demo.movies.base.activity.viewModel.abstractHandlers.permission.CommonPermissions

interface MainHandler {

    fun getPermission(item: CommonPermissions, resultCallback: (Int) -> Unit)
    fun showAlert(messageRes: Int)
    fun showAlertForResult(messageRes: Int, positiveClick: () -> Unit)
    fun showLoader()
    fun hideLoader()
    fun goToMovieDetails(movieModel: MovieModel)
    fun popBackStack()

}