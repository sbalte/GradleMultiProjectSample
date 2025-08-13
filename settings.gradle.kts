rootProject.name = "GradleMultiProjectSample"
includeBuild("build-logic")
include("vtech-shared", "vtech-app")
dependencyResolutionManagement {
    versionCatalogs {
        create("vtechLibs") {
            "${rootProject.projectDir}/gradle/vtech.versions.toml".also { tomlFile ->
                logger.info(">>>>Using version catalog file: $tomlFile for catalog name - vtechLibs")
                from(files(tomlFile))
            }
        }
        create("testLibs") {
            "${rootProject.projectDir}/gradle/test-libs.versions.toml".also { tomlFile ->
                logger.info(">>>>Using version catalog file: $tomlFile for catalog name - testLibs")
                from(files(tomlFile))
            }
        }
    }
}
