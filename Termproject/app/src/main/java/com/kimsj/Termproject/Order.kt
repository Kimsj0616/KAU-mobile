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

        val TableNo : Int = getIntent().getIntExtra("Table_Number", 1)
        println("Table No == ${TableNo}")

        var TableNumber : TextView = findViewById(R.id.TableNo)

        TableNumber.setText(TableNo.toString())



        OrderButton = findViewById(R.id.order_button)
    }
}