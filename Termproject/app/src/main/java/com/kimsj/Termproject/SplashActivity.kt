package com.kimsj.Termproject

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        try {
            Thread.sleep(2000)
            val splashintent = Intent(this, MainActivity::class.java)
            startActivity(splashintent)
            finish()
        }
        catch(e : Exception){
            return;
        }
    }
}