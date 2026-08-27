plugins {
    id("com.android.library")
    kotlin("multiplatform")
    alias(libs.plugins.compose)
    alias(libs.plugins.compose.compiler)
}

androidLibrary {
    namespace = "br.alexandregpereira.offbalance.feature.history.compose"
}

multiplatform {
    commonMain {
        api(projects.feature.history.stateHolder)
        implementation(projects.core.stateHolder.compose)
        implementation(projects.ui.foundation)
    }
    androidMain()
    jvmMain()
}
