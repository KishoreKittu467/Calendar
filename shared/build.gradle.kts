import org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi
import org.jetbrains.kotlin.gradle.ExperimentalWasmDsl
import org.jetbrains.kotlin.gradle.targets.js.webpack.KotlinWebpackConfig

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidKmpLibrary)
}

kotlin {

    androidLibrary {
        compileSdk = libs.versions.android.compileSdk.get().toInt()
        minSdk = libs.versions.android.minSdk.get().toInt()
        namespace = "com.kkapps.bubbles.shared"
        experimentalProperties["android.experimental.kmp.enableAndroidResources"] = true
        experimentalProperties["android.experimental.dsl.compose.enabled"] = true
    }

    @OptIn(ExperimentalWasmDsl::class)
    wasmJs {
        browser {
            commonWebpackConfig {
                devServer = (devServer ?: KotlinWebpackConfig.DevServer()).apply {
                    static(project.projectDir.path)
                }
            }
        }
    }
    
    iosX64()
    iosArm64()
    iosSimulatorArm64()

    // 2 jvm targets not working in KMP projects
//    jvm("desktop")
    jvm()

    @OptIn(ExperimentalKotlinGradlePluginApi::class)
    applyDefaultHierarchyTemplate {
        // create a new group that
        // depends on `common`
        common {
            // Define group name without
            // `Main` as suffix
            group("nonJs") {
                // Provide which targets would
                // be part of this group
                withAndroidTarget()
                withJvm()
                group("ios") {
                    withIos()
                }
            }
        }
    }

    sourceSets {
        commonMain.dependencies {
            // put your Multiplatform dependencies here
        }
    }
}