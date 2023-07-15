import com.pd.xchange.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.project

class AndroidFeatureConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            pluginManager.apply {
                apply("xchange.android.library")
                apply("xchange.android.hilt")
            }

            dependencies {
                add("implementation", project(":core:common"))
                add("implementation", project(":core:data"))
                add("implementation", project(":core:domain"))
                add("implementation", project(":core:jvm"))
                add("implementation", libs.findLibrary("material").get())
                add("implementation", libs.findLibrary("appcompat").get())
                add("implementation", libs.findLibrary("androidx.lifecycle.runtimeCompose").get())
                add("implementation", libs.findLibrary("androidx.lifecycle.viewModelCompose").get())
                add("implementation", libs.findLibrary("androidx.hilt.navigation.compose").get())
                add("implementation", libs.findLibrary("activity-compose").get())
                add("implementation", libs.findLibrary("kotlinx.coroutines.android").get())
            }
        }
    }
}
