plugins {
    id("com.android.library")
    kotlin("multiplatform")
}

androidLibrary(withCompose = false) {
    namespace = "br.alexandregpereira.offbalance.settings.data"
}

multiplatform {
    commonMain {
        api(projects.domain.settings.core)
        implementation(projects.core.ktx)
        implementation(libs.koin.core)
        api(libs.multiplatform.settings)
    }
    androidMain()
    jvmMain()
    jvmTest {
        implementation(libs.bundles.unittest)
    }
}
