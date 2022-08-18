import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.7.10"
    kotlin("kapt")  version "1.7.10"
    id("java-gradle-plugin")
    id("com.github.gmazzo.buildconfig") version "3.1.0"
    `maven-publish`
}

buildscript {
    repositories {
        google()
        mavenCentral()
    }
    dependencies {
        // 因为使用的 Kotlin 需要需要添加 Kotlin 插件
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.6.10")
    }
}

gradlePlugin {
    plugins {
        register("aliasPlugin") {
            id = "top.ntutn.starsea.alias"
            implementationClass = "top.ntutn.starseasdk.proxy.StarseaPlugin"
        }
    }
}

group = "top.ntutn.starsea"
version = "1.2.6"

buildConfig {
    buildConfigField("String", "version", "\"$version\"")
}

repositories {
    mavenCentral()
    maven(url = "https://jitpack.io")
}

dependencies {
    val autoService = "1.0-rc7"
    compileOnly("com.google.auto.service", "auto-service-annotations", autoService)
    kapt("com.google.auto.service", "auto-service", autoService)

    kapt("com.github.rfkhx:starseasdk:1.2.5")

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
