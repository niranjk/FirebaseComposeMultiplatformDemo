package ui

import androidx.compose.runtime.Composable
import com.arkivanov.decompose.extensions.compose.jetbrains.stack.animation.slide
import com.arkivanov.decompose.extensions.compose.jetbrains.stack.animation.stackAnimation
import com.arkivanov.decompose.router.stack.pop
import com.arkivanov.decompose.router.stack.push
import com.arkivanov.essenty.parcelable.Parcelable
import com.arkivanov.essenty.parcelable.Parcelize
import ui.screens.HomeRoute
import ui.screens.SettingsRoute
import ui.theme.MyTheme
import utils.krouter.RoutedContent
import utils.krouter.rememberRouter

@Composable
fun RootComponent(
    isExpandedScreen: Boolean = false
) {

    val router = rememberRouter(RootStateModel::class, listOf(RootStateModel.Home))

    MyTheme(darkTheme = true) {
        RoutedContent(
            router = router,
            animation = stackAnimation(animator = slide()),
            content = { screen ->
                when (screen) {
                    is RootStateModel.Home -> HomeRoute(
                        navigateToSettings = {
                            router.push(RootStateModel.Settings)
                        }
                    )
                    is RootStateModel.Settings -> SettingsRoute(
                        onBackClick = {
                            router.pop()
                        }
                    )
                }
            }
        )
    }
}

@Parcelize
sealed class RootStateModel : Parcelable {
    data object Home : RootStateModel()
    data object Settings : RootStateModel()
}
