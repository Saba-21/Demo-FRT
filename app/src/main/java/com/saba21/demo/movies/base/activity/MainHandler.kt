package com.saba21.demo.movies.base.activity

import com.saba21.demo.domain.models.MovieModel

interface MainHandler {

    fun showAlert(messageRes: Int)
    fun showAlertForResult(messageRes: Int, positiveClick: () -> Unit)
    fun showLoader()
    fun hideLoader()
    fun goToMovieDetails(movieModel: MovieModel)
    fun popBackStack()

}