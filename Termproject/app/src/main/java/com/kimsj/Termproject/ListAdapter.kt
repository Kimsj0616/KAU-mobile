package com.kimsj.Termproject

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView

class ListAdapter(val context: Context, val menus: ArrayList<String>, val prices: ArrayList<Int>) :
    BaseAdapter() {
    override fun getView(p0: Int, p1: View?, p2: ViewGroup?): View {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        val view: View = LayoutInflater.from(context).inflate(R.layout.activity_item, null)

        val menupic = view.findViewById<ImageView>(R.id.imageView)
        val menuname = view.findViewById<TextView>(R.id.menuname)
        val menuprice = view.findViewById<TextView>(R.id.menuprice)

        val menu = menus[p0]
        val price = prices[p0]

        menuname.text = menu
        menuprice.text = price.toString()

        return view
    }

    override fun getItem(p0: Int): Any {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        return menus[p0]
    }

    override fun getItemId(p0: Int): Long {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        return 0
    }

    override fun getCount(): Int {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        return menus.size
    }

}