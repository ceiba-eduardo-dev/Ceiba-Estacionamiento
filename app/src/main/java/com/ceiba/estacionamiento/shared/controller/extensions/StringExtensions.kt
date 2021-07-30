package com.ceiba.estacionamiento.shared.controller.extensions

import android.content.Context
import com.google.android.material.dialog.MaterialAlertDialogBuilder

fun String.showMessage(context: Context) {
    MaterialAlertDialogBuilder(context)
        .setTitle("!ALERT")
        .setMessage(this)
        .setPositiveButton("Ok", null)
        .show()

}

fun String.showMessageContinue(context: Context, listener: () -> Unit) {
    MaterialAlertDialogBuilder(context)
        .setTitle("SUCCESS")
        .setMessage(this)
        .setPositiveButton("Ok") { _, _ -> listener() }
        .show()
}
