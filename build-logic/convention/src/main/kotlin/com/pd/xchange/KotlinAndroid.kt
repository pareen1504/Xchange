package com.pd.xchange

import com.android.build.api.dsl.CommonExtension
import org.gradle.api.JavaVersion
import org.gradle.api.Project
import org.gradle.api.plugins.JavaPluginExtension
import org.gradle.api.tasks.testing.Test
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.withType
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

internal fun Project.configureKotlinAndroid(
    commonExtension: CommonExtension<*, *, *, *>
) {
    commonExtension.apply {
        compileSdk = 33

        defaultConfig {
            minSdk = 21
        }

        compileOptions {
            sourceCompatibility = JavaVersion.VERSION_17
            targetCompatibility = JavaVersion.VERSION_17
        }

        buildFeatures()
        appBuildType()
        configureKotlin()
        testOptions()
    }
}

fun CommonExtension<*, *, *, *>.appBuildType() {
    buildTypes {
        named("debug") {
            buildConfigField(
                type = "String",
                name = "app_id",
                value = "\"\"" // Enter your debug api key here
            )

            buildConfigField(
                type = "String",
                name = "base_url",
                value = "\"https://openexchangerates.org\""
            )
        }
        named("release") {
            buildConfigField(
                type = "String",
                name = "app_id",
                value = "\"\"" // Enter your release api key here
            )

            buildConfigField(
                type = "String",
                name = "base_url",
                value = "\"https://openexchangerates.org\""
            )
        }
    }
}

fun CommonExtension<*, *, *, *>.buildFeatures() {
    buildFeatures.apply {
        buildConfig = true
    }
}

internal fun Project.configureKotlinJvm() {
    extensions.configure<JavaPluginExtension> {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    configureKotlin()
}

private fun Project.configureKotlin() {
    tasks.withType<KotlinCompile>().configureEach {
        kotlinOptions {
            jvmTarget = JavaVersion.VERSION_17.toString()
            freeCompilerArgs += kotlinFreeCompilerArgs
        }
    }
}

@Suppress("UnstableApiUsage")
fun CommonExtension<*, *, *, *>.testOptions() {
    testOptions {
        animationsDisabled = true
        unitTests.isIncludeAndroidResources = true
        unitTests.all { test: Test -> test.testLogging }
    }
}