package com.kimsj.Termproject

import android.os.Parcel
import android.os.Parcelable
import kotlinx.android.parcel.Parcelize


@Parcelize
data class OrderDB(var tablenumber: Int?,
                   var menu1:String?,
                   var menu2:String?,
                   var menu3:String?,
                   var menu4:String?,
                   var menu5:String?) : Parcelable {

    var tableno = tablenumber
    var menu11 = menu1
    var menu22 = menu2
    var menu33 = menu3
    var menu44 = menu4
    var menu55 = menu5

}