package com.example.chart

import android.content.Context

fun Float.dp2px(context: Context):Float{
    return context.resources.displayMetrics.density * this
}

fun Int.dp2px(context: Context):Int{
    return (context.resources.displayMetrics.density * this).toInt()
}