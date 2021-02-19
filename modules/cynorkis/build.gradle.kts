plugins {
    `kotlin-jvm`
    objectbox
    kodein
    akka
    junit
}

dependencies {
    implementation("com.fasterxml.jackson.core:jackson-core:2.12.1")
    implementation("org.asynchttpclient:async-http-client:2.12.2")

    testImplementation("com.sparkjava:spark-core:2.9.3")
}
