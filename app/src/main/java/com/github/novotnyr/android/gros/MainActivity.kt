package com.github.novotnyr.android.gros

import android.Manifest.permission.SEND_SMS
import android.os.Bundle
import android.telephony.SmsManager
import android.text.format.DateUtils
import android.view.View
import android.widget.Button
import androidx.activity.result.contract.ActivityResultContracts.RequestPermission
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat.checkSelfPermission
import androidx.core.content.PermissionChecker.PERMISSION_GRANTED
import com.google.android.material.snackbar.Snackbar
import java.util.*

class MainActivity : AppCompatActivity() {
    private val requestPermissionLauncher = registerForActivityResult(RequestPermission()) { isGranted ->
        if (isGranted) {
            sendSms()
        } else {
            snackBar("Povolenie posielať platby cez SMS nebolo pridelené", {})
        }
    }

    private lateinit var payButton: Button

    private lateinit var appPreferences: AppPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        payButton = findViewById(R.id.payButton)

        appPreferences = AppPreferences(this)
    }

    fun onPayButtonClick(view: View) {
        when (checkSelfPermission(this, SEND_SMS)) {
            PERMISSION_GRANTED -> sendSms()
            else -> requestSmsPermission()
        }
    }

    private fun requestSmsPermission() {
        if (shouldShowRequestPermissionRationale(SEND_SMS)) {
            snackBar("Groš potrebuje povolenie pre odosielanie SMS správ kvôli platbám") {
                requestPermissionLauncher.launch(SEND_SMS)
            }
        } else {
            requestPermissionLauncher.launch(SEND_SMS)
        }
    }

    fun sendSms() {
        val smsManager = SmsManager.getDefault()
        smsManager.sendTextMessage("5556",
            null,
            "KE-123AB A4",
            null,
            null);

        appPreferences.lastPaymentDate = Date()
    }

    fun snackBar(message: String, action: () -> Unit) {
        val rootView = findViewById<View>(android.R.id.content)
        Snackbar.make(rootView, message, Snackbar.LENGTH_LONG)
            .setAction("OK") {
                action.invoke()
            }
            .show()
    }

    private fun refreshButton() {
        val lastPaymentDate = appPreferences.lastPaymentDate
        if (lastPaymentDate.after(Date())) {
            return
        }
        val period = DateUtils.getRelativeTimeSpanString(
            lastPaymentDate.time,
            Date().time,
            DateUtils.SECOND_IN_MILLIS
        )
        payButton.text = period
    }
}