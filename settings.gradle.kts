rootProject.name = "Bubbles"
enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")

pluginManagement {
    includeBuild("deps/libs/rich_text_editor/convention-plugins")
    repositories {
        google {
            content {
                includeGroupByRegex("com\\.android.*")
                includeGroupByRegex("com\\.google.*")
                includeGroupByRegex("androidx.*")
            }
        }
        mavenCentral()
        gradlePluginPortal()
    }
}

dependencyResolutionManagement {
    @Suppress("UnstableApiUsage")
    repositories {
        google {
            content {
                includeGroupByRegex("com\\.android.*")
                includeGroupByRegex("com\\.google.*")
                includeGroupByRegex("androidx.*")
            }
        }
        mavenCentral()
        maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")

        maven("https://maven.pkg.jetbrains.space/kotlin/p/wasm/experimental")
    }
}

plugins {
    id("org.gradle.toolchains.foojay-resolver-convention") version "1.0.0"
}

// Optimise: Include only projects that are necessary
include(":shared")
include(":server")
include(":deps:ui")
include(":deps:libs:calendar:compose-multiplatform:library")
include(":deps:libs:calendar:compose-multiplatform:sample")
include(":deps:libs:rich_text_editor:richeditor-compose")
include(":deps:libs:rich_text_editor:richeditor-compose-coil3")
include(":deps:libs:rich_text_editor:sample:common")
include(":deps:libs:rich_text_editor:sample:android")
include(":deps:libs:rich_text_editor:sample:desktop")
include(":deps:libs:rich_text_editor:sample:web")
include(":composeApp")