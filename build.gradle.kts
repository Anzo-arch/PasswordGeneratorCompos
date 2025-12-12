plugins {
    kotlin("jvm") version "1.9.0"
    id("org.jetbrains.compose") version "1.5.1"
}

repositories {
    google()
    mavenCentral()
}

dependencies {
    implementation(compose.desktop.currentOs)
}

compose.desktop {
    application {
        mainClass = "MainKt"
        nativeDistributions {
            targetFormats(org.jetbrains.compose.desktop.application.dsl.TargetFormat.Dmg,
                          org.jetbrains.compose.desktop.application.dsl.TargetFormat.Msi,
                          org.jetbrains.compose.desktop.application.dsl.TargetFormat.Deb)
            packageName = "PasswordGenerator"
            packageVersion = "1.0.0"
        }
    }
}
