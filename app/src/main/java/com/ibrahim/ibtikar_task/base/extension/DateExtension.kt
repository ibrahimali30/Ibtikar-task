package com.ibrahim.ibtikar_task.base.extension

import android.annotation.SuppressLint
import java.text.SimpleDateFormat

@SuppressLint("SimpleDateFormat")
fun timeToFormattedString(time: Long):String{
    val spf = SimpleDateFormat("dd MMM yyyy hh:mm aaa")
    return spf.format(time)
}

fun timeToFormattedStringAdapter(time: Long):String{
    val spf = SimpleDateFormat("hh:mm aaa dd/MM/yyyy ")
    return spf.format(time)
}