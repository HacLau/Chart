package com.example.chart

import android.app.Application
lateinit var appContext:Application

class App:Application() {
    override fun onCreate() {
        super.onCreate()
        appContext = this
    }
}