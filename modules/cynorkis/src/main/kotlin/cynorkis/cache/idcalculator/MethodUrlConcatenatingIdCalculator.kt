package cynorkis.cache.idcalculator

import cynorkis.cache.CacheIdCalculator
import cynorkis.core.ConnectionRequest

class MethodUrlConcatenatingIdCalculator : CacheIdCalculator {
    override fun calculateId(request: ConnectionRequest): String = "${request.method} ${request.url}"
}