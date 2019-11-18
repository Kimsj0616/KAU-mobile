package com.kimsj.Termproject

import android.content.Intent
import android.os.Bundle
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {
    private var scanQRBtn: ImageButton? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        scanQRBtn = findViewById(R.id.scanQR)

        scanQRBtn!!.setOnClickListener {
            val scanintent = Intent(this, ScanQR::class.java)

            startActivity(scanintent)
        }
    }
}

