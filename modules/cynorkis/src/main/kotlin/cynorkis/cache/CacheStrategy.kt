package cynorkis.cache

import cynorkis.core.ConnectionRequest
import cynorkis.core.ConnectionResponse

interface CacheStrategy {
    fun isEligibleForCaching(request: ConnectionRequest, response: ConnectionResponse): Boolean
}
