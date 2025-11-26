package com.kkapps.common.ui.components.icons

import androidx.compose.foundation.Image
import androidx.compose.material.icons.materialIcon
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathFillType
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.PathBuilder
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp
import org.jetbrains.compose.ui.tooling.preview.Preview

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

val AddIcon: ImageVector
    get() = ImageVector.Builder(
        name = "Add", defaultWidth = 24.dp, defaultHeight = 24.dp, viewportWidth = 24f, viewportHeight = 24f
    ).apply {
        addPath(
            fill = Brush.linearGradient(
                colors = listOf(Color.Green, Color.DarkGray),
            ),
            pathData = PathBuilder().apply {
                moveTo(19f, 13f)
                horizontalLineTo(13f)
                verticalLineTo(19f)
                horizontalLineTo(11f)
                verticalLineTo(13f)
                horizontalLineTo(5f)
                verticalLineTo(11f)
                horizontalLineTo(11f)
                verticalLineTo(5f)
                horizontalLineTo(13f)
                verticalLineTo(11f)
                horizontalLineTo(19f)
                verticalLineTo(13f)
                close()
            }.nodes
        )
    }.build()

val EditIcon: ImageVector
    get() = ImageVector.Builder(
        name = "Edit", defaultWidth = 24.dp, defaultHeight = 24.dp, viewportWidth = 24f, viewportHeight = 24f
    ).apply {
        addPath(
            fill = Brush.linearGradient(
                colors = listOf(Color.Green, Color.DarkGray),
            ),
            pathData = PathBuilder().apply {
                moveTo(3f, 17.25f)
                verticalLineTo(21f)
                horizontalLineTo(6.75f)
                lineTo(17.81f, 9.94f)
                lineTo(14.06f, 6.19f)
                lineTo(3f, 17.25f)
                close()
                moveTo(20.71f, 7.04f)
                curveTo(21.1f, 6.65f, 21.1f, 6.02f, 20.71f, 5.63f)
                lineTo(18.37f, 3.29f)
                curveTo(17.98f, 2.9f, 17.35f, 2.9f, 16.96f, 3.29f)
                lineTo(15.13f, 5.12f)
                lineTo(18.88f, 8.87f)
                lineTo(20.71f, 7.04f)
                close()
            }.nodes
        )
    }.build()


val AirplaneTakeoff: ImageVector
    get() {
        if (_airplaneTakeoff != null) {
            return _airplaneTakeoff!!
        }
        _airplaneTakeoff = materialIcon(name = "Plane") {
            path(
                fill = SolidColor(Color(0xFFDCDCDC)),
                fillAlpha = 1.0F,
                strokeAlpha = 1.0F,
                strokeLineWidth = 0.0F,
                strokeLineCap = StrokeCap.Butt,
                strokeLineJoin = StrokeJoin.Miter,
                strokeLineMiter = 4.0F,
                pathFillType = PathFillType.NonZero,
            ) {
                moveTo(2.5F, 19.0F)
                horizontalLineTo(21.5F)
                verticalLineTo(21.0F)
                horizontalLineTo(2.5F)
                verticalLineTo(19.0F)
                moveTo(22.07F, 9.64F)
                curveTo(21.86F, 8.84F, 21.03F, 8.36F, 20.23F, 8.58F)
                lineTo(14.92F, 10.0F)
                lineTo(8.0F, 3.57F)
                lineTo(6.09F, 4.08F)
                lineTo(10.23F, 11.25F)
                lineTo(5.26F, 12.58F)
                lineTo(3.29F, 11.04F)
                lineTo(1.84F, 11.43F)
                lineTo(3.66F, 14.59F)
                lineTo(4.43F, 15.92F)
                lineTo(6.03F, 15.5F)
                lineTo(11.34F, 14.07F)
                lineTo(15.69F, 12.91F)
                lineTo(21.0F, 11.5F)
                curveTo(21.81F, 11.26F, 22.28F, 10.44F, 22.07F, 9.64F)
                close()
            }
        }
        return _airplaneTakeoff!!
    }

private var _airplaneTakeoff: ImageVector? = null

@Preview
@Composable
@Suppress("UnusedPrivateMember")
private fun IconAirplaneTakeoffPreview() {
    Image(imageVector = AirplaneTakeoff, contentDescription = null)
}

val AirplaneLanding: ImageVector
    get() {
        if (_airplaneLanding != null) {
            return _airplaneLanding!!
        }
        _airplaneLanding = materialIcon(name = "AirplaneLanding") {
            path(
                fill = SolidColor(Color(0xFFDCDCDC)),
                fillAlpha = 1.0F,
                strokeAlpha = 1.0F,
                strokeLineWidth = 0.0F,
                strokeLineCap = StrokeCap.Butt,
                strokeLineJoin = StrokeJoin.Miter,
                strokeLineMiter = 4.0F,
                pathFillType = PathFillType.NonZero,
            ) {
                moveTo(2.5F, 19.0F)
                horizontalLineTo(21.5F)
                verticalLineTo(21.0F)
                horizontalLineTo(2.5F)
                verticalLineTo(19.0F)
                moveTo(9.68F, 13.27F)
                lineTo(14.03F, 14.43F)
                lineTo(19.34F, 15.85F)
                curveTo(20.14F, 16.06F, 20.96F, 15.59F, 21.18F, 14.79F)
                curveTo(21.39F, 14.0F, 20.92F, 13.17F, 20.12F, 12.95F)
                lineTo(14.81F, 11.53F)
                lineTo(12.05F, 2.5F)
                lineTo(10.12F, 2.0F)
                verticalLineTo(10.28F)
                lineTo(5.15F, 8.95F)
                lineTo(4.22F, 6.63F)
                lineTo(2.77F, 6.24F)
                verticalLineTo(11.41F)
                lineTo(4.37F, 11.84F)
                lineTo(9.68F, 13.27F)
                close()
            }
        }
        return _airplaneLanding!!
    }

private var _airplaneLanding: ImageVector? = null

@Preview
@Composable
@Suppress("UnusedPrivateMember")
private fun IconAirplaneLandingPreview() {
    Image(imageVector = AirplaneLanding, contentDescription = null)
}