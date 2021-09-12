plugins {
    kotlin("jvm") version "1.5.30"
}

allprojects {
    repositories {
        mavenCentral()
    }
}

subprojects {
    apply(plugin="org.jetbrains.kotlin.jvm")

    dependencies {
        implementation(kotlin("stdlib"))
    }
}