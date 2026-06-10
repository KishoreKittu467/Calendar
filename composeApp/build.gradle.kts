import org.jetbrains.compose.desktop.application.dsl.TargetFormat
import org.jetbrains.kotlin.gradle.ExperimentalWasmDsl
import org.jetbrains.kotlin.gradle.targets.js.webpack.KotlinWebpackConfig

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidKmpLibrary)
    alias(libs.plugins.jetbrainsCompose)
    alias(libs.plugins.composeCompiler)
    alias(libs.plugins.googleServices)
    alias(libs.plugins.kotlinSerialization)
    alias(libs.plugins.ksp)
    alias(libs.plugins.room)
}

kotlin {

    androidLibrary {
        compileSdk = libs.versions.android.compileSdk.get().toInt()
        minSdk = libs.versions.android.minSdk.get().toInt()
        namespace = "com.kkapps.bubbles"
        experimentalProperties["android.experimental.kmp.enableAndroidResources"] = true
        experimentalProperties["android.experimental.dsl.compose.enabled"] = true
    }

//    sourceSets["main"].resources.srcDirs("src/commonMain/composeResources")

    @OptIn(ExperimentalWasmDsl::class)
    wasmJs {
        outputModuleName = "BubblesApp"
        browser {
            commonWebpackConfig {
                outputFileName = "BubblesApp.js"
                devServer = (devServer ?: KotlinWebpackConfig.DevServer()).apply {
//                    static = (static ?: mutableListOf()).apply {
//                        // Serve sources to debug inside browser
//                        add(project.projectDir.path)
//                    }
                    static(project.projectDir.path)
                }
            }
        }
        binaries.executable()
    }

    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64()
    ).forEach { iosTarget ->
        iosTarget.binaries.framework {
            baseName = "BubblesApp"
            isStatic = true

            freeCompilerArgs += listOf(
                "-Xbinary=bundleId=com.kkapps.bubbles"
            )
        }
    }

    jvm("desktop")

    room {
        schemaDirectory("$projectDir/schemas/")
    }

    sourceSets {
        val commonMain by getting
        val wasmJsMain by getting
        
        // Create nonJs source set
        val nonJsMain by creating {
            dependsOn(commonMain)
            kotlin.srcDir("build/generated/ksp/nonJsMain")
            dependencies {
                implementation(libs.koin.core.coroutines)
                api(libs.androidx.room.runtime)
                api(libs.sqlite.bundled)
            }
        }
        
        val androidMain by getting {
            dependsOn(nonJsMain)
        }
        
        val iosX64Main by getting
        val iosArm64Main by getting
        val iosSimulatorArm64Main by getting
        
        val iosMain by creating {
            dependsOn(nonJsMain)
            iosX64Main.dependsOn(this)
            iosArm64Main.dependsOn(this)
            iosSimulatorArm64Main.dependsOn(this)
        }
        
        val desktopMain by getting {
            dependsOn(nonJsMain)
        }
        
        androidMain.dependencies {
            implementation(libs.koin.android)
            implementation(libs.koin.androidx.compose)
            implementation(libs.compose.activity)
            implementation(libs.ktor.client.okhttp)
        }
        
        commonMain.dependencies {
            implementation(compose.runtime)
            implementation(compose.foundation)
            implementation(compose.material3)
            implementation(compose.ui)
            implementation(compose.components.resources)
            implementation(compose.components.uiToolingPreview)

            implementation(libs.androidx.lifecycle.viewmodel)
            implementation(libs.androidx.lifecycle.runtime.compose)
            implementation(libs.jetbrains.compose.navigation)
            implementation(libs.jetbrains.material.icons)
            implementation(libs.kotlinx.serialization.json)
            api(libs.koin.core)

            implementation(libs.bundles.coil)
            implementation(libs.bundles.ktor.client)
            implementation(libs.bundles.koin)

            implementation(libs.kotlinx.datetime)
            implementation(projects.deps.libs.calendar.composeMultiplatform.library)

            implementation(projects.shared)
            implementation(projects.deps.ui)
        }
        
        desktopMain.dependencies {
            implementation(compose.desktop.currentOs)
            implementation(libs.kotlinx.coroutines.swing)
            implementation(libs.ktor.client.okhttp)
        }
        
        iosMain.dependencies {
            implementation(libs.ktor.client.darwin)
        }
        
        wasmJsMain.dependencies {
            implementation(libs.ktor.client.js)
        }
    }
    compilerOptions {
        freeCompilerArgs.add("-Xexpect-actual-classes")
    }
}

dependencies {
    // ksp room compiler will not compile for wasmJs
    // use target specific ksp: https://kotlinlang.org/docs/ksp-multiplatform.html
    "ksp"(libs.androidx.room.compiler)
}

compose.desktop {
    application {
        mainClass = "com.kkapps.bubbles.MainKt"

        nativeDistributions {
            targetFormats(TargetFormat.Dmg, TargetFormat.Msi, TargetFormat.Deb)
            packageName = "com.kkapps.bubbles"
            packageVersion = "1.0.0"
        }
    }
}
tasks.withType<com.google.devtools.ksp.gradle.KspAATask>().configureEach {
    // Ensure this specific KSP task depends on the resource generator task
    if (name == "kspDebugKotlinAndroid") {
        dependsOn("generateResourceAccessorsForAndroidDebug")
        dependsOn("generateResourceAccessorsForAndroidMain")
        dependsOn("generateActualResourceCollectorsForAndroidMain")
        dependsOn("generateResourceAccessorsForNonJsMain")
        dependsOn("generateComposeResClass")
        dependsOn("generateResourceAccessorsForCommonMain")
        dependsOn("generateExpectResourceCollectorsForCommonMain")
    }
    // You might also want to do this for release builds or other variants
    if (name == "kspReleaseKotlinAndroid") {
        dependsOn("generateResourceAccessorsForAndroidRelease")
        dependsOn("generateResourceAccessorsForAndroidMain")
        dependsOn("generateActualResourceCollectorsForAndroidMain")
        dependsOn("generateResourceAccessorsForNonJsMain")
        dependsOn("generateComposeResClass")
        dependsOn("generateResourceAccessorsForCommonMain")
        dependsOn("generateExpectResourceCollectorsForCommonMain")
    }
    // Add more for other build variants if necessary
}

tasks.named("wasmJsBrowserDevelopmentRun") {
    notCompatibleWithConfigurationCache("Reason: Uses Project object or ClassLoader internally")
}