package com.kimsj.Termproject

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.*
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import kotlinx.android.synthetic.main.activity_order.*


class Order : AppCompatActivity()
{
    private var firebasedb : FirebaseDatabase = FirebaseDatabase.getInstance()
    private var ref : DatabaseReference = firebasedb.reference
    private var menuref : DatabaseReference = firebasedb.getReference("menulist")
    private var storage  = FirebaseStorage.getInstance()
    private var storageref = storage.getReferenceFromUrl("gs://monsterrat-ec078.appspot.com")
    private var pathReference : StorageReference = storageref.child("고구마치즈돈까스.jpg")

    var qttmenu1 = 0
    var qttmenu2 = 0
    var qttmenu3 = 0
    var qttmenu4 = 0
    var qttmenu5 = 0
    var total_price = 0
    var tableNo: Int ?= 0
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
                        if (item.name == "고구마치즈돈까스.jpg"){
                            pic1.setImageBitmap(resizedbitmap)
                        }
                        else if (item.name == "돈까스.jpg"){
                            pic2.setImageBitmap(resizedbitmap)
                        }
                        else if (item.name == "스파게티.jpg"){
                            pic3.setImageBitmap(resizedbitmap)
                        }
                        else if (item.name == "치즈돈까스.jpg"){
                            pic4.setImageBitmap(resizedbitmap)
                        }
                        else if (item.name == "콜라.jpg"){
                            pic5.setImageBitmap(resizedbitmap)
                        }
                    }.addOnFailureListener {
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

        setContentView(R.layout.activity_order)

        var tableNumber : TextView = findViewById(R.id.TableNo)

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

        getlist()

        if (intent.hasExtra("Table_Number")) {
            tableNo = intent.getIntExtra("Table_Number", 1)
            println("table : ${tableNo}")
            tableNumber.text = tableNo.toString()
        }

        plusmenu1.setOnClickListener {
            qttmenu1++
            total_price += menu1price.text.toString().toInt()
            totalprice.text = total_price.toString()
            menu1qtt.text = qttmenu1.toString()
        }

        plusmenu2.setOnClickListener {
            qttmenu2++
            total_price += menu2price.text.toString().toInt()
            totalprice.text = total_price.toString()
            menu2qtt.text = qttmenu2.toString()
        }

        plusmenu3.setOnClickListener {
            qttmenu3++
            total_price += menu3price.text.toString().toInt()
            totalprice.text = total_price.toString()
            menu3qtt.text = qttmenu3.toString()
        }

        plusmenu4.setOnClickListener {
            qttmenu4++
            total_price += menu4price.text.toString().toInt()
            totalprice.text = total_price.toString()
            menu4qtt.text = qttmenu4.toString()
        }

        plusmenu5.setOnClickListener {
            qttmenu5++
            total_price += menu5price.text.toString().toInt()
            totalprice.text = total_price.toString()
            menu5qtt.text = qttmenu5.toString()
        }

        minusmenu1.setOnClickListener {
            if(qttmenu1 == 0){
                Toast.makeText(this@Order, "상품을 0개 이하로 주문하실 수 없습니다!!", Toast.LENGTH_LONG).show()
            }
            else{
                qttmenu1--
                total_price -= menu1price.text.toString().toInt()
                totalprice.text = total_price.toString()
                menu1qtt.text = qttmenu1.toString()
            }
        }

        minusmenu2.setOnClickListener {
            if(qttmenu2 == 0){
                Toast.makeText(this@Order, "상품을 0개 이하로 주문하실 수 없습니다!!", Toast.LENGTH_LONG).show()
            }
            else{
                qttmenu2--
                total_price -= menu2price.text.toString().toInt()
                totalprice.text = total_price.toString()
                menu2qtt.text = qttmenu2.toString()
            }
        }

        minusmenu3.setOnClickListener {
            if(qttmenu3 == 0){
                Toast.makeText(this@Order, "상품을 0개 이하로 주문하실 수 없습니다!!", Toast.LENGTH_LONG).show()
            }
            else{
                qttmenu3--
                total_price -= menu3price.text.toString().toInt()
                totalprice.text = total_price.toString()
                menu3qtt.text = qttmenu3.toString()
            }
        }

        minusmenu4.setOnClickListener {
            if(qttmenu4 == 0){
                Toast.makeText(this@Order, "상품을 0개 이하로 주문하실 수 없습니다!!", Toast.LENGTH_LONG).show()
            }
            else{
                qttmenu4--
                total_price -= menu4price.text.toString().toInt()
                totalprice.text = total_price.toString()
                menu4qtt.text = qttmenu4.toString()
            }
        }

        minusmenu5.setOnClickListener {
            if(qttmenu5 == 0){
                Toast.makeText(this@Order, "상품을 0개 이하로 주문하실 수 없습니다!!", Toast.LENGTH_LONG).show()
            }
            else{
                qttmenu5--
                total_price -= menu5price.text.toString().toInt()
                totalprice.text = total_price.toString()
                menu5qtt.text = qttmenu5.toString()
            }
        }

        order_button.setOnClickListener {
            val orderIntent = Intent(this@Order, ResultOrder::class.java)

            orderIntent.putExtra("tableNo", tableNo!!.toInt())
            orderIntent.putExtra("qttmenu1", qttmenu1)
            orderIntent.putExtra("qttmenu2", qttmenu2)
            orderIntent.putExtra("qttmenu3", qttmenu3)
            orderIntent.putExtra("qttmenu4", qttmenu4)
            orderIntent.putExtra("qttmenu5", qttmenu5)
            orderIntent.putExtra("total_price", total_price)

            startActivity(orderIntent)
        }
    }
}