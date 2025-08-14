//import org.springframework.boot.gradle.tasks.bundling.BootJar
plugins {
    id(vtechLibs.versions.vtechLibraryName.get())
    alias(libs.plugins.jFrogPlugin)
    alias(libs.plugins.kotlinxBenchmarkPlugin)
}

group = "com.veda.tech.gradle.shared"
version = "1.0.0-SNAPSHOT"

dependencies {
    kover(project(":vtech-shared"))
}