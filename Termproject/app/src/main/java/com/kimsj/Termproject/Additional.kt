package com.kimsj.Termproject

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.*
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import kotlinx.android.synthetic.main.activity_add.*
import kotlinx.android.synthetic.main.activity_add.menu1qtt
import kotlinx.android.synthetic.main.activity_add.menu2qtt
import kotlinx.android.synthetic.main.activity_add.menu3qtt
import kotlinx.android.synthetic.main.activity_add.menu4qtt
import kotlinx.android.synthetic.main.activity_add.menu5qtt
import kotlinx.android.synthetic.main.activity_add.minusmenu1
import kotlinx.android.synthetic.main.activity_add.minusmenu2
import kotlinx.android.synthetic.main.activity_add.minusmenu3
import kotlinx.android.synthetic.main.activity_add.minusmenu4
import kotlinx.android.synthetic.main.activity_add.minusmenu5
import kotlinx.android.synthetic.main.activity_add.plusmenu1
import kotlinx.android.synthetic.main.activity_add.plusmenu2
import kotlinx.android.synthetic.main.activity_add.plusmenu3
import kotlinx.android.synthetic.main.activity_add.plusmenu4
import kotlinx.android.synthetic.main.activity_add.plusmenu5
import kotlinx.android.synthetic.main.activity_add.totalprice
import kotlinx.android.synthetic.main.activity_order.menu1
import kotlinx.android.synthetic.main.activity_order.menu1price
import kotlinx.android.synthetic.main.activity_order.menu2
import kotlinx.android.synthetic.main.activity_order.menu2price
import kotlinx.android.synthetic.main.activity_order.menu3
import kotlinx.android.synthetic.main.activity_order.menu3price
import kotlinx.android.synthetic.main.activity_order.menu4
import kotlinx.android.synthetic.main.activity_order.menu4price
import kotlinx.android.synthetic.main.activity_order.menu5
import kotlinx.android.synthetic.main.activity_order.menu5price

class Additional : AppCompatActivity() {
    private var firebasedb : FirebaseDatabase = FirebaseDatabase.getInstance()
    private var ref : DatabaseReference = firebasedb.reference
    private var menuref : DatabaseReference = firebasedb.getReference("menulist")
    private var storage  = FirebaseStorage.getInstance()
    private var storageref = storage.getReferenceFromUrl("gs://monsterrat-ec078.appspot.com")
    private var pathReference : StorageReference = storageref.child("고구마치즈돈까스.jpg")

    var tablenumber : Int = 0
    var add_qttmenu1 = 0
    var add_qttmenu2 = 0
    var add_qttmenu3 = 0
    var add_qttmenu4 = 0
    var add_qttmenu5 = 0
    var pre_price : Int ?= 0
    var add_price : Int = 0
    var pics : ArrayList<Bitmap> = ArrayList()
    var menus : ArrayList<String> = ArrayList()
    var prices : ArrayList<Int> = ArrayList()

    private fun resizeBitmap(bitmap: Bitmap): Bitmap {
        var w : Int = bitmap.width
        var h : Int = bitmap.height
        //println("w: ${w}, h: ${h}")

        h = w

        //println("w: ${w}, h: ${h}")
        return Bitmap.createScaledBitmap(
            bitmap,
            w,
            h,
            false
        )
    }

    fun getlist(){
        storageref.listAll()
            .addOnSuccessListener { listResult ->
                listResult.items.forEach { item ->
                    item.getBytes(2048 * 4096).addOnSuccessListener  {
                        val bitmap = BitmapFactory.decodeByteArray(it, 0, it.size)
                        var resizedbitmap = resizeBitmap(bitmap)
                        pics.add(resizedbitmap)
                    }
                        .addOnFailureListener {
                            println("storage-read-fail-each")
                        }
                }
            }
            .addOnFailureListener {
                println("storage-read-fail")
            }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_add)

        add_pic1.setImageBitmap(pics[0])
        add_pic2.setImageBitmap(pics[1])
        add_pic3.setImageBitmap(pics[2])
        add_pic4.setImageBitmap(pics[3])
        add_pic5.setImageBitmap(pics[4])

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

                menu1.text = menus[0]
                menu2.text = menus[1]
                menu3.text = menus[2]
                menu4.text = menus[3]
                menu5.text = menus[4]

