plugins {
    id("org.jetbrains.kotlin.jvm")
    id("org.jetbrains.kotlin.kapt")
}

dependencies {
    implementation(project(":core:jvm"))
    implementation(libs.retrofit.core)
    implementation(libs.okhttp)
    implementation(libs.okhttp.logging)
    implementation(libs.retrofit.moshi.convertor)
    implementation(libs.hilt.core)
    implementation(libs.kotlinx.coroutines.core)
    kapt(libs.hilt.core.compiler)
    compileOnly(libs.json)
}
