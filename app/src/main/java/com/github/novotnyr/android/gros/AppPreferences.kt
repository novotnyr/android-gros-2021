package com.github.novotnyr.android.gros

import android.content.Context
import androidx.core.content.edit
import androidx.preference.PreferenceManager
import java.util.*

class AppPreferences(context: Context) {
    private val LAST_PAYMENT = "last-payment"

    private val preferences = PreferenceManager.getDefaultSharedPreferences(context)

    var lastPaymentDate: Date
        get() {
            val lastPayment = preferences.getLong(LAST_PAYMENT, Long.MAX_VALUE)
            return Date(lastPayment)
        }

        set(value) = preferences.edit {
            putLong(LAST_PAYMENT, value.time)
        }
}