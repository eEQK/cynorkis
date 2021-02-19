package cynorkis.coordinator

import cynorkis.cache.CacheServiceFactory
import cynorkis.core.ConnectionRequest
import java.util.concurrent.TimeUnit
import kotlin.system.measureTimeMillis
import kotlin.test.assertTrue
import org.junit.jupiter.api.Test
import spark.Spark

internal class BasicCoordinatorTest {
    init {
        Spark.get("/delayed") { request, response ->
            TimeUnit.MILLISECONDS.sleep(2000)
            "ok"
        }
    }

    @Test
    fun `response is being cached`() {
        val cacheService = CacheServiceFactory.objectbox()
        cacheService.clearCache()

        val coordinator = CoordinatorFactory.basic()
        val request = ConnectionRequest("GET", "http://localhost:${Spark.port()}/delayed")

        val firstSend = measureTimeMillis { coordinator.send(request).get() }
        val secondSend = measureTimeMillis { coordinator.send(request).get() }
        assertTrue(firstSend > secondSend * 100.0)
    }
}


