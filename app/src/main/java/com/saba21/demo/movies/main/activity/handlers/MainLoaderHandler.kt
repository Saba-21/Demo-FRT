package com.saba21.demo.movies.main.activity.handlers

import android.app.AlertDialog
import android.content.Context

class MainLoaderHandler(context: Context) {

    private val loader = AlertDialog.Builder(context).setMessage("loading").create()

    fun showLoader() {
        loader.show()
    }

    fun hideLoader() {
        loader.dismiss()
    }

}