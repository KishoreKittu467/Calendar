package com.kkapps.bubbles.di

import org.koin.core.context.startKoin
import org.koin.dsl.KoinAppDeclaration

fun initKoin(config: KoinAppDeclaration? = null) {
    startKoin {
        config?.invoke(this)
        modules(platformModules, domainModules, sharedModules)
        // todo: kmp not yet supporting lazyModules. Use it for featureModules when supported
        modules(featureModules)
    }
}