plugins {
    id("xchange.jvm.library")
    id("xchange.kapt")
    id("com.google.devtools.ksp")
}

dependencies {
    implementation(libs.kotlinx.coroutines.core)
    compileOnly(libs.json)
    implementation(libs.hilt.core)
    kapt(libs.hilt.core.compiler)
    implementation(libs.moshi)
    ksp(libs.moshi.codegen)
}