package com.kkapps.bubbles.app

import android.app.Application
import com.kkapps.bubbles.di.initKoin
import org.koin.android.ext.koin.androidContext

class BubblesApp: Application() {
    override fun onCreate() {
        super.onCreate()
        initKoin {
            androidContext(this@BubblesApp)
        }
    }
}