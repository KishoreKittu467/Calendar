package com.kkapps.bubbles

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.window.ComposeViewport
import com.kkapps.bubbles.app.App
import com.kkapps.bubbles.di.initKoin
import kotlinx.browser.document
import org.jetbrains.skiko.wasm.onWasmReady

@OptIn(ExperimentalComposeUiApi::class)
fun main() {
    onWasmReady {
        initKoin()
        ComposeViewport(document.body!!) {
            Box(Modifier.fillMaxSize()) {
                App()
            }
        }
    }
}