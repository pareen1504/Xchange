plugins {
    id("xchange.android.library")
    id("xchange.kapt")
    id("xchange.android.hilt")
    id("com.google.devtools.ksp")
    id("kotlinx-serialization")
}

android {
    namespace = "com.pd.xchange.data"
}

dependencies {
    implementation(project(":core:jvm"))
    implementation(project(":core:database"))
    implementation(project(":core:common"))
    implementation(project(":core:domain"))
    implementation(project(":core:network"))
    implementation(libs.kotlinx.coroutines.core)
    implementation(libs.moshi)
    implementation(libs.moshi.adapters)
    ksp(libs.moshi.codegen)
    implementation(libs.retrofit.core)

    testImplementation(libs.junit4)
    testImplementation(libs.kotest.property)
    testImplementation(libs.mock.webserver)
    testImplementation(libs.retrofit.moshi.convertor)
    testImplementation(libs.bundles.mockk)
    testImplementation(libs.kotlinx.coroutines.test)
}
