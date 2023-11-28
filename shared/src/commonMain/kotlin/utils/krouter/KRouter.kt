package utils.krouter

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.ProvidableCompositionLocal
import androidx.compose.runtime.State
import androidx.compose.runtime.remember
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.Modifier
import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.extensions.compose.jetbrains.stack.Children
import com.arkivanov.decompose.extensions.compose.jetbrains.stack.animation.StackAnimation
import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.router.stack.StackNavigation
import com.arkivanov.essenty.parcelable.Parcelable
import kotlin.reflect.KClass


class Router<C : Parcelable>(
    private val navigator: StackNavigation<C>,
    val stack: State<ChildStack<C, ComponentContext>>,
) : StackNavigation<C> by navigator

val LocalRouter: ProvidableCompositionLocal<Router<*>?> =
    staticCompositionLocalOf { null }

@Composable
fun <C : Parcelable> rememberRouter(
    type: KClass<C>,
    stack: List<C>,
    handleBackButton: Boolean = true,
): Router<C> {
    val navigator: StackNavigation<C> = remember { StackNavigation() }

    val packageName: String =
        requireNotNull(type.simpleName) { "Unable to retain anonymous instance of $type" }

    val childStackState: State<ChildStack<C, ComponentContext>> = rememberChildStack(
        source = navigator,
        initialStack = { stack },
        key = packageName,
        handleBackButton = handleBackButton,
        type = type,
    )

    return remember { Router(navigator = navigator, stack = childStackState) }
}

@Composable
fun <C : Parcelable> RoutedContent(
    router: Router<C>,
    modifier: Modifier = Modifier,
    animation: StackAnimation<C, ComponentContext>? = null,
    content: @Composable (C) -> Unit,
) {
    CompositionLocalProvider(LocalRouter provides router) {
        Children(
            stack = router.stack.value,
            modifier = modifier,
            animation = animation,
        ) { child ->
            CompositionLocalProvider(LocalComponentContext provides child.instance) {
                content(child.configuration)
            }
        }
    }
}