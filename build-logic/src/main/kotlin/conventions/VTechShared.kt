@file:Suppress("ConstPropertyName")

package conventions

import org.gradle.api.artifacts.ExternalModuleDependencyBundle
import org.gradle.api.artifacts.MinimalExternalModuleDependency
import org.gradle.api.artifacts.VersionCatalog
import org.gradle.api.provider.Provider

object VTechShared {
    const val JavaVersion: Int = 17
    val kotlinLangArgs = listOf("-Xjsr305=strict",
        "-Xconsistent-data-class-copy-visibility",
        //Kotlin 2.2
        "-Xcontext-parameters",
        "-Xcontext-sensitive-resolution",
        "-Xannotation-target-all",
        "-Xannotation-default-target=param-property",
        "-Xnested-type-aliases",)
    val libraryList = listOf("exposedCcore", "exposedJdbc")
    val libraryBundlesList = listOf(
        "apacheBundle",
        "arrowBundle",
        "googleBundle",
        "http4kBundle",
        "kotlinCoreBundle",
        "mutinyBundle",
        "solaceBundle",
        "jacksonBundle",
        "jaxbBundle",
//        "springBootBundle",
//        "otherLibsBundle",
    )
    val testLibraryList = listOf(
        "kotlinTestBundle",
        "kotestBundle",
        "springTestBundle",
        "http4kTestBundle",
        "arrowTestBundle",
        "dbTestBundle",
        "mockTestBundle",
        "junitTestBundle",
    )
    fun handleBundle(library: VersionCatalog, bundleList: List<String>,): List<Pair<String, Provider<ExternalModuleDependencyBundle>>> =
        bundleList.map { it to library.findBundle(it).get() }
    fun handleLibrary(library: VersionCatalog, bundleList: List<String>,): List<Pair<String, Provider<MinimalExternalModuleDependency>>> =
        bundleList.map { it to library.findLibrary(it).get() }
}