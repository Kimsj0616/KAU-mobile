package com.kimsj.Termproject

import android.content.Intent
import android.os.Bundle
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase


class MainActivity : AppCompatActivity() {

    private var scanQRBtn: ImageButton? = null

    private var firebasedb: FirebaseDatabase = FirebaseDatabase.getInstance()
    private var ref: DatabaseReference = firebasedb.reference

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

