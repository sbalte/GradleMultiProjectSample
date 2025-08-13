import org.gradle.kotlin.dsl.`java-library`
import org.gradle.kotlin.dsl.withType
import org.springframework.boot.gradle.tasks.bundling.BootJar
plugins {
    `java-library`
    id("vtech-shared-convention")
}

tasks.withType<BootJar> {
    enabled = false
}