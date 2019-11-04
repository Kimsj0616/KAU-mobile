package com.kimsj.Termproject

import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_order.*

class Order : AppCompatActivity()
{
    var qttmenu1 = 0
    var qttmenu2 = 0
    var qttmenu3 = 0
    var qttmenu4 = 0
    var qttmenu5 = 0
    var total_price = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_order)

        var tableNumber : TextView = findViewById(R.id.TableNo)

        if (intent.hasExtra("Table_Number")) {
            val tableNo = intent.getIntExtra("Table_Number", 1)
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

        }
    }

}