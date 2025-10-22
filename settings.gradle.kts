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

// Independent modules
val shared = DynamicModule(":shared")
val server = DynamicModule(":server")
val ui = DynamicModule(":deps:ui")

// Library modules
val richTextEditor = DynamicModule(
    name = ":deps:libs:rich_text_editor:richeditor-compose"
)
val calendar = DynamicModule(":deps:libs:calendar:compose-multiplatform:library")

// App module
val composeApp = DynamicModule(
    name = ":composeApp",
    dependencies = setOf(shared, server, ui, calendar, richTextEditor)
)

// Demos and samples
val calendarSample = DynamicModule(
    name = ":deps:libs:calendar:compose-multiplatform:sample",
    dependencies = setOf(calendar)
)
val richTextEditorSample = DynamicModule(
    name = ":deps:libs:rich_text_editor:sample",
    dependencies = setOf(
        richTextEditor,
        DynamicModule(":deps:libs:rich_text_editor:richeditor-compose-coil3"),
        DynamicModule(":deps:libs:rich_text_editor:sample:common"),
        DynamicModule(":deps:libs:rich_text_editor:sample:android"),
        DynamicModule(":deps:libs:rich_text_editor:sample:desktop"),
        DynamicModule(":deps:libs:rich_text_editor:sample:web")
    )
)

val enabledRootModules = setOf(
    composeApp,
    richTextEditorSample,
    calendarSample
)

fun includeModulesRecursively(
    remainingModules: Set<DynamicModule>,
    alreadyIncludedModules: MutableSet<String> = mutableSetOf()
) {
    remainingModules.forEach { module ->
        if (module.name !in alreadyIncludedModules) {
            if (module.dependencies.isNotEmpty()) {
                includeModulesRecursively(module.dependencies, alreadyIncludedModules)
            }
            // Now include the module itself
            include(module.name)
            alreadyIncludedModules.add(module.name)
        }
    }
}

data class DynamicModule(
    val name: String,
    val dependencies: Set<DynamicModule> = emptySet()
)

includeModulesRecursively(enabledRootModules)