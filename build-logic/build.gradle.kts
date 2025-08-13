plugins {
    `kotlin-dsl`
}
repositories {
    mavenCentral()
}

val vtechLibs = the<VersionCatalogsExtension>().named("vtechLibs")
val libs = the<VersionCatalogsExtension>().named("libs")
val testLibs = extensions.getByType(VersionCatalogsExtension::class.java).named("testLibs")
dependencies {
    implementation(vtechLibs.findBundle("gradlePluginBundle").get())
}