package com.kkapps.common.ui.cards

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import bubbles.common.ui.generated.resources.Res
import bubbles.common.ui.generated.resources.ic_movie_media_player
import org.jetbrains.compose.resources.painterResource

@Composable
fun GlassCard(
    content: @Composable () -> Unit
) {
    Card(
        modifier = Modifier.padding(16.dp)
    ) {
        Image(painterResource(Res.drawable.ic_movie_media_player), null)
        content()
    }
}