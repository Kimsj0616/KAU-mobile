package com.kimsj.Termproject

import android.os.Parcel
import android.os.Parcelable
import kotlinx.android.parcel.Parcelize


@Parcelize
class OrderDB(var tablenumber: Int?,
                   var qttmenu1:Int?,
                   var qttmenu2:Int?,
                   var qttmenu3:Int?,
                   var qttmenu4:Int?,
                   var qttmenu5:Int?) : Parcelable {

    val map1 = mapOf("Table number" to tablenumber,
        "돈까스" to qttmenu1,
        "치즈돈까스" to qttmenu2,
        "고구마치즈돈까스" to qttmenu3,
        "스파게티" to qttmenu4,
        "콜라" to qttmenu5)
}