                menu1price.text = prices[0].toString()
                menu2price.text = prices[1].toString()
                menu3price.text = prices[2].toString()
                menu4price.text = prices[3].toString()
                menu5price.text = prices[4].toString()
            }
        })

        if(intent.hasExtra("tableNo")){
            tablenumber = intent.getIntExtra("tableNo", 0)
        }
        if(intent.hasExtra("total_price")){
            pre_price = intent.getIntExtra("total_price", 0)
        }

        plusmenu1.setOnClickListener {
            add_qttmenu1++
            add_price += menu1price.text.toString().toInt()
            totalprice.text = add_price.toString()
            menu1qtt.text = add_qttmenu1.toString()
        }

        plusmenu2.setOnClickListener {
            add_qttmenu2++
            add_price += menu2price.text.toString().toInt()
            totalprice.text = add_price.toString()
            menu2qtt.text = add_qttmenu2.toString()
        }

        plusmenu3.setOnClickListener {
            add_qttmenu3++
            add_price += menu3price.text.toString().toInt()
            totalprice.text = add_price.toString()
            menu3qtt.text = add_qttmenu3.toString()
        }

        plusmenu4.setOnClickListener {
            add_qttmenu4++
            add_price += menu4price.text.toString().toInt()
            totalprice.text = add_price.toString()
            menu4qtt.text = add_qttmenu4.toString()
        }

        plusmenu5.setOnClickListener {
            add_qttmenu5++
            add_price += menu5price.text.toString().toInt()
            totalprice.text = add_price.toString()
            menu5qtt.text = add_qttmenu5.toString()
        }

        minusmenu1.setOnClickListener {
            if(add_qttmenu1 == 0){
                Toast.makeText(this@Additional, "상품을 0개 이하로 주문하실 수 없습니다!!", Toast.LENGTH_LONG).show()
            }
            else{
                add_qttmenu1--
                add_price -= menu1price.text.toString().toInt()
                totalprice.text = add_price.toString()
                menu1qtt.text = add_qttmenu1.toString()
            }
        }

        minusmenu2.setOnClickListener {
            if(add_qttmenu2 == 0){
                Toast.makeText(this@Additional, "상품을 0개 이하로 주문하실 수 없습니다!!", Toast.LENGTH_LONG).show()
            }
            else{
                add_qttmenu2--
                add_price -= menu2price.text.toString().toInt()
                totalprice.text = add_price.toString()
                menu2qtt.text = add_qttmenu2.toString()
            }
        }

        minusmenu3.setOnClickListener {
            if(add_qttmenu3 == 0){
                Toast.makeText(this@Additional, "상품을 0개 이하로 주문하실 수 없습니다!!", Toast.LENGTH_LONG).show()
            }
            else{
                add_qttmenu3--
                add_price -= menu3price.text.toString().toInt()
                totalprice.text = add_price.toString()
                menu3qtt.text = add_qttmenu3.toString()
            }
        }

        minusmenu4.setOnClickListener {
            if(add_qttmenu4 == 0){
                Toast.makeText(this@Additional, "상품을 0개 이하로 주문하실 수 없습니다!!", Toast.LENGTH_LONG).show()
            }
            else{
                add_qttmenu4--
                add_price -= menu4price.text.toString().toInt()
                totalprice.text = add_price.toString()
                menu4qtt.text = add_qttmenu4.toString()
            }
        }

        minusmenu5.setOnClickListener {
            if(add_qttmenu5 == 0){
                Toast.makeText(this@Additional, "상품을 0개 이하로 주문하실 수 없습니다!!", Toast.LENGTH_LONG).show()
            }
            else{
                add_qttmenu5--
                add_price -= menu5price.text.toString().toInt()
                totalprice.text = add_price.toString()
                menu5qtt.text = add_qttmenu5.toString()
            }
        }

        reorder_button.setOnClickListener {
            val re_orderIntent = Intent(this@Additional, ResultOrder::class.java)

            re_orderIntent.putExtra("tableNo", tablenumber!!.toInt())
            re_orderIntent.putExtra("qttmenu1", add_qttmenu1)
            re_orderIntent.putExtra("qttmenu2", add_qttmenu2)
            re_orderIntent.putExtra("qttmenu3", add_qttmenu3)
            re_orderIntent.putExtra("qttmenu4", add_qttmenu4)
            re_orderIntent.putExtra("qttmenu5", add_qttmenu5)
            re_orderIntent.putExtra("total_price", add_price)

            startActivity(re_orderIntent)
        }
    }
}