plugins {
    id("xchange.android.library")
    id("xchange.android.library.compose")
    id("xchange.android.hilt")
}

android {
    namespace = "com.pd.xchange.core.common"
}

dependencies {
    implementation(libs.kotlinx.coroutines.android)
    implementation(libs.material3)

    testImplementation(libs.kotlinx.coroutines.test)
    testImplementation(libs.junit4)
    testImplementation(libs.kotest.property)
    testImplementation(libs.bundles.mockk)
}