package com.kkapps.common.ui.components.icons

import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.PathBuilder
import androidx.compose.ui.unit.dp

val StarIcon: ImageVector
    get() = ImageVector.Builder(
        name = "Star", defaultWidth = 24.dp, defaultHeight = 24.dp, viewportWidth = 24f, viewportHeight = 24f
    ).apply {
        addPath(
            fill = Brush.linearGradient(
                colors = listOf(Color.Yellow, Color.Black),
            ),
            pathData = PathBuilder().apply {
                moveTo(12f, 17.27f)
                lineTo(18.18f, 21f)
                lineTo(16.54f, 13.97f)
                lineTo(22f, 9.24f)
                lineTo(14.81f, 8.63f)
                lineTo(12f, 2f)
                lineTo(9.19f, 8.63f)
                lineTo(2f, 9.24f)
                lineTo(7.45f, 13.97f)
                lineTo(5.82f, 21f)
                close()
            }.nodes,
        )
    }.build()

val ArrowBackIcon: ImageVector
    get() = ImageVector.Builder(
        name = "ArrowBack", defaultWidth = 24.dp, defaultHeight = 24.dp, viewportWidth = 24f, viewportHeight = 24f
    ).apply {
        addPath(
            fill = Brush.linearGradient(
                colors = listOf(Color.Cyan, Color.Blue)
            ),
            pathData = PathBuilder().apply {
                moveTo(20f, 11f)
                horizontalLineTo(7.83f)
                lineTo(13.42f, 5.41f)
                lineTo(12f, 4f)
                lineTo(4f, 12f)
                lineTo(12f, 20f)
                lineTo(13.41f, 18.59f)
                lineTo(7.83f, 13f)
                horizontalLineTo(20f)
                verticalLineTo(11f)
                close()
            }.nodes,
        )
    }.build()

val FavoriteIcon: ImageVector
    get() = ImageVector.Builder(
        name = "Favorite", defaultWidth = 24.dp, defaultHeight = 24.dp, viewportWidth = 24f, viewportHeight = 24f
    ).apply {
        addPath(
            fill = Brush.linearGradient(
                colors = listOf(Color.Black, Color.Red)
            ),
            pathData = PathBuilder().apply {
                moveTo(12f, 21.35f)
                lineTo(10.55f, 20.03f)
                curveTo(5.4f, 15.36f, 2f, 12.28f, 2f, 8.5f)
                curveTo(2f, 5.42f, 4.42f, 3f, 7.5f, 3f)
                curveTo(9.24f, 3f, 10.91f, 3.81f, 12f, 5.08f)
                curveTo(13.09f, 3.81f, 14.76f, 3f, 16.5f, 3f)
                curveTo(19.58f, 3f, 22f, 5.42f, 22f, 8.5f)
                curveTo(22f, 12.28f, 18.6f, 15.36f, 13.45f, 20.04f)
                lineTo(12f, 21.35f)
                close()
            }.nodes
        )
    }.build()

val FavoriteBorderIcon: ImageVector
    get() = ImageVector.Builder(
        name = "FavoriteBorder", defaultWidth = 24.dp, defaultHeight = 24.dp, viewportWidth = 24f, viewportHeight = 24f
    ).apply {
        addPath(
            stroke = Brush.linearGradient(
                colors = listOf(Color.Black, Color.Gray)
            ),
            pathData = PathBuilder().apply {
                moveTo(16.5f, 3f)
                curveTo(14.76f, 3f, 13.09f, 3.81f, 12f, 5.08f)
                curveTo(10.91f, 3.81f, 9.24f, 3f, 7.5f, 3f)
                curveTo(4.42f, 3f, 2f, 5.42f, 2f, 8.5f)
                curveTo(2f, 12.28f, 5.4f, 15.36f, 10.55f, 20.03f)
                lineTo(12f, 21.35f)
                lineTo(13.45f, 20.04f)
                curveTo(18.6f, 15.36f, 22f, 12.28f, 22f, 8.5f)
                curveTo(22f, 5.42f, 19.58f, 3f, 16.5f, 3f)
                close()
            }.nodes
        )
    }.build()

val KeyboardArrowRightIcon: ImageVector
    get() = ImageVector.Builder(
        name = "KeyboardArrowRight", defaultWidth = 24.dp, defaultHeight = 24.dp, viewportWidth = 24f, viewportHeight = 24f
    ).apply {
        addPath(
            pathData = PathBuilder().apply {
                moveTo(8.59f, 16.59f)
                lineTo(13.17f, 12f)
                lineTo(8.59f, 7.41f)
                lineTo(10f, 6f)
                lineTo(16f, 12f)
                lineTo(10f, 18f)
                lineTo(8.59f, 16.59f)
                close()
            }.nodes
        )
    }.build()

val CloseIcon: ImageVector
    get() = ImageVector.Builder(
        name = "Close", defaultWidth = 24.dp, defaultHeight = 24.dp, viewportWidth = 24f, viewportHeight = 24f
    ).apply {
        addPath(
            stroke = Brush.linearGradient(
                colors = listOf(Color.Red, Color.Magenta),
            ),
            pathData = PathBuilder().apply {
                moveTo(19f, 6.41f)
                lineTo(17.59f, 5f)
                lineTo(12f, 10.59f)
                lineTo(6.41f, 5f)
                lineTo(5f, 6.41f)
                lineTo(10.59f, 12f)
                lineTo(5f, 17.59f)
                lineTo(6.41f, 19f)
                lineTo(12f, 13.41f)
                lineTo(17.59f, 19f)
                lineTo(19f, 17.59f)
                lineTo(13.41f, 12f)
                close()
            }.nodes
        )
    }.build()

val SearchIcon: ImageVector
    get() = ImageVector.Builder(
        name = "Search", defaultWidth = 24.dp, defaultHeight = 24.dp, viewportWidth = 24f, viewportHeight = 24f
    ).apply {
        addPath(
            stroke = Brush.linearGradient(
                colors = listOf(Color.White, Color.LightGray),
            ),
            pathData = PathBuilder().apply {
                moveTo(15.5f, 14f)
                horizontalLineTo(14.71f)
                lineTo(14.43f, 13.73f)
                curveTo(15.41f, 12.59f, 16f, 11.11f, 16f, 9.5f)
                curveTo(16f, 5.91f, 13.09f, 3f, 9.5f, 3f)
                curveTo(5.91f, 3f, 3f, 5.91f, 3f, 9.5f)
                curveTo(3f, 13.09f, 5.91f, 16f, 9.5f, 16f)
                curveTo(11.11f, 16f, 12.59f, 15.41f, 13.73f, 14.43f)
                lineTo(14f, 14.71f)
                verticalLineTo(15.5f)
                lineTo(19f, 20.49f)
                lineTo(20.49f, 19f)
                lineTo(15.5f, 14f)
                close()
                moveTo(9.5f, 14f)
                curveTo(7.01f, 14f, 5f, 11.99f, 5f, 9.5f)
                curveTo(5f, 7.01f, 7.01f, 5f, 9.5f, 5f)
                curveTo(11.99f, 5f, 14f, 7.01f, 14f, 9.5f)
                curveTo(14f, 11.99f, 11.99f, 14f, 9.5f, 14f)
                close()
            }.nodes
        )
    }.build()