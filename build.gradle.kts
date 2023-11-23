buildscript {
    repositories {
        gradlePluginPortal()
    }
    dependencies {
        classpath (libs.moko.classpath)
    }
}

plugins {
    // this is necessary to avoid the plugins to be loaded multiple times
    // in each subproject's classloader
    alias(libs.plugins.jetbrainsCompose) apply false
    alias(libs.plugins.androidApplication) apply false
    alias(libs.plugins.kotlinMultiplatform) apply false
    id("com.android.library").apply(false)
    id("com.google.gms.google-services").version("4.3.14").apply(false) // This line
}
