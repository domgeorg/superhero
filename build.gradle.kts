buildscript {
    repositories {
        google()
        maven("https://plugins.gradle.org/m2/")
    }
}

plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.kotlin.jvm) apply false
    alias(libs.plugins.ksp) apply false
    alias(libs.plugins.hilt) apply false
    alias(libs.plugins.android.library) apply false
}

tasks.register("moveHookFile") {
    doLast {
        val sourceFile = file(".scripts/pre-push")
        val destinationDir = file(".git/hooks")

        copy {
            from(sourceFile)
            into(destinationDir)
        }
    }
}