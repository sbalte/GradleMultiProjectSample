@file:Suppress("unused")

package conventions

import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.plugins.JavaPluginExtension

class LibraryConvention : Plugin<Project> {
    override fun apply(project: Project) {
        project.plugins.apply("java-library") // Apply the Java plugin

        project.extensions.configure(JavaPluginExtension::class.java) {
            sourceCompatibility = org.gradle.api.JavaVersion.VERSION_17
            targetCompatibility = org.gradle.api.JavaVersion.VERSION_17
        }

        project.repositories.mavenCentral() // Add Maven Central repository
    }
}