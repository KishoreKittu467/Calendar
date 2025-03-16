plugins {
    alias(libs.plugins.kotlinJvm)
}

dependencies {
    implementation(project(":deps:libs:calendar:core"))

    testImplementation(libs.test.junit5.api)
    testRuntimeOnly(libs.test.junit5.engine)
}
