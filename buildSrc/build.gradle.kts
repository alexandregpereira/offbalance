plugins {
    kotlin("jvm") version "2.1.20"
}

repositories {
    google()
    mavenCentral()
    gradlePluginPortal()
}

dependencies {
    implementation(kotlin("gradle-plugin"))
    implementation(libs.gradle.android)
    compileOnly(kotlin("compiler-embeddable"))
}
