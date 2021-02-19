package cynorkis.cache

import cynorkis.cache.objectbox.ObjectboxCacheService
import cynorkis.core.ConnectionRequest
import cynorkis.core.ConnectionResponse

interface CacheService {
    fun fetchFromCache(request: ConnectionRequest): ConnectionResponse?
    fun cache(request: ConnectionRequest, response: ConnectionResponse)
    fun clearCache()
}

object CacheServiceFactory {
    fun objectbox(): CacheService = ObjectboxCacheService()
}