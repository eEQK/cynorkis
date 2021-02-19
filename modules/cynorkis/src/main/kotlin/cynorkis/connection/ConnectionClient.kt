package cynorkis.connection

import cynorkis.connection.asynchttpclient.AsyncHttpConnectionClient
import cynorkis.core.ConnectionRequest
import cynorkis.core.ConnectionResponse
import java.util.concurrent.CompletableFuture
import org.asynchttpclient.Dsl.asyncHttpClient

interface ConnectionClient {
    fun send(request: ConnectionRequest): CompletableFuture<ConnectionResponse>
}

object ConnectionClientFactory {
    fun asyncHttp(): ConnectionClient = AsyncHttpConnectionClient(asyncHttpClient())
}