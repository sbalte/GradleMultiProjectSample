//import org.springframework.boot.gradle.tasks.bundling.BootJar
plugins {
    id(vtechLibs.versions.vtechLibrary.get())
    alias(libs.plugins.kotlinSerializationPlugin)
}

group = "com.veda.tech.gradle.shared"
version = "1.0.0-SNAPSHOT"

dependencies {}