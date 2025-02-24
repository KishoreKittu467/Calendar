import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import com.kkapps.bubbles.app.App
import com.kkapps.bubbles.di.initKoin

fun main() = application {
    initKoin()
    Window(
        onCloseRequest = ::exitApplication,
        title = "Bubbles",
    ) {
        App()
    }
}