import kotlinx.serialization.Serializable

@Serializable
data class User(
    val location: String,
    val day: String
)
