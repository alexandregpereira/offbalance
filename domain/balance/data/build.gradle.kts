plugins {
    kotlin("multiplatform")
}

multiplatform {
    commonMain {
        api(projects.domain.balance.core)
        implementation(projects.core.ktx)
        implementation(libs.koin.core)
    }
    jvmMain()
    jvmTest {
        implementation(libs.bundles.unittest)
    }
}
