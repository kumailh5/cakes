package com.kumail.cakes.util

import android.content.Context
import android.content.DialogInterface
import androidx.appcompat.app.AlertDialog
import com.kumail.cakes.R

/**
 * Created by kumailhussain on 16/11/2021.
 */
fun showErrorDialog(
    context: Context,
    message: String,
    listener: (DialogInterface, Int) -> Unit = { _, _ -> }
) {
    AlertDialog.Builder(context)
        .setTitle(R.string.error)
        .setMessage(message)
        .setPositiveButton(R.string.ok) { dialogInterface, i ->
            run {
                dialogInterface.dismiss()
                listener(dialogInterface, i)
            }
        }
        .setNeutralButton(R.string.retry) { dialogInterface, i ->
            run {
                dialogInterface.dismiss()
                listener(dialogInterface, i)
            }
        }
        .setCancelable(true)
        .create()
        .show()
}

fun showPopupDialog(context: Context, title: String, description: String) {
    AlertDialog.Builder(context)
        .setTitle(title)
        .setMessage(description)
        .setPositiveButton(R.string.ok) { dialogInterface, i ->
            run {
                dialogInterface.dismiss()
            }
        }
        .setCancelable(true)
        .create()
        .show()
}