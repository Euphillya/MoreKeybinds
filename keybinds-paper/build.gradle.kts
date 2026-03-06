plugins {
    java
    id("io.papermc.paperweight.userdev")
}

paperweight {
    paperweight.reobfArtifactConfiguration = io.papermc.paperweight.userdev.ReobfArtifactConfiguration.MOJANG_PRODUCTION
}

dependencies {

    paperweight.foliaDevBundle("1.21.11-R0.1-SNAPSHOT")

}

tasks {
    assemble {
        dependsOn(reobfJar)
    }
    compileJava {
        options.encoding = "UTF-8"
    }
}