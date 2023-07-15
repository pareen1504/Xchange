pluginManagement {
    includeBuild("build-logic")
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "Xchange"
include(":app")
include(":core:jvm")
include(":core:common")
include(":core:data")
include(":core:database")
include(":core:domain")
include(":core:network")
include(":features:currencyconverter")