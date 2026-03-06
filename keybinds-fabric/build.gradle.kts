plugins {
    id("net.fabricmc.fabric-loom-remap") version "1.14-SNAPSHOT"
}

dependencies {
    minecraft("com.mojang:minecraft:1.21.11")

    mappings(loom.officialMojangMappings())

    modImplementation("net.fabricmc:fabric-loader:0.18.2")

    modImplementation("net.fabricmc.fabric-api:fabric-api:0.139.4+1.21.11")
}