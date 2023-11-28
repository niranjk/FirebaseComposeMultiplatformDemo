import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.window.ComposeUIViewController
import com.arkivanov.decompose.DefaultComponentContext
import com.arkivanov.essenty.lifecycle.LifecycleRegistry
import platform.UIKit.UIViewController
import utils.krouter.LocalComponentContext

actual fun getPlatformName(): String = "iOS"

fun MainViewController(): UIViewController {

    val lifecycle = LifecycleRegistry()

    val rootComponentContext = DefaultComponentContext(lifecycle = lifecycle)

    return ComposeUIViewController {
        CompositionLocalProvider(LocalComponentContext provides rootComponentContext) {
            DecomposeApp()
        }
    }
}