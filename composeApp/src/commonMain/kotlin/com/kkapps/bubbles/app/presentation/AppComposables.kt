package com.kkapps.bubbles.app.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp


@Composable
fun Bar(value: Int, max: Int, height: Int = 16) {
    val clamped = value.coerceIn(0, max.coerceAtLeast(1))
    val fraction = clamped.toFloat() / max.coerceAtLeast(1)
    Row(
        Modifier
            .fillMaxWidth()
            .height(height.dp)
            .background(MaterialTheme.colorScheme.surfaceVariant)
    ) {
        Surface(
            modifier = Modifier
                .fillMaxWidth(fraction)
                .fillMaxSize(),
            color = MaterialTheme.colorScheme.primary
        ) {}
    }
}