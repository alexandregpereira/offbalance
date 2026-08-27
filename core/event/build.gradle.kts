plugins {
    kotlin("multiplatform")
}

multiplatform {
    commonMain {
        api(libs.kotlin.coroutines.core)
    }
    jvmMain()
    jvmTest {
        implementation(libs.bundles.unittest)
    }
}
