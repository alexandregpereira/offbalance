plugins {
    id("com.android.library")
    kotlin("multiplatform")
    alias(libs.plugins.compose)
    alias(libs.plugins.compose.compiler)
}

androidLibrary {
    namespace = "br.alexandregpereira.offbalance.ui"
}

multiplatform {
    commonMain {
        api(compose.ui)
        api(compose.foundation)
        implementation(compose.material)
        implementation(compose.materialIconsExtended)
        implementation(compose.uiUtil)
    }
    androidMain {
        implementation(libs.android.compose.tooling.preview)
    }
    jvmMain()
}