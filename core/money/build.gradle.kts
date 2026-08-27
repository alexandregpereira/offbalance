plugins {
    kotlin("multiplatform")
}

multiplatform {
    commonMain()
    jvmMain()
    jvmTest {
        implementation(libs.bundles.unittest)
    }
}
