package com.kimsj.Termproject

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button

class MainActivity : AppCompatActivity() {

    private var createQRBtn: Button? = null
    private var scanQRBtn: Button? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        createQRBtn = findViewById(R.id.createQR)
        scanQRBtn = findViewById(R.id.scanQR)

        createQRBtn!!.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {

                val intent : Intent = Intent(this@MainActivity,CreateQR::class.java)

                startActivity(intent!!)
            }
    })

        scanQRBtn!!.setOnClickListener(View.OnClickListener {
            val intent = Intent(this@MainActivity, ScanQR::class.java)
            startActivity(intent)
        })

    }
}
