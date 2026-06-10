import org.jetbrains.kotlin.gradle.ExperimentalWasmDsl
import org.jetbrains.kotlin.gradle.targets.js.webpack.KotlinWebpackConfig

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidKmpLibrary)
    alias(libs.plugins.jetbrainsCompose)
    alias(libs.plugins.composeCompiler)
}

kotlin {

    @OptIn(ExperimentalWasmDsl::class)
    wasmJs {
        outputModuleName = "BubblesUiWasm"
        browser {
            commonWebpackConfig {
                devServer = (devServer ?: KotlinWebpackConfig.DevServer()).apply {
                    static(project.projectDir.path)
                }
            }
        }
    }

    androidLibrary {
        compileSdk = libs.versions.android.compileSdk.get().toInt()
        minSdk = libs.versions.android.minSdk.get().toInt()
        namespace = "com.kkapps.bubbles.ui"
        experimentalProperties["android.experimental.kmp.enableAndroidResources"] = true
        experimentalProperties["android.experimental.dsl.compose.enabled"] = true
    }

    iosX64()
    iosArm64()
    iosSimulatorArm64()

    jvm()

    sourceSets {
        commonMain.dependencies {
            api(compose.ui)
            api(compose.runtime)
            api(compose.foundation)
            api(compose.material3)
            api(compose.animation)
            api(compose.uiUtil)
            api(compose.components.resources)
            api(compose.components.uiToolingPreview)
            api(libs.jetbrains.material.icons)
        }
    }
}

compose.resources {
    publicResClass = true
    generateResClass = auto
}