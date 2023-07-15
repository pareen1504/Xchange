plugins {
    id("xchange.android.feature")
    id("xchange.android.library.compose")
}

android {
    namespace = "com.pd.xchange.features.currencyconverter"
    testOptions {
        unitTests.isReturnDefaultValues = true
    }
}

dependencies {
    implementation(libs.ui)
    implementation(libs.ui.graphics)
    implementation(libs.ui.tooling.preview)
    implementation(libs.ui.tooling.preview)
    implementation(libs.material3)

    testImplementation(libs.junit4)
    testImplementation(libs.kotest.property)
    testImplementation(libs.bundles.mockk)
    testImplementation(libs.kotlinx.coroutines.test)
}
