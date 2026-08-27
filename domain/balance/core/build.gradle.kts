plugins {
    kotlin("multiplatform")
}

multiplatform {
    commonMain {
        api(projects.core.money)
        api(libs.kotlin.datetime)
    }
    jvmMain()
}
