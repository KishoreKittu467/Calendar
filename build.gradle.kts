
//import com.kizitonwose.calendar.buildsrc.Version
//import com.kizitonwose.calendar.buildsrc.Version.isNoPublish
//import com.kizitonwose.calendar.buildsrc.androidLibProjects
//import com.kizitonwose.calendar.buildsrc.multiplatformLibProjects
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    alias(libs.plugins.androidApplication) apply false
    alias(libs.plugins.androidLibrary) apply false
    alias(libs.plugins.kotlinJvm) apply false
//    alias(libs.plugins.kotlinAndroid) apply false
    alias(libs.plugins.composeCompiler) apply false
    alias(libs.plugins.kotlinter) apply false
    alias(libs.plugins.mavenPublish) apply false
    alias(libs.plugins.kotlinMultiplatform) apply false
    alias(libs.plugins.jetbrainsCompose) apply false
    alias(libs.plugins.kotlinSerialization) apply false
    alias(libs.plugins.versionCheck)
    alias(libs.plugins.bcv)
}

allprojects {
    apply(plugin = rootProject.libs.plugins.kotlinter.get().pluginId)

    tasks.withType<KotlinCompile> {
        compilerOptions {
            freeCompilerArgs.add("-Xwarning-level=NOTHING_TO_INLINE:disabled")
        }
    }
//    plugins.withType<KotlinBasePlugin> {
//        extensions.configure<KotlinProjectExtension> {
//            if ("sample" !in project.name) {
//                explicitApi()
//            }
//        }
//    }
    tasks.withType<Test> {
        useJUnitPlatform()
        // https://docs.gradle.org/8.8/userguide/performance.html#execute_tests_in_parallel
        maxParallelForks = (Runtime.getRuntime().availableProcessors() / 2).coerceAtLeast(1)
    }
    afterEvaluate {
        // Android and Multiplatform libraries are published separately
        // See https://github.com/kizitonwose/Calendar/pull/561
        disableMavenPublicationsIfNeeded(listOf("library"), "2.9.1-SNAPSHOT")
        disableMavenPublicationsIfNeeded(listOf("core", "data", "view", "compose"), "2.9.1-SNAPSHOT")
    }
}

fun Project.disableMavenPublicationsIfNeeded(
    projects: List<String>,
    version: String,
) {
    if (version == "NO_PUBLISH" && project.name in projects) {
        tasks.withType<AbstractPublishToMaven> {
            enabled = false
        }
    }
}

//apiValidation {
//    ignoredProjects += listOf(
//        "sample",
//    )
//
//    @OptIn(kotlinx.validation.ExperimentalBCVApi::class)
//    klib {
//        enabled = true
//    }
//}

// tasks.register<Delete>("clean").configure {
//    delete(rootProject.layout.buildDirectory)
// }
