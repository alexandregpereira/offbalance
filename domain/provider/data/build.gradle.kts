plugins {
    kotlin("multiplatform")
    alias(libs.plugins.kotlin.serialization)
}

multiplatform {
    commonMain {
        api(projects.domain.provider.core)
        implementation(projects.core.ktx)
        implementation(libs.koin.core)
        implementation(libs.ktor.core)
        implementation(libs.ktor.content.negotiation)
        implementation(libs.ktor.json)
        implementation(libs.kotlin.serialization)
    }
    jvmMain()
    jvmTest {
        implementation(libs.bundles.unittest)
    }
}
