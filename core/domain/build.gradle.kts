plugins {
    id("xchange.android.library")
    id("xchange.kapt")
    id("xchange.android.hilt")
}

android {
    namespace = "com.pd.xchange.domain"
}

dependencies {
    implementation(project(":core:jvm"))
    implementation(project(":core:common"))
    implementation(libs.kotlinx.coroutines.core)
    kapt(libs.hilt.core.compiler)
    testImplementation(libs.junit4)
    testImplementation(libs.kotest.property)
    testImplementation(libs.mockK)
    testImplementation(libs.kotlinx.coroutines.test)
}
