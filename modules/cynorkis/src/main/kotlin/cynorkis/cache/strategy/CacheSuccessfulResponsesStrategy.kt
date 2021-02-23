package cynorkis.cache.strategy

import cynorkis.cache.CacheStrategy
import cynorkis.core.ConnectionRequest
import cynorkis.core.ConnectionResponse

class CacheSuccessfulResponsesStrategy : CacheStrategy {
    override fun isEligibleForCaching(request: ConnectionRequest, response: ConnectionResponse) =
        response.statusCode in 200..299
}