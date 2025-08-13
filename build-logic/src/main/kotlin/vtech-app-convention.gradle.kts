import org.gradle.kotlin.dsl.application
import org.springframework.boot.gradle.tasks.bundling.BootJar
plugins {
    application
    id("vtech-shared-convention")
}
tasks.withType<BootJar> {
    enabled = true
}