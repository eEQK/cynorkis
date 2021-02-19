package cynorkis.core

class ConnectionRequest(
    val method: String,
    val url: String,
    val headers: List<String> = listOf(),
    val body: ByteArray = byteArrayOf(),
)
