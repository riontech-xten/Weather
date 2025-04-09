plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.kapt)
    alias(libs.plugins.navigation.safe.args)
    alias(libs.plugins.kotlin.parcelize)
    alias(libs.plugins.dagger.hilt)
    alias(libs.plugins.google.services)
    alias(libs.plugins.google.android.libraries.mapsplatform.secrets.gradle.plugin)
}

android {
    namespace = "com.xtensolutions.weatherapp"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.xtensolutions.weatherapp"
        minSdk = 26
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }

    buildTypes {
        getByName("debug") {
            isMinifyEnabled = false
            buildConfigField("String", "BASE_URL", "\"http://api.openweathermap.org/data/2.5/\"")
            buildConfigField("String", "API_KEY", "\"7c6bdcef0221d82e22a242700e57d080\"")
            buildConfigField("boolean", "LOG", "true")
        }

        getByName("release") {
            isMinifyEnabled = false
            buildConfigField("String", "BASE_URL", "\"http://api.openweathermap.org/data/2.5/\"")
            buildConfigField("String", "API_KEY", "\"7c6bdcef0221d82e22a242700e57d080\"")
            buildConfigField("boolean", "LOG", "false")
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }

    buildFeatures {
        viewBinding = true
        dataBinding = true
        buildConfig = true
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)

    implementation(libs.play.services.maps)
    implementation(libs.play.services.location)
    implementation(libs.constraintlayout)

    // Hilt dependencies
    implementation(libs.dagger.hilt.android)
    implementation(libs.androidx.vectordrawable)
    implementation(libs.androidx.preference)
    kapt(libs.dagger.hilt.android.compiler)

// Hilt ViewModel extension
//    implementation(libs.androidx.hilt.lifecycle.viewmodel)
//    kapt(libs.androidx.hilt.compiler)
    implementation(libs.androidx.fragment.ktx)

// coroutines dependencies
    implementation(libs.kotlinx.coroutines.android)
    implementation(libs.kotlinx.coroutines.core)

// Lifecycle dependencies
    implementation(libs.androidx.lifecycle.extensions)
    implementation(libs.androidx.lifecycle.livedata.ktx)
    implementation(libs.androidx.lifecycle.viewmodel.ktx)
    implementation(libs.androidx.lifecycle.common.java8)

// Navigation ui dependencies
    implementation(libs.androidx.navigation.fragment.ktx)
    implementation(libs.androidx.navigation.ui.ktx)

// Retrofit dependencies
    implementation(libs.retrofit2)
    implementation(libs.retrofit2.converter.gson)
    implementation(libs.okhttp3.logging.interceptor)

// Room
    implementation(libs.androidx.room.runtime)
    kapt(libs.androidx.room.compiler)
    implementation(libs.androidx.room.ktx)

// Glide
    implementation(libs.glide)
    annotationProcessor(libs.glide.compiler)

    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(libs.androidx.arch.core.testing)

// Hilt testing dependency
    androidTestImplementation(libs.dagger.hilt.android.testing)
// Make Hilt generate code in the androidTest folder
    kaptAndroidTest(libs.dagger.hilt.android.compiler)
}
