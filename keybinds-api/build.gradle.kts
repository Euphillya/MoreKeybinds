plugins {
    java
    `java-library`
    `maven-publish`
}

description = "MoreKeybinds API"

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(21))
    }
    withSourcesJar()
    withJavadocJar()
}

repositories {
    maven("https://repo.papermc.io/repository/maven-public/")
}

dependencies {
    // Paper API is only needed at compile time — it is provided by the server at runtime
    compileOnly("io.papermc.paper:paper-api:1.21.11-R0.1-SNAPSHOT")
}

tasks.compileJava {
    options.encoding = "UTF-8"
}

// Publish locally so keybinds-paper and other plugins can depend on it
publishing {
    publications {
        create<MavenPublication>("maven") {
            from(components["java"])
            groupId = "moe.euphyllia"
            artifactId = "keybinds-api"
            version = project.version.toString()
        }
    }
}
