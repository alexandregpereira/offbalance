plugins {
    id("com.android.library")
    kotlin("multiplatform")
    alias(libs.plugins.compose)
    alias(libs.plugins.compose.compiler)
}

androidLibrary {
    namespace = "br.alexandregpereira.offbalance.state.compose"
}

multiplatform {
    commonMain {
        api(projects.core.stateHolder)
        api(compose.runtime)
        api(libs.koin.compose)
    }
    androidMain()
    jvmMain()
}
