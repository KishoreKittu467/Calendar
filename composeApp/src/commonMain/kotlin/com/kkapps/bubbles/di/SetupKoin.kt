package com.kkapps.bubbles.di

import org.koin.core.context.startKoin

fun initKoin() {
    startKoin {
        modules(platformModules, sharedModules)
    }
}