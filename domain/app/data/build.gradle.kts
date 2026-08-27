plugins {
    id("com.android.library")
    kotlin("multiplatform")
    id("app.cash.sqldelight")
}

androidLibrary(withCompose = false) {
    namespace = "br.alexandregpereira.offbalance.data"
}

multiplatform {
    commonMain {
        api(projects.domain.balance.data)
        api(projects.domain.provider.data)
        api(projects.domain.settings.data)
        implementation(projects.core.ktx)
        implementation(libs.koin.core)
        implementation(libs.sqldelight.runtime)
        implementation(libs.ktor.core)
        implementation(libs.ktor.content.negotiation)
        implementation(libs.ktor.json)
        implementation(libs.ktor.logging)
        implementation(libs.kotlin.serialization)
    }
    androidMain {
        implementation(libs.sqldelight.android)
        implementation(libs.ktor.okhttp)
    }
    jvmMain {
        implementation(libs.sqldelight.jvm)
        implementation(libs.ktor.okhttp)
    }
    jvmTest {
        implementation(libs.bundles.unittest)
        implementation(libs.sqldelight.jvm)
        implementation(libs.koin.core)
        implementation(libs.multiplatform.settings.test)
    }
}

sqldelight {
    databases {
        create("OffbalanceDatabase") {
            packageName.set("br.alexandregpereira.offbalance.database")
        }
    }
}
