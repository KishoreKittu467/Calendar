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

Running the app on different platforms:

- Android : Select `AndroidApp` configuration in android studio
  - run `./gradlew :composeApp:assembleDebug` in Terminal
- iOS: Select `iosApp` configuration in android studio
  - Run `./gradlew :composeApp:embedAndSignAppleFrameworkForXcode`
- Desktop: run `./gradlew :composeApp:run` in Terminal
  - Web: run `./gradlew :composeApp:wasmJsBrowserDevelopmentRun`
  - `./gradlew :composeApp:wasmJsBrowserRun` in Terminal
- Server: run `./gradlew :server:run` in Terminal

Demos
- Android : Select `calendarAndroid` configuration in android studio
  - run `./gradlew :deps:libs:kk_calendar:compose-multiplatform:sample:assembleDebug` in Terminal
- iOS: Select `calendarIos` configuration in android studio
- Desktop: run `./gradlew :deps:libs:kk_calendar:compose-multiplatform:sample:run` in Terminal
- Web: run `./gradlew :deps:libs:kk_calendar:compose-multiplatform:sample:wasmJsBrowserDevelopmentRun`
  - run `./gradlew :deps:libs:kk_calendar:compose-multiplatform:sample:wasmJsBrowserRun` in Terminal
