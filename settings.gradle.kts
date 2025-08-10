dependencyResolutionManagement {
    @Suppress("UnstableApiUsage")
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "Offbalance"
include(
    ":app",
    ":ui:foundation"
)
 