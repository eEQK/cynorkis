# Cynorkis
### _Minimal, caching HTTP client_

Cynorkis is a bare-bones HTTP client designed for fast prototyping.

```kotlin
import cynorkis.coordinator.CoordinatorFactory
import cynorkis.core.ConnectionRequest

fun main() {
    CoordinatorFactory.basic()
        .send(
            ConnectionRequest("GET", "https://www.reddit.com/r/kotlin.json")
        )
}
```

Caching makes it easier to work with rate limiting APIs or ones that are slow assuming that responses are reasonably static. Cynorkis lets you focus on implementing business logic without having to worry about an intermediate layer you'd have to introduce otherwise.