package com.kkapps.bubbles.di

import Greeting
import org.koin.dsl.module

val appModule = module {
    single { Greeting() }
}