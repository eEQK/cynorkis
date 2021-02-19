plugins {
    `kotlin-dsl`
}

repositories {
    mavenCentral()
    jcenter()
}

dependencies {
    implementation("org.jetbrains.kotlin:kotlin-gradle-plugin:1.4.30")
    implementation("com.google.code.gson:gson:2.8.6")
    implementation("io.objectbox:objectbox-gradle-plugin:2.9.0")
}