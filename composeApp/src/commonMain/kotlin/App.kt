import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import dev.gitlive.firebase.Firebase
import dev.gitlive.firebase.firestore.firestore
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.painterResource

@OptIn(ExperimentalResourceApi::class)
@Composable
fun App() {
    MaterialTheme {
        var greetingText by remember { mutableStateOf("Firebase Compose Multiplatform!") }
        var showImage by remember { mutableStateOf(false) }
        Column(Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
            var list by remember { mutableStateOf(listOf<User>()) }
            LaunchedEffect(Unit){
                list = getUsers()
            }
            LazyColumn {
                items(list) {
                    UserItem(it)
                }
            }
            Button(onClick = {
                greetingText = "Compose: ${Greeting().greet()}"
                showImage = !showImage
            }) {
                Text(greetingText)
            }
            AnimatedVisibility(showImage) {
                Image(
                    painterResource("compose-multiplatform.xml"),
                    null
                )
            }
        }
    }
}

@Composable
fun UserItem(user: User){
    Column {
        Text(text = user.location)
        Text(text = user.day)
    }
}
suspend fun getUsers(): List<User> {
    val firebaseFirestore = Firebase.firestore
    try{
        val userResponse = firebaseFirestore.collection("Users").get()
        return userResponse.documents.map {
            it.data()
        }
    } catch (e: Exception){
        throw e
    }
}