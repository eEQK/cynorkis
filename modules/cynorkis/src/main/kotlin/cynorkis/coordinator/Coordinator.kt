package cynorkis.coordinator

import cynorkis.cache.CacheService
import cynorkis.cache.CacheServiceFactory
import cynorkis.connection.ConnectionClient
import cynorkis.connection.ConnectionClientFactory
import cynorkis.coordinator.basic.BasicCoordinator
import cynorkis.core.ConnectionRequest
import cynorkis.core.ConnectionResponse
import java.util.concurrent.CompletableFuture

interface Coordinator {
    fun send(request: ConnectionRequest): CompletableFuture<ConnectionResponse>
}

object CoordinatorFactory {
    fun basic(
        connectionClient: ConnectionClient = ConnectionClientFactory.asyncHttp(),
        cacheService: CacheService = CacheServiceFactory.objectbox(),
    ): Coordinator = BasicCoordinator(connectionClient, cacheService)
}