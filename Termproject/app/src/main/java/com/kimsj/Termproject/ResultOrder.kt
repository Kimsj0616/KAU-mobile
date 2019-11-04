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
    var menu1 :String? = "돈까스"
    var menu2 :String? = "치즈돈까스"
    var menu3 :String? = "고구마치즈돈까스"
    var menu4 :String? = "스파게티"
    var menu5 :String? = "콜라"



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        setContentView(R.layout.activity_resultorder)

        if (intent.hasExtra("Table_Number")) {
            tablenumber = intent.getIntExtra("Table_Number", 1)
        }
        /*if (intent.hasExtra("qttmenu1")) {
            menu1 = intent.getStringExtra("qttmenu1")
        }
        if (intent.hasExtra("qttmenu2")) {
            menu2 = intent.getStringExtra("qttmenu2")
        }
        if (intent.hasExtra("qttmenu3")) {
            menu3 = intent.getStringExtra("qttmenu3")
        }

        if (intent.hasExtra("qttmenu4")) {
            menu4 = intent.getStringExtra("qttmenu4")
        }
        if (intent.hasExtra("qttmenu5")) {
            menu5 = intent.getStringExtra("qttmenu5")
        }*/



        val order = OrderDB(tablenumber,menu1,menu2,menu3,menu4,menu5)
        ref.setValue(order)

    }
}