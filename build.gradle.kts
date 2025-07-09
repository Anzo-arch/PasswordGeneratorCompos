plugins {
    kotlin("jvm") version "1.8.22"                      // Plugin Kotlin JVM
    id("org.jetbrains.compose") version "1.5.10"        // Plugin Compose para desktop
    application                                         // Plugin para empacotar app executável
}

repositories {
    google()                                            // Repositório Google (Android, Compose)
    mavenCentral()                                      // Maven Central para libs Kotlin
    maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")  // Repo Compose dev
}

dependencies {
    implementation(compose.desktop.currentOs)          // Dependência principal Compose Desktop
}

application {
    mainClass.set("MainKt")                             // Classe principal gerada pelo Kotlin (fun main)
}

kotlin {
    jvmToolchain(17)                                    // Usa JDK 17 para compilar
}
