package com.kkapps.bubbles.previews

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.kkapps.common.ui.cards.GlassCard

@Composable
@Preview
fun GlassCardPreview() {
    GlassCard {
        Text("This is a glass card")
    }
}