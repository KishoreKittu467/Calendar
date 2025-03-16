plugins {
    alias(libs.plugins.kotlinJvm)
}

dependencies {
    compileOnly(libs.compose.runtime) // Only needed for @Immutable annotation.
}
