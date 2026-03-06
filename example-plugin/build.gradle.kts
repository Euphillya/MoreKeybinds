plugins {
    java
}

repositories {
    maven("https://repo.papermc.io/repository/maven-public/")
}

dependencies {
    compileOnly("io.papermc.paper:paper-api:1.21.11-R0.1-SNAPSHOT")
    compileOnly("org.lwjgl:lwjgl-glfw:3.4.1")
    compileOnly(project(":keybinds-api"))
    compileOnly(project(":keybinds-paper"))
}

tasks.compileJava {
    options.encoding = "UTF-8"
}
