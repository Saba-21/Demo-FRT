package com.saba21.demo.movies.main.activity.handlers

import android.app.AlertDialog
import android.content.Context

class MainUtilitiesHandler(private val context: Context) {

    fun showAlert(messageRes: Int) {
        AlertDialog.Builder(context).setMessage(messageRes).show()
    }

}