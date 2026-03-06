plugins {
    id("java")
    id("io.papermc.paperweight.userdev") version "2.0.0-beta.19" apply false
}

allprojects {
    group = "moe.euphyllia"
    version = "1.0-SNAPSHOT"

    repositories {
        mavenCentral()
        maven("https://maven.fabricmc.net/")
        maven("https://repo.papermc.io/repository/maven-public/")
    }
}


subprojects {
    apply(plugin = "java")

    java {
        toolchain {
            languageVersion.set(JavaLanguageVersion.of(21))
        }
    }
}

tasks.test {
    useJUnitPlatform()
}