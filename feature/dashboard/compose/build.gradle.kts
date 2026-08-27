plugins {
    id("com.android.library")
    kotlin("multiplatform")
    alias(libs.plugins.compose)
    alias(libs.plugins.compose.compiler)
}

androidLibrary {
    namespace = "br.alexandregpereira.offbalance.feature.dashboard.compose"
}

multiplatform {
    commonMain {
        api(projects.feature.dashboard.stateHolder)
        implementation(projects.core.stateHolder.compose)
        implementation(projects.ui.foundation)
    }
    androidMain()
    jvmMain()
}
