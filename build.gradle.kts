import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.7.10"
    `maven-publish`
}

group = "top.ntutn.starsea"
version = "1.1.2"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(kotlin("test"))
}

tasks.test {
    useJUnitPlatform()
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "11"
}

publishing {
    publications {
        create<MavenPublication>("maven") {
            groupId = "top.ntutn.starsea"
            artifactId = "sdk"
            version = project.version as String

            from(components["kotlin"])
        }
    }
}
