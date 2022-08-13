import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.7.10"
    kotlin("kapt")  version "1.7.10"
    `maven-publish`
}

group = "top.ntutn.starsea"
version = "1.2.0"

repositories {
    mavenCentral()
}

dependencies {
    val autoService = "1.0-rc7"
    compileOnly("com.google.auto.service", "auto-service-annotations", autoService)
    kapt("com.google.auto.service", "auto-service", autoService)
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
