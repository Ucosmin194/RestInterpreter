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
    maven("https://jitpack.io")
    maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
}

kotlin {
    jvm {
        jvmToolchain(11)
        withJava()
    }
    sourceSets {
        val uiTooling = "1.4.3"
        val sqliteVersion = "3.36.0.1"
        val jvmMain by getting {
            dependencies {
                implementation("io.ktor:ktor-client-core:1.6.4")
                implementation("io.ktor:ktor-client-apache:1.6.4")
                implementation("io.ktor:ktor-client-json:1.6.4")
                implementation("io.ktor:ktor-client-gson:1.6.4")
                implementation("org.xerial:sqlite-jdbc:$sqliteVersion")
                implementation ("androidx.compose.ui:ui-tooling: SuiTooling")
                implementation("com.github.BazaiHassan: Awesome ToastLibrary: 1.1")
            }
        }
        val jvmTest by getting
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