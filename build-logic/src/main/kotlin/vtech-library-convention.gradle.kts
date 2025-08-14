import org.springframework.boot.gradle.tasks.bundling.BootJar

plugins {
    `java-library`
    id("vtech-shared-convention")
}

tasks.withType<BootJar> {
    enabled = false
}