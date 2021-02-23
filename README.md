# Cynorkis

### _Minimal, caching HTTP client_

[![License: Unlicense](https://img.shields.io/badge/license-Unlicense-pink.svg)](http://unlicense.org/)
[![Email](https://img.shields.io/badge/Contact--me-Email-orange.svg)](mailto:karol.czeryna@gmail.com)
[![Telegram](https://img.shields.io/badge/Contact--me-Telegram-blue.svg)](https://t.me/karol.nn)

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

Caching makes it easier to work with rate limiting APIs or ones that are slow assuming that responses are reasonably
static. Cynorkis lets you focus on implementing business logic without having to worry about an intermediate layer you'd have to introduce otherwise.

The way it works is really simple. Coordinator asks cache service for cached response. If none is found, the coordinator tells connection service to send a request that is then saved into cache for later usage.

### Gradle setup

```kotlin
repositories {
    maven {
        url = uri("https://maven.pkg.github.com/eEQK/cynorkis")
        credentials {
            // unfortunately, there's no way to download a github package anonymously
            // also, jCenter will shut down soon, I'm yet to discover an alternative
            // I'd rather avoid using maven central during early development stages
            username = System.getenv("GITHUB_USER")
            password = System.getenv("GITHUB_TOKEN") // with read:packages permission
        }
    }

}

dependencies {
    implementation("cynorkis:cynorkis:1.0.0")
}
```