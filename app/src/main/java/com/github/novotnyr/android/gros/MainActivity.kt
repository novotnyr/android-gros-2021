package com.github.novotnyr.android.gros

import android.Manifest.permission.SEND_SMS
import android.os.Bundle
import android.telephony.SmsManager
import android.view.View
import android.widget.Button
import androidx.activity.result.contract.ActivityResultContracts.RequestPermission
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat.checkSelfPermission
import androidx.core.content.PermissionChecker.PERMISSION_GRANTED
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {
    private val requestPermissionLauncher = registerForActivityResult(RequestPermission()) { isGranted ->
        if (isGranted) {
            sendSms()
        } else {
            snackBar("Povolenie posielať platby cez SMS nebolo pridelené", {})
        }
    }

    private lateinit var payButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        payButton = findViewById(R.id.payButton)
    }

    fun onPayButtonClick(view: View) {
        when (checkSelfPermission(this, SEND_SMS)) {
            PERMISSION_GRANTED -> sendSms()
            else -> requestPermissionLauncher.launch(SEND_SMS)
        }
    }

    fun sendSms() {
        val smsManager = SmsManager.getDefault()
        smsManager.sendTextMessage("5556",
            null,
            "KE-123AB A4",
            null,
            null);
    }

    fun snackBar(message: String, action: () -> Unit) {
        val rootView = findViewById<View>(android.R.id.content)
        Snackbar.make(rootView, message, Snackbar.LENGTH_LONG)
            .setAction("OK") {
                action.invoke()
            }
            .show()
    }
}