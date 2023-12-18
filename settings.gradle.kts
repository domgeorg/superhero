@file:Suppress("UnstableApiUsage")



pluginManagement {
    repositories {
        gradlePluginPortal()
        google()
        mavenCentral()
    }
}

dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}
rootProject.name = "superhero"

include(":app")
include(":core-model")
include(":core-navigation")
include(":core-network")
include(":core-resources")
include(":core-data")
include(":core-database")
include(":core-design-system")
include(":core-preferences")
include(":feature-home")
include(":feature-details")