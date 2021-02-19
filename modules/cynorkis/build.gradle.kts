plugins {
    `kotlin-jvm`
    objectbox
    kodein
    akka
    junit

    `maven-publish`
}

dependencies {
    implementation("com.fasterxml.jackson.core:jackson-core:2.12.1")
    implementation("org.asynchttpclient:async-http-client:2.12.2")

    testImplementation("com.sparkjava:spark-core:2.9.3")
}


publishing {
    publications {
        create<MavenPublication>(rootProject.name) {
            from(components["kotlin"])
        }
    }

    repositories {
        maven {
            name = "GitHubPackages"
            setUrl("https://maven.pkg.github.com/eEQK/cynorkis")
            credentials {
                username = System.getenv("GITHUB_ACTOR")
                password = System.getenv("GITHUB_TOKEN")
            }
        }
    }
}