package com.kimsj.Termproject

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageButton
import android.widget.TextView
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class ResultOrder : AppCompatActivity(){

    private var firebasedb : FirebaseDatabase = FirebaseDatabase.getInstance()
    private  var ref : DatabaseReference = firebasedb.reference

    var menu1 :String? = null
    var menu2 :String? = null
    var menu3 :String? = null
    var menu4 :String? = null
    var menu5 :String? = null


    class orderData{
        val tablenumber :Int? = null
        val menu1 :String? = null
        val menu2 :String? = null
        val menu3 :String? = null
        val menu4 :String? = null
        val menu5 :String? = null

    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        var order = orderData()
        ref.setValue(order)

    }
}