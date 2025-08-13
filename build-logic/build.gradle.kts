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
    implementation(vtechLibs.findLibrary("gradlePluginKotlin").get())
    implementation(vtechLibs.findLibrary("gradlePluginKotlinSpring").get())
    implementation(vtechLibs.findLibrary("gradlePluginSpringBoot").get())
    implementation(vtechLibs.findLibrary("gradlePluginSpringManagement").get())
    implementation(vtechLibs.findLibrary("gradlePluginGoogleKsp").get())
}