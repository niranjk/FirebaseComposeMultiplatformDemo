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
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Settings
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun SettingsRoute(
    modifier: Modifier = Modifier,
    onBackClick: (String) -> Unit = {}
) {

    Surface(modifier = modifier.fillMaxSize(),
        color = MaterialTheme.colors.background){
        Scaffold(
            topBar = {
                TopAppBar(
                    modifier = Modifier.fillMaxSize(),
                    title = { Text(text = "Settings") },
                    navigationIcon = {
                        IconButton(onClick = { onBackClick("") }){
                            Icon(
                                imageVector = Icons.Default.ArrowBack,
                                contentDescription = "Settings"
                            )
                        }
                    },
                    backgroundColor = MaterialTheme.colors.primary
                )
            }
        ){
            Row(modifier = Modifier.fillMaxSize(), horizontalArrangement = Arrangement.Center) {
                Text("Settings Screen")
            }
        }
    }
}