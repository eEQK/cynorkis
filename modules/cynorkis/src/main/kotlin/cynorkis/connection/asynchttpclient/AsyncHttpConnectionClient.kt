package cynorkis.connection.asynchttpclient

import cynorkis.connection.ConnectionClient
import cynorkis.core.ConnectionRequest
import cynorkis.core.ConnectionResponse
import java.util.concurrent.CompletableFuture
import org.asynchttpclient.AsyncHttpClient
import org.asynchttpclient.RequestBuilder
import org.asynchttpclient.Response

internal class AsyncHttpConnectionClient(
    private val httpClient: AsyncHttpClient
) : ConnectionClient {
    override fun send(request: ConnectionRequest): CompletableFuture<ConnectionResponse> {
        val asyncHttpRequest = asyncHttpRequestFrom(request)

        return httpClient
            .executeRequest(asyncHttpRequest)
            .toCompletableFuture()
            .thenApply(this::connectionResponseFrom)
    }

    private fun asyncHttpRequestFrom(request: ConnectionRequest) = RequestBuilder()
        .apply {
            setUrl(request.url)
            setMethod(request.method)
            request.headers.forEach {
                val (key, value) = it.split(":")
                setHeader(key, value)
            }
            setBody(request.body)
        }.build()

    private fun connectionResponseFrom(response: Response) = ConnectionResponse(
        statusCode = response.statusCode,
        statusCodePhrase = response.statusText,
        headers = response.headers.map { header -> "${header.key}:${header.value}" },
        body = response.responseBodyAsBytes
    )
}