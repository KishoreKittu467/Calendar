package com.kkapps.bubbles.app

import android.app.Application
import com.kkapps.bubbles.di.initKoin

class BubblesApp: Application() {
    override fun onCreate() {
        super.onCreate()
        initKoin()
    }
}