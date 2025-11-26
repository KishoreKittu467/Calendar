🫧 Bubbles

This is a Kotlin Multiplatform project targeting Android, iOS, Web, Desktop, Server.

* `/composeApp` is for code that will be shared across your Compose Multiplatform applications.
  It contains several subfolders:
  - `commonMain` is for code that’s common for all targets.
  - Other folders are for Kotlin code that will be compiled for only the platform indicated in the folder name.
    For example, if you want to use Apple’s CoreCrypto for the iOS part of your Kotlin app,
    `iosMain` would be the right folder for such calls.

* `/iosApp` contains iOS applications. Even if you’re sharing your UI with Compose Multiplatform, 
  you need this entry point for your iOS app. This is also where you should add SwiftUI code for your project.

* `/server` is for the Ktor server application.

* `/shared` is for the code that will be shared between all targets in the project.
  The most important subfolder is `commonMain`. If preferred, you can add code to the platform-specific folders here too.


Learn more about [Kotlin Multiplatform](https://www.jetbrains.com/help/kotlin-multiplatform-dev/get-started.html),
[Compose Multiplatform](https://github.com/JetBrains/compose-multiplatform/#compose-multiplatform),
[Kotlin/Wasm](https://kotl.in/wasm/)…

We would appreciate your feedback on Compose/Web and Kotlin/Wasm in the public Slack channel [#compose-web](https://slack-chats.kotlinlang.org/c/compose-web).
If you face any issues, please report them on [GitHub](https://github.com/JetBrains/compose-multiplatform/issues).

Retrieve elements fast: Map<String, Any>
No duplicates: Set<Any>
Maintain order: List<Any>
No duplicates & maintain order: 

Running the apps on different platforms:

## Bubbles App

- [x] Android : `AndroidApp`
    - [x] or run in terminal: `./gradlew :composeApp:installDebug && adb shell monkey -p com.kkapps.bubbles -c android.intent.category.LAUNCHER 1`
- [x] iOS: Select `iosApp`
- [x] Desktop: `./gradlew :composeApp:run`
- [x] Web: `./gradlew :composeApp:wasmJsBrowserDevelopmentRun --info`
    - [ ] `PREREQUISITE:` Keep `org.gradle.configuration-cache=false` in `gradle.properties`
- [x] Server: `./gradlew :server:run`
- 

## Demos

**Calendar**
- [x] Android: `AndroidCalendarSample`
    - [x] or run in terminal: `./gradlew :deps:libs:calendar:compose-multiplatform:sample:installDebug && adb shell monkey -p com.kizitonwose.calendar.compose.multiplatform.sample -c android.intent.category.LAUNCHER 1`
- [ ] iOS: Select `calendar`
    - [ ] `BUG:` Not installing
- [x] Desktop: `./gradlew :deps:libs:calendar:compose-multiplatform:sample:run`
- [x] Web: `./gradlew :deps:libs:calendar:compose-multiplatform:sample:wasmJsBrowserDevelopmentRun --info`
    - [ ] `PREREQUISITE:` Keep `org.gradle.configuration-cache=false` in `gradle.properties`

**RickTextEditor**
- [x] Android: `AndroidRichTextEditorSample`
    - [x] or run in terminal: `./gradlew :deps:libs:rich_text_editor:sample:android:installDebug && adb shell monkey -p com.mohamedrejeb.richeditor -c android.intent.category.LAUNCHER 1`
- [ ] iOS: `richTextEditor`
    - [ ] `BUG:` Not installing
- [x] Desktop: `./gradlew :deps:libs:rich_text_editor:sample:desktop:run`
- [x] Web: `./gradlew :deps:libs:rich_text_editor:sample:web:wasmJsBrowserDevelopmentRun --info`
    - [ ] `BUG:` Not covering entire screen, Viewport kept on expanding as window size changes