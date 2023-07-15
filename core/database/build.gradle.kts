plugins {
    id("xchange.android.library")
    id("xchange.android.hilt")
    id("xchange.kapt")
}

android {
    namespace = "com.pd.xchange.database"

    defaultConfig {
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    packaging {
        resources {
            excludes.apply {
                add("META-INF/*")
            }
        }
    }
}

dependencies {
    implementation(libs.kotlinx.coroutines.android)
    implementation(libs.room.ktx)
    implementation(libs.room.runtime)
    kapt(libs.room.compiler)

    api(libs.androidx.test.core)
    api(libs.androidx.test.espresso.core)
    api(libs.androidx.test.rules)
    api(libs.androidx.test.runner)
    api(libs.hilt.android.testing)
    api(libs.junit4)
    api(libs.kotlinx.coroutines.test)
}
