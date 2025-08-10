plugins {
    id("com.android.library")
    kotlin("multiplatform")
    alias(libs.plugins.compose)
    alias(libs.plugins.compose.compiler)
}

androidLibrary {
    namespace = "br.alexandregpereira.offbalance.ui.app"
}

multiplatform {
    commonMain {
        implementation(project(":ui:foundation"))
        implementation(compose.material)
    }
    androidMain {
        implementation(libs.android.compose.tooling.preview)
    }
    jvmMain {
        implementation(compose.desktop.currentOs)
    }
}