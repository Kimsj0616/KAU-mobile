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

class ResultOrder : AppCompatActivity(){

    private var firebasedb : FirebaseDatabase = FirebaseDatabase.getInstance()
    private var ref : DatabaseReference = firebasedb.reference
    private var storage : FirebaseStorage = FirebaseStorage.getInstance("gs://monsterrat-ec078.appspot.com/")
    private var storageref : StorageReference = storage.getReference()
    private var pathReference : StorageReference = storageref.child("images/image.jpg")

    var tablenumber :Int? = null
    var pay : Button?=null
    var menuqtt1 :Int? = 0
    var menuqtt2 :Int? = 0
    var menuqtt3 :Int? = 0
    var menuqtt4 :Int? = 0
    var menuqtt5 :Int? = 0
    var totalprice :Int? = 0
    var count : Int? = null
    var menus : ArrayList<String> = ArrayList()
    var prices : ArrayList<Int> = ArrayList()



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_resultorder)

        pay = findViewById(R.id.pay)
        pay!!.setOnClickListener(object : View.OnClickListener {
            override fun onClick(p0: View?) {

                var intent : Intent = Intent(this@ResultOrder,PayActivity::class.java)

                startActivity(intent)

            }
        })

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
            total.text = totalprice.toString()
        }

        addbutton.setOnClickListener {
            val add_intent = Intent(this, Additional::class.java)

            add_intent.putExtra("tableNo", tablenumber)
            add_intent.putExtra("total_price", totalprice)

            startActivity(add_intent)
        }

        ref.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onCancelled(p0: DatabaseError) {


            }

            override fun onDataChange(p0: DataSnapshot) {

                count = p0.child("tablecount").value.toString().toInt()
                //count=0
                val order = OrderDB(tablenumber,menuqtt1,menuqtt2,menuqtt3,menuqtt4,menuqtt5)

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