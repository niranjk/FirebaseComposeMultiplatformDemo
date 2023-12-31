package utils.krouter

import androidx.compose.runtime.Composable
import androidx.compose.runtime.ProvidableCompositionLocal
import androidx.compose.runtime.State
import androidx.compose.runtime.remember
import androidx.compose.runtime.staticCompositionLocalOf
import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.extensions.compose.jetbrains.subscribeAsState
import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.router.stack.StackNavigationSource
import com.arkivanov.decompose.router.stack.childStack
import com.arkivanov.essenty.parcelable.Parcelable
import kotlin.reflect.KClass


val LocalComponentContext: ProvidableCompositionLocal<ComponentContext> =
    staticCompositionLocalOf { error("Root component context was not provided") }
@Composable
internal fun <C : Parcelable> rememberChildStack(
    type: KClass<C>,
    source: StackNavigationSource<C>,
    initialStack: () -> List<C>,
    key: String = "DefaultChildStack",
    handleBackButton: Boolean = false,
): State<ChildStack<C, ComponentContext>> {
    val componentContext = LocalComponentContext.current

    return remember {
        componentContext.childStack(
            source = source,
            initialStack = initialStack,
            configurationClass = type,
            key = key,
            handleBackButton = handleBackButton,
            childFactory = { _, childComponentContext -> childComponentContext },
        )
    }.subscribeAsState()
}