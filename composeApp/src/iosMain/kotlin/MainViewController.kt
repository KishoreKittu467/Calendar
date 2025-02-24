import androidx.compose.ui.window.ComposeUIViewController
import com.kkapps.bubbles.app.App
import com.kkapps.bubbles.di.initKoin

fun MainViewController() = ComposeUIViewController(
    configure = {
        initKoin()
    }
) {
    App()
}