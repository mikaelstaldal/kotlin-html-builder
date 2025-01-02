group = "nu.staldal"
version = "0.2.0"

plugins {
    // Apply the org.jetbrains.kotlin.jvm Plugin to add support for Kotlin.
    alias(libs.plugins.kotlin.jvm)

    // Apply the java-library plugin for API and implementation separation.
    `java-library`

    `maven-publish`
}

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(21)
    }
}

repositories {
    mavenCentral()
}

dependencies {
    // Use JUnit Jupiter for testing.
    testImplementation(libs.junit.jupiter)
    testImplementation(libs.kotlin.test)

    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

tasks.test {
    useJUnitPlatform()
}

val sourcesJar by tasks.registering(Jar::class) {
    group = "build"
    description = "Assembles a jar archive containing the main source files."
    archiveClassifier.set("sources") // Sets the "-sources" suffix
    from(sourceSets["main"].allSource)
}

publishing {
    publications {
        create<MavenPublication>("mavenJava") {
            from(components["java"]) // Includes the main Java/Kotlin component
            artifact(sourcesJar) // Adds the source JAR
        }
    }
    repositories {
        mavenLocal() // Publish to the local Maven repository
    }
}
