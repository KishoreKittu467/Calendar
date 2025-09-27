# WARP.md

This file provides guidance to WARP (warp.dev) when working with code in this repository.

## Project Architecture

This is a **Kotlin Multiplatform (KMP) project** called "Bubbles" targeting Android, iOS, Desktop (JVM), Web (WebAssembly), and Server platforms using Compose Multiplatform for UI and Ktor for the backend.

### High-Level Architecture

**Multi-module Structure:**
- `composeApp/` - Main application module containing platform-specific UI implementations and shared Compose UI
- `shared/` - Shared business logic and data models across all platforms (currently minimal)
- `server/` - Ktor-based server application
- `deps/` - External dependencies and custom libraries including:
  - `deps/ui/` - Shared UI components
  - `deps/libs/calendar/` - Custom calendar library
  - `deps/libs/rich_text_editor/` - Rich text editing components

**Platform Targeting Strategy:**
- Uses Kotlin's `applyDefaultHierarchyTemplate` with custom source set groups:
  - `nonJs` group: Android, Desktop (JVM), and iOS
  - `wasmJs` group: Web platform using WebAssembly
- Platform-specific implementations in respective source sets (`androidMain`, `iosMain`, `desktopMain`, `wasmJsMain`)

**Key Architectural Patterns:**
- **MVVM** with ViewModels for presentation layer
- **Repository pattern** for data access abstraction
- **Dependency Injection** using Koin with platform-specific modules
- **Navigation** using Jetpack Compose Navigation with type-safe routes
- **Clean Architecture** layers: `presentation/`, `domain/`, `data/`, `core/`

### Technology Stack

- **UI Framework**: Compose Multiplatform with Material3
- **Navigation**: Jetpack Compose Navigation (type-safe)
- **DI**: Koin with platform-specific modules
- **Database**: Room with SQLite (for non-JS platforms)
- **HTTP Client**: Ktor Client with platform-specific engines
- **Image Loading**: Coil 3 with platform support
- **Serialization**: Kotlinx Serialization
- **Async**: Kotlinx Coroutines

### Application Structure

The app follows a **feature-based package structure** under `features/book/`:
- `presentation/` - UI screens, ViewModels, and UI state
- `domain/` - Business logic, entities, and repository interfaces  
- `data/` - Data sources, repository implementations, database, and network

**Navigation Architecture:**
- Type-safe routes defined in `Route.kt` using Kotlinx Serialization
- Nested navigation graph (`BookGraph` containing `BookList` and `BookDetail`)
- Shared ViewModels between screens using custom Koin extension

## Essential Commands

### Building and Running

**Android:**
```bash
# Install debug APK to connected device/emulator
./gradlew :composeApp:installDebug

# Install and launch automatically
./gradlew :composeApp:installDebug --no-daemon && adb shell monkey -p com.kkapps.bubbles -c android.intent.category.LAUNCHER 1
```

**iOS:**
```bash
# Generate iOS framework and open Xcode project
./gradlew :composeApp:embedAndSignAppleFrameworkForXcode
# Then open iosApp/iosApp.xcodeproj in Xcode
```

**Desktop:**
```bash
# Run desktop application
./gradlew :composeApp:run
```

**Web (WebAssembly):**
```bash
# Run development server for web version
./gradlew :composeApp:wasmJsBrowserDevelopmentRun --info
```

**Server:**
```bash
# Run Ktor server (development mode enabled via gradle.properties)
./gradlew :server:run
```

### Development Commands

**Clean Build:**
```bash
# Clean and rebuild entire project
./gradlew clean build
```

**Database Schema:**
```bash
# Room schema files are generated in composeApp/schemas/
# After Room entity changes, rebuild to update schemas
./gradlew :composeApp:kspDebugKotlinAndroid
```

**Platform-Specific Builds:**
```bash
# Build for specific platform only
./gradlew :composeApp:compileKotlinAndroid
./gradlew :composeApp:compileKotlinDesktop  
./gradlew :composeApp:compileKotlinIos
./gradlew :composeApp:compileKotlinWasmJs
```

### Demo Applications

**Calendar Demo:**
```bash
# Android
./gradlew :deps:libs:calendar:compose-multiplatform:sample:installDebug
# Desktop  
./gradlew :deps:libs:calendar:compose-multiplatform:sample:run
```

**Rich Text Editor Demo:**
```bash
# Android
./gradlew :deps:libs:rich_text_editor:sample:android:installDebug
# Desktop
./gradlew :deps:libs:rich_text_editor:sample:desktop:run
# Web
./gradlew :deps:libs:rich_text_editor:sample:web:wasmJsBrowserDevelopmentRun
```

## Key Implementation Details

### Dependency Injection Setup

Each platform has specific DI modules:
- `Modules.kt` - Common feature and shared modules
- `Modules.android.kt` - Android-specific implementations (Room database)
- `Modules.nonJs.kt` - Non-JavaScript platforms (Room, SQLite)
- `Modules.wasmJs.kt` - WebAssembly-specific implementations

### Database Architecture

- **Room Database** used for non-JS platforms with KSP code generation
- **WebAssembly** uses alternative storage (separate repository implementation)
- Platform-specific `DatabaseFactory` implementations in respective source sets
- Schemas stored in `composeApp/schemas/` directory

### HTTP Client Configuration

Platform-specific HTTP client engines:
- **Android**: OkHttp
- **iOS**: Darwin (NSURLSession)
- **Desktop**: OkHttp  
- **WebAssembly**: JS/WASM engine

### Build Configuration Notes

- **Java/Kotlin Version**: JVM 21 across all modules
- **Android**: minSdk 24, targetSdk 36, compileSdk 36
- **Gradle**: Configuration cache disabled due to WebAssembly compatibility
- **WebAssembly**: Special webpack configuration for development server
- **KSP**: Room compiler excluded from WebAssembly builds

### Resource Management

- **Compose Resources**: Shared resources in `src/commonMain/composeResources/`
- **Platform Resources**: Android resources in `src/androidMain/res/`
- **Web Assets**: Static files in `src/wasmJsMain/resources/`

## Development Workflow

1. **Feature Development**: Create new features in `features/` following existing package structure
2. **Platform Testing**: Test on multiple platforms using respective run commands
3. **Database Changes**: Update Room entities and rebuild to generate new schemas
4. **DI Configuration**: Add new dependencies to appropriate platform-specific modules
5. **Navigation**: Define new routes in `Route.kt` and add to navigation graph in `App.kt`

## Important Configuration Files

- `gradle/libs.versions.toml` - Centralized version catalog for all dependencies
- `gradle.properties` - Gradle and platform-specific build configurations
- `settings.gradle.kts` - Project structure and included modules
- `google-services.json` - Firebase configuration (Android)
- Platform build files: `composeApp/build.gradle.kts`, `server/build.gradle.kts`, `shared/build.gradle.kts`