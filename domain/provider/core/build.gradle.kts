plugins {
    kotlin("multiplatform")
}

multiplatform {
    commonMain {
        api(projects.domain.balance.core)
        api(projects.core.event)
    }
    jvmMain()
}
