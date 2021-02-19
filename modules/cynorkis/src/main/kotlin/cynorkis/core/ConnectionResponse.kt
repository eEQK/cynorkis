package cynorkis.core

class ConnectionResponse(
    val statusCode: Int,
    val statusCodePhrase: String,
    val headers: List<String>,
    val body: ByteArray,
)
