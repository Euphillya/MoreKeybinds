package moe.euphyllia.keybinds.example;

import moe.euphyllia.keybinds.api.KeyPressEvent;
import moe.euphyllia.keybinds.api.PlayerKeybindModReadyEvent;
import moe.euphyllia.keybinds.paper.api.KeybindAPI;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;
import org.lwjgl.glfw.GLFW;

public class ExamplePlugin extends JavaPlugin implements Listener {

    private static final String DASH = "example:dash";
    private static final String SHIELD = "example:shield";

    @Override
    public void onEnable() {
        getServer().getPluginManager().registerEvents(this, this);
    }

    @EventHandler
    public void onModReady(final PlayerKeybindModReadyEvent event) {
        // Register keybinds once the mod is confirmed on the client
        KeybindAPI.get().register(event.getPlayer(), DASH, GLFW.GLFW_KEY_R);
        KeybindAPI.get().register(event.getPlayer(), SHIELD, GLFW.GLFW_KEY_F);
    }

    @EventHandler
    public void onKeyPress(KeyPressEvent event) {
        if (event.is(DASH)) {
            event.getPlayer().sendMessage("§bDash!");
        } else if (event.is(SHIELD)) {
            event.getPlayer().sendMessage("§aShield raised!");
        }
    }
}
