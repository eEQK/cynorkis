import com.google.gson.Gson
import org.jetbrains.kotlin.gradle.plugin.getKotlinPluginVersion
import java.net.URL

val TAG_NAME_VERSION_PREFIX = "v"

plugins {
    kotlin("jvm")
}

repositories {
    mavenCentral()
}

val updateRuntime by tasks.registering {
    updateGradle()
    updateKotlin()
}

fun updateKotlin() {
    val buildSrcPath = "${projectDir.path}/buildSrc"
    val buildSrcConfigFile = File("$buildSrcPath/build.gradle.kts")

    val currentKotlinVersion = project.getKotlinPluginVersion() ?: error("Couldn't determine Kotlin version")
    val latestKotlinVersion = fetchLatestGithubRelease("JetBrains/kotlin")
    val updatedConfigFile = buildSrcConfigFile.readText().replace(oldValue = currentKotlinVersion, newValue = latestKotlinVersion)

    buildSrcConfigFile.writeText(updatedConfigFile)
}

fun updateGradle() {
    val releaseName = fetchLatestGithubRelease("gradle/gradle")
    "./gradlew wrapper --gradle-version=$releaseName".runCommand(workingDir = projectDir.path)
}

fun fetchLatestGithubRelease(projectIdentifier: String): String {
    val latestReleaseJson = githubProjectLatestReleaseUrl(projectIdentifier).readText()
    val tagName = Gson().fromJson<Map<String, String>>(latestReleaseJson, Map::class.java).getValue("tag_name")

    return tagName.drop(TAG_NAME_VERSION_PREFIX.length)
}

fun githubProjectLatestReleaseUrl(projectIdentifier: String): URL = URL("https://api.github.com/repos/$projectIdentifier/releases/latest")

fun String.runCommand(workingDir: String) {
    ProcessBuilder(*split(" ").toTypedArray())
        .directory(File(workingDir))
        .inheritIO()
        .start()
        .waitFor(15, TimeUnit.SECONDS)
}