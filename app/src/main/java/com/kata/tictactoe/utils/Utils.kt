package com.kata.tictactoe.utils

import android.content.Context
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
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

fun Context.alertDialog(
    positiveText: String = "Ok",
    negativeText: String = "",
    message: String = "",
    callback: () -> Unit
) {
    val dialog = AlertDialog.Builder(this)
        .setCancelable(false)
        .setMessage(message)
        .setPositiveButton(positiveText) { _, _ ->
            callback()
        }
        .setNegativeButton(negativeText) { _, _ ->
        }
        .create()

    dialog.show()

    val textView = dialog.findViewById<View>(android.R.id.message) as TextView
    textView.textSize = 20f

}