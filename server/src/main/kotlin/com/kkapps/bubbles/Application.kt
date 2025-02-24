package com.kkapps.bubbles

import Greeting
import SERVER_PORT
import com.kkapps.bubbles.di.appModule
import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.koin.ktor.plugin.Koin
import org.koin.ktor.ext.get

fun main() {
    embeddedServer(Netty, port = SERVER_PORT, host = "0.0.0.0") {
        configureKoin()
        configureRouting()
    }.start(wait = true)
}

fun Application.configureKoin() {
    install(Koin) {
        modules(appModule)
    }
}

fun Application.configureRouting() {
    val greeting: Greeting = get()
    routing {
        get("/") {
            call.respondText("Ktor: ${greeting.greet()}")
        }
    }
}