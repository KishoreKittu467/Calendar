package com.kkapps.common.ui.cards

import androidx.compose.foundation.Image
import androidx.compose.material3.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import bubbles.deps.ui.generated.resources.Res
import bubbles.deps.ui.generated.resources.ic_movie_media_player
import org.jetbrains.compose.resources.painterResource

@Composable
fun GlassCard(
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
) {
    Card(
        modifier = modifier
    ) {
        Image(painterResource(Res.drawable.ic_movie_media_player), null)
        content()
    }
}