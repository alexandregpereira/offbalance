plugins {
    id("com.android.library")
    kotlin("multiplatform")
    alias(libs.plugins.compose)
    alias(libs.plugins.compose.compiler)
}

androidLibrary {
    namespace = "br.alexandregpereira.offbalance.ui.foundation"
}

multiplatform {
    commonMain {
        api(compose.ui)
        api(compose.material)
        api(compose.materialIconsExtended)
        api(compose.uiUtil)
    }
    androidMain()
    jvmMain()
}