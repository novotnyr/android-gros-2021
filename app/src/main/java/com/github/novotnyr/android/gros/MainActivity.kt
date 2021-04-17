package com.github.novotnyr.android.gros

import android.Manifest.permission.SEND_SMS
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat.checkSelfPermission
import androidx.core.content.PermissionChecker.PERMISSION_GRANTED

class MainActivity : AppCompatActivity() {
    private lateinit var payButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        payButton = findViewById(R.id.payButton)
    }

    fun onPayButtonClick(view: View) {
        when (checkSelfPermission(this, SEND_SMS)) {
            PERMISSION_GRANTED -> TODO("Send SMS")
            else -> TODO("Permission not granted. Will not send SMS")
        }
    }
}