package com.kimsj.Termproject

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class Order : AppCompatActivity()
{
    private var OrderButton: Button? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_order)

        var tableNumber : TextView = findViewById(R.id.TableNo)

        if (intent.hasExtra("Table_Number")) {
            val tableNo = intent.getIntExtra("Table_Number", 1)
            println("table : ${tableNo}")
            tableNumber.text = tableNo.toString()
        }


        OrderButton = findViewById(R.id.order_button)

    }
}