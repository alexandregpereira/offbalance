plugins {
    kotlin("multiplatform")
}

multiplatform {
    commonMain {
        api(projects.domain.provider.core)
    }
    jvmMain()
}
