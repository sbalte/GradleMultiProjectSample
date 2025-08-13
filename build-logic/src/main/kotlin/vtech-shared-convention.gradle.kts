@file:Suppress("unused")

import conventions.VTechShared

plugins {
    kotlin("jvm")
    kotlin("plugin.spring")
    id("org.springframework.boot")
    id("io.spring.dependency-management")
    `java-test-fixtures`
    `maven-publish`
}
java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(VTechShared.JavaVersion)
    }
}
kotlin {
    compilerOptions {
        freeCompilerArgs = VTechShared.kotlinLangArgs
    }
}
tasks.test {
    useJUnitPlatform()
}
repositories {
    mavenCentral()
}
tasks.withType<AbstractTestTask>().configureEach {
    failOnNoDiscoveredTests = false
}
val vtechLibs = the<VersionCatalogsExtension>().named("vtechLibs")
val libs = the<VersionCatalogsExtension>().named("libs")
val testLibs = extensions.getByType(VersionCatalogsExtension::class.java).named("testLibs")

dependencies {
    //bundles
    implementation(libs.findLibrary("exposedJdbc").get())
    implementation(libs.findLibrary("exposedCcore").get())
    implementation(libs.findBundle("apacheBundle").get())
    implementation(libs.findBundle("arrowBundle").get())
    implementation(libs.findBundle("googleBundle").get())
//    implementation(libs.findBundle("http4kBundle").get())
    implementation(libs.findBundle("kotlinCoreBundle").get())
    implementation(libs.findBundle("mutinyBundle").get())
    implementation(libs.findBundle("solaceBundle").get())
    implementation(libs.findBundle("jacksonBundle").get())
    implementation(libs.findBundle("jaxbBundle").get())
    implementation(libs.findBundle("springBootBundle").get())
    implementation(libs.findBundle("otherLibsBundle").get())
    //testFixtures
    testFixturesApi(libs.findBundle("kotlinTestBundle").get())
    testFixturesApi(libs.findBundle("kotestBundle").get())
    testFixturesApi(libs.findBundle("springTestBundle").get())
    testFixturesApi(libs.findBundle("http4kTestBundle").get())
    testFixturesApi(libs.findBundle("arrowTestBundle").get())
    testFixturesApi(libs.findBundle("dbTestBundle").get())
    testFixturesApi(libs.findBundle("mockTestBundle").get())
    //test
    testImplementation(libs.findBundle("kotlinTestBundle").get())
    testImplementation(libs.findBundle("kotestBundle").get())
    testImplementation(libs.findBundle("springTestBundle").get())
    testImplementation(libs.findBundle("http4kTestBundle").get())
    testImplementation(libs.findBundle("arrowTestBundle").get())
    testImplementation(libs.findBundle("dbTestBundle").get())
    testImplementation(libs.findBundle("mockTestBundle").get())
    testImplementation(kotlin("test"))
}