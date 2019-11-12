package com.kimsj.Termproject

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageButton
import android.widget.TextView
import com.google.firebase.database.*
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference

class ResultOrder : AppCompatActivity(){

    private var firebasedb : FirebaseDatabase = FirebaseDatabase.getInstance()
    private var ref : DatabaseReference = firebasedb.reference
    private var storage : FirebaseStorage = FirebaseStorage.getInstance("gs://monsterrat-ec078.appspot.com/")
    private var storageref : StorageReference = storage.getReference()
    private var pathReference : StorageReference = storageref.child("images/image.jpg")

    var tablenumber :Int? = null
    var menu1 :Int? = 0
    var menu2 :Int? = 0
    var menu3 :Int? = 0
    var menu4 :Int? = 0
    var menu5 :Int? = 0
    var count : Int?=null



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

        ref.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onCancelled(p0: DatabaseError) {


            }

            override fun onDataChange(p0: DataSnapshot) {

                count = p0.child("tablecount").value.toString().toInt()
                //count=0
                val order = OrderDB(tablenumber,menu1,menu2,menu3,menu4,menu5)

                ref.child("table list").child("${count}").setValue(order.map1)

                ref.child("tablecount").setValue(p0.child("tablecount").value.toString().toInt()+1)

            }
        })



        //read(2)

    }

    fun read(j : Int)
    {
        var i : Int = 0
        when(i)
        {
            1->
            {
                ref.child("menu").addChildEventListener(object : ChildEventListener {
                    override fun onCancelled(p0: DatabaseError) {
                    }

                    override fun onChildMoved(p0: DataSnapshot, p1: String?) {

                    }

                    override fun onChildChanged(p0: DataSnapshot, p1: String?) {
                        p0.child("순대국밥").getValue(true) //5000

                    }

                    override fun onChildAdded(p0: DataSnapshot, p1: String?) {

                        p0.child("콩나물국밥").getValue(true) //6000

                    }

                    override fun onChildRemoved(p0: DataSnapshot) {

                    }
                })
            }
            2->
            {
                ref.addListenerForSingleValueEvent(object : ValueEventListener {
                    override fun onCancelled(p0: DatabaseError) {

                    }

                    override fun onDataChange(p0: DataSnapshot) {
                        Log.d("getval",p0.child("table list").getValue(true).toString())
                    }
                })
            }
            3->
            {
                ref.addValueEventListener(object : ValueEventListener {
                    override fun onCancelled(p0: DatabaseError) {

                    }

                    override fun onDataChange(p0: DataSnapshot) {
                        Log.d("getval",p0.child("table list").getValue(true).toString())

                    }
                })
            }
        }

    }

}