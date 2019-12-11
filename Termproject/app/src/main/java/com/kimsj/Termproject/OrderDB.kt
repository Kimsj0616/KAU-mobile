package com.kimsj.Termproject

import android.os.Bundle

import androidx.appcompat.app.AppCompatActivity

import com.google.firebase.database.*



class OrderDB(var tablenumber: Int?,
              var qttmenu1:Int?,
              var qttmenu2:Int?,
              var qttmenu3:Int?,
              var qttmenu4:Int?,
              var qttmenu5:Int?) : AppCompatActivity(){

    var menu1 : String ? = null
    var menu2 : String ? = null
    var menu3 : String ? = null
    var menu4 : String ? = null
    var menu5 : String ? = null

    var map1 = mapOf("Table number" to tablenumber,
        menu1 to qttmenu1,
        menu2 to qttmenu2,
        menu3 to qttmenu3,
        menu4 to qttmenu4,
        menu5 to qttmenu5)

    private var firebasedb : FirebaseDatabase = FirebaseDatabase.getInstance()
    private var ref : DatabaseReference = firebasedb.reference
    var menus : ArrayList<String> = ArrayList()
    var prices : ArrayList<Int> = ArrayList()



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        ref.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onCancelled(p0: DatabaseError) {

            }
            override fun onDataChange(p0: DataSnapshot) {

                for(i in p0.child("menulist").children)
                {
                    menus.add(i.key.toString())
                    prices.add(i.getValue(true).toString().toInt())
                }

                menu1 = menus[0]
                menu2 = menus[1]
                menu3 = menus[2]
                menu4 = menus[3]
                menu5 = menus[4]

            }
        })
    }

}