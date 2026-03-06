plugins {
    java
    id("io.papermc.paperweight.userdev")
}

paperweight {
    paperweight.reobfArtifactConfiguration = io.papermc.paperweight.userdev.ReobfArtifactConfiguration.MOJANG_PRODUCTION
}

dependencies {
    paperweight.foliaDevBundle("1.21.11-R0.1-SNAPSHOT")
    implementation(project(":keybinds-api"))
    compileOnly("org.lwjgl:lwjgl-glfw:3.4.1")
}

tasks {
    assemble {
        dependsOn(reobfJar)
    }
    compileJava {
        options.encoding = "UTF-8"
    }
    jar {
        dependsOn(":keybinds-api:jar")
        from(configurations.runtimeClasspath.get().filter {
            it.name.contains("keybinds-api")
        }.map { zipTree(it) })
    }
}
