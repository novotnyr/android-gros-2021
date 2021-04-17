package com.github.novotnyr.android.gros

import android.Manifest
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.content.PermissionChecker

class MainActivity : AppCompatActivity() {
    private lateinit var payButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        payButton = findViewById(R.id.payButton)
    }

    fun onPayButtonClick(view: View) {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.SEND_SMS)
            == PermissionChecker.PERMISSION_GRANTED
        ) {
            Log.i("Gros", "Permission granted. Sending SMS")
            TODO("Send SMS")
        } else {
            TODO("Permission not granted. Will not send SMS")
        }
    }
}