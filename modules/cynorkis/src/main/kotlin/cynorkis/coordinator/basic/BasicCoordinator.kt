package cynorkis.coordinator.basic

import cynorkis.cache.CacheService
import cynorkis.connection.ConnectionClient
import cynorkis.coordinator.Coordinator
import cynorkis.core.ConnectionRequest
import cynorkis.core.ConnectionResponse
import java.util.concurrent.CompletableFuture

internal class BasicCoordinator(
    private val connectionClient: ConnectionClient,
    private val cacheService: CacheService
) : Coordinator {
    override fun send(request: ConnectionRequest): CompletableFuture<ConnectionResponse> {
        val cachedResponse = cacheService.fetchFromCache(request)
        return cachedResponse?.let { CompletableFuture.completedFuture(it) }
            ?: connectionClient.send(request).whenComplete { response, _ ->
                response?.let { cacheService.cache(request, it) }
            }
    }
}
