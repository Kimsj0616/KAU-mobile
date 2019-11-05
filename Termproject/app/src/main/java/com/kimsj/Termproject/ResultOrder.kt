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
    private var ref : DatabaseReference = firebasedb.reference

    var tablenumber :Int? = null
    var menu1 :Int? = 0
    var menu2 :Int? = 0
    var menu3 :Int? = 0
    var menu4 :Int? = 0
    var menu5 :Int? = 0



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        setContentView(R.layout.activity_resultorder)

        if (intent.hasExtra("tableNo")) {
            tablenumber = intent.getIntExtra("tableNo", 0)
        }
        if (intent.hasExtra("qttmenu1")) {
            menu1 = intent.getIntExtra("qttmenu1",0)
        }
        if (intent.hasExtra("qttmenu2")) {
            menu2 = intent.getIntExtra("qttmenu2",0)
        }
        if (intent.hasExtra("qttmenu3")) {
            menu3 = intent.getIntExtra("qttmenu3",0)
        }

        if (intent.hasExtra("qttmenu4")) {
            menu4 = intent.getIntExtra("qttmenu4",0)
        }
        if (intent.hasExtra("qttmenu5")) {
            menu5 = intent.getIntExtra("qttmenu5",0)
        }



        val order = OrderDB(tablenumber,menu1,menu2,menu3,menu4,menu5)
        ref.child("table list").setValue(order.map1)

    }
}