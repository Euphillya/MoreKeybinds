rootProject.name = "KeyBinds"

pluginManagement {

    repositories {
        gradlePluginPortal()
        maven("https://maven.fabricmc.net/")
        maven("https://repo.papermc.io/repository/maven-public/")
    }

}

include("keybinds-api")
include("keybinds-fabric")
include("keybinds-paper")