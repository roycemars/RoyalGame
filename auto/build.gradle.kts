plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
}

android {
    namespace = "com.roycemars.royalgame.auto"
    compileSdk = 36

    defaultConfig {
        applicationId = "com.roycemars.royalgame.auto"
        minSdk = 26
        targetSdk = 36
        versionCode = 1
        versionName = "1.0"
    }
}

dependencies {
    implementation(libs.androidx.car.app)
}
