plugins {
    id(vtechLibs.versions.vtechApp.get())
    alias(libs.plugins.jFrogPlugin)
    alias(libs.plugins.kotlinxBenchmarkPlugin)
}

group = "com.veda.tech.gradle.app"
version = "1.0.0-SNAPSHOT"

dependencies {
    implementation(project(":vtech-shared"))
}

application {
    mainClass.set("com.veda.tech.gradle.app.MainKt")
}
