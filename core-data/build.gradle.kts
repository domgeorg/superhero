@Suppress("DSL_SCOPE_VIOLATION") // TODO: Remove once KTIJ-19369 is fixed
plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.jvm)
    alias(libs.plugins.ksp)
}

android {
    namespace = "com.georgiopoulos.core_data"
    compileSdk = ProjectConfiguration.compileSdk

    defaultConfig {
        minSdk = ProjectConfiguration.minSdk

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
        debug {
            isMinifyEnabled = false
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {
    implementation(project(":core-network"))
    implementation(project(":core-model"))
    implementation(project(":core-database"))
    implementation(project(":core-preferences"))
    implementation(libs.core.ktx)
    implementation(libs.moshi)
    implementation(libs.timber)
    implementation(libs.hilt.android)
    implementation(libs.datastore)
    ksp(libs.hilt.compiler)
    implementation(libs.paging.common)
    testImplementation(libs.junit)
    implementation(libs.mockk)
    implementation(libs.coroutines.test)
    implementation(libs.androidx.arch.core)
}