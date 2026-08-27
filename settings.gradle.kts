plugins {
    id("org.gradle.toolchains.foojay-resolver-convention") version "0.10.0"
}
dependencyResolutionManagement {
    @Suppress("UnstableApiUsage")
    repositories {
        google()
        mavenCentral()
    }
}

enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")

rootProject.name = "Offbalance"
include(
    ":app",

    ":core:ktx",
    ":core:money",
    ":core:event",
    ":core:state-holder",
    ":core:state-holder:compose",

    ":domain:app:data",
    ":domain:balance:core",
    ":domain:balance:data",
    ":domain:provider:core",
    ":domain:provider:data",
    ":domain:settings:core",
    ":domain:settings:data",

    ":feature:dashboard:state-holder",
    ":feature:dashboard:compose",
    ":feature:history:state-holder",
    ":feature:history:compose",
    ":feature:settings:state-holder",
    ":feature:settings:compose",

    ":ui:app",
    ":ui:foundation",
)
