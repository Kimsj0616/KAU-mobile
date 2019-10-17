package com.kimsj.Termproject

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.zxing.integration.android.IntentIntegrator
import android.R.attr.data
import android.widget.ImageButton
import android.widget.Toast
import com.google.zxing.integration.android.IntentResult



class MainActivity : AppCompatActivity() {

    private var scanQRBtn: ImageButton? = null

    private var firebasedb : FirebaseDatabase = FirebaseDatabase.getInstance()
    private  var ref : DatabaseReference = firebasedb.reference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        scanQRBtn = findViewById(R.id.scanQR)

        scanQRBtn!!.setOnClickListener(object : View.OnClickListener {
            override fun onClick(p0: View?) {
                val intent : Intent = Intent(this@MainActivity,ScanQR::class.java)

                startActivity(intent)
            }
        })
    }
}
