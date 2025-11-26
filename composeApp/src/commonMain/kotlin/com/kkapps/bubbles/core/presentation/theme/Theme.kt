package com.kkapps.bubbles.core.presentation.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

enum class ThemeMode { System, Light, Dark }

@Composable
fun BubblesTheme(
    themeMode: ThemeMode,
    content: @Composable () -> Unit
) {
    val dark = when (themeMode) {
        ThemeMode.System -> isSystemInDarkTheme()
        ThemeMode.Light -> false
        ThemeMode.Dark -> true
    }
    val colors = if (dark) darkColorScheme() else lightColorScheme()
    MaterialTheme(
        colorScheme = colors,
        content = content
    )
}

object Colors {
    val example1Selection = Color(0xFFFCCA3E)
    val example1Bg = Color(0xFF3A284C)
    val example1BgLight = Color(0xFF433254)
    val example1BgSecondary = Color(0xFF51356E)
    val example1WhiteLight = Color(0x4DFFFFFF)
    val example4GrayPast = Color(0xFFBEBEBE)
    val example4Gray = Color(0xFF474747)
    val example5PageBgColor = Color(0xFF0E0E0E)
    val example5ItemViewBgColor = Color(0xFF1B1B1B)
    val example5ToolbarColor = Color(0xFF282828)
    val example5TextGrey = Color(0xFFDCDCDC)
    val example5TextGreyLight = Color(0xFF616161)
    val example6MonthBgColor = Color(0xFFB2EBF2)
    val example6MonthBgColor2 = Color(0xFFF2C4B2)
    val example6MonthBgColor3 = Color(0xFFB2B8F2)
    val example7Yellow = Color(0xFFFFEB3B)
    val primary = Color(0xFF3F51B5)
    val accent = Color(0xFFFF4081)
}