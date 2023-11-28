package ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Create
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier


@Composable
fun HomeRoute(
    modifier: Modifier = Modifier,
    navigateToSettings: (String) -> Unit = {}
) {

    Surface(modifier = modifier.fillMaxSize(),
        color = MaterialTheme.colors.background){
        Scaffold(
            topBar = {
                TopAppBar(
                    modifier = Modifier.fillMaxSize(),
                    title = { Text(text = "Compose Multiplatform Navigation Tutorial") },
                    navigationIcon = {
                        IconButton(onClick = {  }){
                            Icon(
                                imageVector = Icons.Default.Menu,
                                contentDescription = "Menu"
                            )
                        }
                    },
                    backgroundColor = MaterialTheme.colors.primary
                )
            },
            floatingActionButton = {
                FloatingActionButton(
                    onClick = {navigateToSettings("")},
                    backgroundColor = MaterialTheme.colors.primary
                ){
                    Icon(imageVector = Icons.Default.Create, contentDescription = "Add")
                }
            }
        ){
            Row(modifier = Modifier.fillMaxSize(), horizontalArrangement = Arrangement.Center) {
                Text("Home Screen")
            }
        }
    }
}