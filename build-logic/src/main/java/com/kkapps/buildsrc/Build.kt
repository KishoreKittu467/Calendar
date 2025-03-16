@file:Suppress("unused", "ConstPropertyName")

package com.kkapps.buildsrc

import org.gradle.api.JavaVersion
import org.gradle.jvm.toolchain.JavaLanguageVersion

object Config {
    val compatibleJavaVersion = JavaVersion.VERSION_21
    val compatibleJavaLanguageVersion = JavaLanguageVersion.of(compatibleJavaVersion.majorVersion.toInt())
}

object Android {
    const val minSdk = 24
    const val targetSdk = 35
    const val compileSdk = 35
}