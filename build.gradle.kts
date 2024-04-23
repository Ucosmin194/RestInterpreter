import org.jetbrains.compose.desktop.application.dsl.TargetFormat

plugins {
    kotlin("multiplatform")
    id("org.jetbrains.compose")
}

group = "com.postman"
version = "1.0-SNAPSHOT"

repositories {
    google()
    mavenCentral()
    maven { url = uri("https://jitpack.io") }
    maven { url = uri("https://maven.pkg.jetbrains.space/public/p/compose/dev") }
}

kotlin {
    jvm {
        jvmToolchain(19)
        withJava()
    }
    sourceSets {
        val uiTooling = "1.6.0"
        val sqliteVersion = "3.36.0.1"
        val roomVersion = "2.6.1"
        val jvmMain by getting {
            dependencies {
                implementation(compose.desktop.currentOs)
                implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.7.0")
                implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.7.3")
                implementation("io.ktor:ktor-client-core:1.6.4")
                implementation("io.ktor:ktor-client-apache:1.6.4")
                implementation("io.ktor:ktor-client-json:1.6.4")
                implementation("io.ktor:ktor-client-gson:1.6.4")
                implementation("org.xerial:sqlite-jdbc:$sqliteVersion")
                implementation("androidx.compose.ui:ui-tooling:$uiTooling")
                implementation("com.github.BazaiHassan:AwesomeToastLibrary:1.1")
                implementation("androidx.room:room-runtime:$roomVersion")
//                kapt("androidx.room:room-compiler:$roomVersion")

                // Optional - Kotlin Extensions and Coroutines support for Room
                implementation("androidx.room:room-ktx:$roomVersion")

            }
        }
        val jvmTest by getting {
            dependencies{
                implementation("androidx.room:room-testing:$roomVersion")
            }
        }
    }
}

compose.desktop {
    application {
        mainClass = "MainKt"
        nativeDistributions {
            targetFormats(TargetFormat.Dmg, TargetFormat.Msi, TargetFormat.Deb)
            packageName = "dbpostman"
            packageVersion = "1.0.0"
                }
        }
}