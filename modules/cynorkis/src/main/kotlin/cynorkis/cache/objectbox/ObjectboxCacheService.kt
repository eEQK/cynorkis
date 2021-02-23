package cynorkis.cache.objectbox

import cynorkis.cache.CacheService
import cynorkis.cache.CacheStrategy
import cynorkis.core.ConnectionRequest
import cynorkis.core.ConnectionResponse
import io.objectbox.BoxStore
import io.objectbox.kotlin.boxFor
import java.io.File


private const val HEADER_DELIMETER = "=9*aA7V18NIe="

private val home = System.getProperty("user.home")
private const val cacheFolder = ".cynorkis-cache"
private val store: BoxStore = MyObjectBox.builder()
    .baseDirectory(File(home))
    .name(cacheFolder).build()

internal class ObjectboxCacheService(
    private val cacheStrategy: CacheStrategy
) : CacheService {
    private val box = store.boxFor<ObjectboxCacheModel>()

    override fun fetchFromCache(request: ConnectionRequest): ConnectionResponse? {
        return box.query()
            .equal(ObjectboxCacheModel_.responseId, computeResponseId(request))
            .build()
            .findFirst()
            .let(::connectionResponseFrom)
    }

    override fun cache(request: ConnectionRequest, response: ConnectionResponse) {
        if (cacheStrategy.isEligibleForCaching(request, response)) {
            box.put(cacheModelFrom(request, response))
        }
    }

    override fun clearCache() {
        box.removeAll()
    }

    private fun cacheModelFrom(request: ConnectionRequest, response: ConnectionResponse): ObjectboxCacheModel {
        return ObjectboxCacheModel(
            responseId = computeResponseId(request),
            statusCode = response.statusCode,
            statusCodePhrase = response.statusCodePhrase,
            headers = response.headers.joinToString(HEADER_DELIMETER),
            body = response.body
        )
    }

    private fun connectionResponseFrom(cache: ObjectboxCacheModel?): ConnectionResponse? {
        cache ?: return null
        return ConnectionResponse(
            cache.statusCode,
            cache.statusCodePhrase,
            cache.headers.split(HEADER_DELIMETER),
            cache.body,
        )
    }

    private fun computeResponseId(request: ConnectionRequest): String = "${request.method} ${request.url}"
}