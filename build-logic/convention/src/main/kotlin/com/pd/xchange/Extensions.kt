package com.pd.xchange

import org.gradle.api.Project
import org.gradle.api.artifacts.VersionCatalog
import org.gradle.api.artifacts.VersionCatalogsExtension
import org.gradle.kotlin.dsl.getByType

val Project.libs
    get(): VersionCatalog = extensions.getByType<VersionCatalogsExtension>().named("libs")

inline val kotlinFreeCompilerArgs: List<String>
    get() = listOf(
        "-opt-in=kotlin.RequiresOptIn",
        "-opt-in=kotlinx.coroutines.ExperimentalCoroutinesApi",
    )
