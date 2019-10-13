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
import android.widget.Toast
import com.google.zxing.integration.android.IntentResult



class MainActivity : AppCompatActivity() {

    private var createQRBtn: Button? = null
    private var scanQRBtn: Button? = null

    private var firebasedb : FirebaseDatabase = FirebaseDatabase.getInstance()
    private  var ref : DatabaseReference = firebasedb.reference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Toast.makeText(this@MainActivity,"asd",Toast.LENGTH_LONG).show()
        ref.setValue("김세종")
        ref.child("name").setValue("김세종")


        createQRBtn = findViewById(R.id.createQR)
        scanQRBtn = findViewById(R.id.scanQR)

        createQRBtn!!.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {

                val intent : Intent = Intent(this@MainActivity,CreateQR::class.java)

                startActivity(intent!!)
            }
    })

        scanQRBtn!!.setOnClickListener(object : View.OnClickListener {
            override fun onClick(p0: View?) {
                val intent : Intent = Intent(this@MainActivity,ScanQR::class.java)

                startActivity(intent)
            }
        })
    }
}
