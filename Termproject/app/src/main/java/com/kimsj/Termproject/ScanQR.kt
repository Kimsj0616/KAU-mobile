package com.kimsj.Termproject

import android.app.Activity
import android.content.Intent;
import android.net.Uri
import android.os.Bundle;
import android.view.View
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;


class ScanQR : AppCompatActivity()
{
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_scan_qr)

        val scanner = IntentIntegrator(this)
        scanner.setOrientationLocked(false)


        scanner.initiateScan()




    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (resultCode == Activity.RESULT_OK){
            val result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data)
            if (result != null) {
                if (result.contents == null) {
                    Toast.makeText(this, "Cancelled", Toast.LENGTH_LONG).show()
                } else {
                    val intent: Intent = Intent(Intent.ACTION_VIEW, Uri.parse(result.contents))
                    startActivity(intent)
                }
            } else {
                super.onActivityResult(requestCode, resultCode, data)
            }
        }
    }

}