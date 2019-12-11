package com.kimsj.Termproject

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.*
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import kotlinx.android.synthetic.main.activity_resultorder.*
import kr.co.bootpay.BootpayAnalytics

class Bill : AppCompatActivity(){

    private var firebasedb : FirebaseDatabase = FirebaseDatabase.getInstance()
    private var ref : DatabaseReference = firebasedb.reference

    var tablenumber :Int? = null
    var menuqtt1 :Int? = 0
    var menuqtt2 :Int? = 0
    var menuqtt3 :Int? = 0
    var menuqtt4 :Int? = 0
    var menuqtt5 :Int? = 0
    var totalprice :Int? = 0

    var menus : ArrayList<String> = ArrayList()
    var prices : ArrayList<Int> = ArrayList()




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bill)
        ref.child("menulist").child("돈까스").setValue("7000")
        ref.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onCancelled(p0: DatabaseError) {


            }
            override fun onDataChange(p0: DataSnapshot) {

                for(i in p0.child("menulist").children)
                {
                    menus.add(i.key.toString())
                    prices.add(i.getValue(true).toString().toInt())
                }

                name1.text = menus[0]
                name2.text = menus[1]
                name3.text = menus[2]
                name4.text = menus[3]
                name5.text = menus[4]
            }
        })


        if (intent.hasExtra("tableNo")) {
            tablenumber = intent.getIntExtra("tableNo", 0)
            tableno.text = "TABLE ${tablenumber}"
        }
        if (intent.hasExtra("qttmenu1")) {
            menuqtt1 = intent.getIntExtra("qttmenu1",0)
            qtt1.text = menuqtt1.toString()
        }
        if (intent.hasExtra("qttmenu2")) {
            menuqtt2 = intent.getIntExtra("qttmenu2",0)
            qtt2.text = menuqtt2.toString()
        }
        if (intent.hasExtra("qttmenu3")) {
            menuqtt3 = intent.getIntExtra("qttmenu3",0)
            qtt3.text = menuqtt3.toString()
        }
        if (intent.hasExtra("qttmenu4")) {
            menuqtt4 = intent.getIntExtra("qttmenu4",0)
            qtt4.text = menuqtt4.toString()
        }
        if (intent.hasExtra("qttmenu5")) {
            menuqtt5 = intent.getIntExtra("qttmenu5",0)
            qtt5.text = menuqtt5.toString()
        }
        if (intent.hasExtra("total_price")) {
            totalprice = intent.getIntExtra("total_price",0)
            println("###########################${totalprice}")
            total.text = totalprice.toString()
        }

    }




}