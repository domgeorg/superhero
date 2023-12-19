@Suppress("DSL_SCOPE_VIOLATION") // TODO: Remove once KTIJ-19369 is fixed
plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.jvm)
    alias(libs.plugins.ksp)
}

android {
    namespace = "com.georgiopoulos.feature_details"
    compileSdk = ProjectConfiguration.compileSdk

    defaultConfig {
        minSdk = ProjectConfiguration.minSdk

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }

    testOptions {
        unitTests {
            isIncludeAndroidResources = true
            isReturnDefaultValues = true
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.3"
    }
}

dependencies {
    implementation(project(":core-data"))
    implementation(project(":core-model"))
    implementation(project(":core-navigation"))
    implementation(project(":core-design-system"))
    implementation(project(":core-resources"))
    implementation(libs.core.ktx)
    implementation(libs.hilt.android)
    implementation(libs.androidx.hilt.navigation.compose)
    implementation(libs.androidx.compose.material3)
    implementation(libs.paging.compose)
    ksp(libs.hilt.compiler)
    implementation(libs.coil.base)
    implementation(libs.coil.compose)
    testImplementation(libs.junit)
    implementation(libs.mockk)
    implementation(libs.coroutines.test)
    implementation(libs.androidx.arch.core)
    androidTestImplementation(libs.androidx.test.ext.junit)
}