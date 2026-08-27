plugins {
    kotlin("multiplatform")
}

multiplatform {
    commonMain {
        api(projects.core.stateHolder)
        api(projects.domain.settings.core)
        implementation(projects.core.ktx)
        implementation(libs.koin.core)
    }
    jvmMain()
    jvmTest {
        implementation(libs.bundles.unittest)
    }
}
