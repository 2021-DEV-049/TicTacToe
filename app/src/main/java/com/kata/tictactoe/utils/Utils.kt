package com.kata.tictactoe.utils

import android.view.View
import androidx.core.content.ContextCompat
import com.google.android.material.snackbar.Snackbar
import com.kata.tictactoe.R

fun View.showSnackBar(message: String, errorMessage: Boolean) {
    val snackBar =
        Snackbar.make(rootView.findViewById(android.R.id.content), message, Snackbar.LENGTH_SHORT)
    val snackBarView = snackBar.view

    if (errorMessage) {
        snackBarView.setBackgroundColor(
            ContextCompat.getColor(
                this.context,
                R.color.colorSnackBarError
            )
        )
    } else {
        snackBarView.setBackgroundColor(
            ContextCompat.getColor(
                this.context,
                R.color.colorSnackBarSuccess
            )
        )
    }
    snackBar.show()
}