package cynorkis.cache

import cynorkis.core.ConnectionRequest

interface CacheIdCalculator {
    fun calculateId(request: ConnectionRequest): String
}
