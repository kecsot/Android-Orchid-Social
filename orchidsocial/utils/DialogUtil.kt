package com.kecsot.orchidsocial.utils

import android.content.Context
import android.content.DialogInterface
import android.support.v7.app.AlertDialog
import java.util.concurrent.atomic.AtomicBoolean

/**
 * Created by kecsotamas on 2018. 03. 19..
 */
class DialogUtil {

    companion object {
        private lateinit var INSTANCE: DialogUtil
        private val initialized = AtomicBoolean()

        val instance: DialogUtil get() = INSTANCE

        fun initialize() {
            if(initialized.getAndSet(true)) {
                INSTANCE = DialogUtil()
            }
        }
    }

    public fun showCloseableDialog(context: Context,message: String) {
        AlertDialog.Builder(context).setMessage(message)
                .setPositiveButton(android.R.string.ok, DialogInterface.OnClickListener { dialog, id -> })
                .show()
    }
}