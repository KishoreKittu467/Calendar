
//import com.kizitonwose.calendar.buildsrc.Android
//import com.kizitonwose.calendar.buildsrc.Config
//import com.kizitonwose.calendar.buildsrc.Version
import org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi
import org.jetbrains.kotlin.gradle.ExperimentalWasmDsl

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidLibrary)
    alias(libs.plugins.jetbrainsCompose)
    alias(libs.plugins.composeCompiler)
    alias(libs.plugins.kotlinSerialization)
    alias(libs.plugins.mavenPublish)
}

kotlin {
    @OptIn(ExperimentalWasmDsl::class)
    wasmJs {
        outputModuleName = "CalendarLibraryWasm"
        browser {}
        binaries.library()
        binaries.executable()
    }

    js(IR) {
        outputModuleName = "CalendarLibraryJs"
        browser()
        binaries.executable()
    }

    androidTarget {
        publishLibraryVariants("release")
    }

    jvm("desktop")

    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64(),
    ).forEach { iosTarget ->
        iosTarget.binaries.framework {
            baseName = "ComposeApp"
            isStatic = true
        }
    }

    applyDefaultHierarchyTemplate()

    sourceSets {
        val commonMain by getting
        val wasmJsMain by getting
        val nativeMain by getting
        val desktopMain by getting
        val androidMain by getting
        val jvmMain by creating {
            dependsOn(commonMain)
        }
        androidMain.dependsOn(jvmMain)
        androidMain.dependencies {
            implementation(compose.preview)
        }
        commonMain.dependencies {
            implementation(compose.runtime)
            implementation(compose.foundation)
            implementation(compose.ui)
            implementation(compose.components.resources)
            implementation(compose.components.uiToolingPreview)
            implementation(libs.androidx.annotation)
            implementation(libs.kotlinx.serialization.core)
            api(libs.kotlinx.datetime)
        }

        val nonJvmMain by creating {
            dependsOn(commonMain)
            nativeMain.dependsOn(this)
            wasmJsMain.dependsOn(this)
            dependencies {
                api(libs.kotlinx.serialization.core)
            }
        }
        desktopMain.dependsOn(jvmMain)
        desktopMain.dependencies {
            implementation(compose.desktop.currentOs)
        }
        commonTest.dependencies {
            implementation(libs.kotlin.test)
            implementation(libs.kotlinx.serialization.json)
        }
    }
    @OptIn(ExperimentalKotlinGradlePluginApi::class)
    compilerOptions {
        freeCompilerArgs.add("-Xexpect-actual-classes")
    }
}

android {
    namespace = "com.kizitonwose.calendar.compose.multiplatform"
    compileSdk = libs.versions.android.compileSdk.get().toInt()

    sourceSets["main"].manifest.srcFile("src/androidMain/AndroidManifest.xml")
    sourceSets["main"].res.srcDirs("src/androidMain/res")

    defaultConfig {
        minSdk = libs.versions.android.minSdk.get().toInt()
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
    java {
        toolchain {
            languageVersion.set(
                JavaLanguageVersion.of(JavaVersion.VERSION_21.majorVersion.toInt())
            )
        }
    }
    kotlin {
        jvmToolchain {
            languageVersion.set(
                JavaLanguageVersion.of(JavaVersion.VERSION_21.majorVersion.toInt())
            )
        }
        compilerOptions {
            optIn.add("kotlin.time.ExperimentalTime")
        }
    }
    buildFeatures {
        compose = true
    }
    dependencies {
        debugImplementation(compose.uiTooling)
    }
}

mavenPublishing {
    coordinates(version = "2.9.1-SNAPSHOT")
}
