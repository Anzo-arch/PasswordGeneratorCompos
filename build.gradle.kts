plugins {
    kotlin("jvm") version "1.8.22"
    id("org.jetbrains.compose") version "1.5.10"
    application
}

repositories {
    google()
    mavenCentral()
    maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
}

dependencies {
    implementation(compose.desktop.currentOs)
}

application {
    mainClass.set("MainKt")
}

kotlin {
    jvmToolchain(17)
}
