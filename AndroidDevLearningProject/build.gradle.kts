    // Top-level build file where you can add configuration options common to all sub-projects/modules.

    buildscript {
        repositories {
            google()
        }
        dependencies {
            classpath(libs.androidx.navigation.safe.args.gradle.plugin)
        }
    }
    plugins {
        alias(libs.plugins.android.application) apply false
        alias(libs.plugins.kotlin.android) apply false
        alias(libs.plugins.kotlin.kapt) apply false
        id("com.google.dagger.hilt.android") version "2.56.2" apply false
        id("com.google.devtools.ksp") version "2.0.0-1.0.21" apply false
}