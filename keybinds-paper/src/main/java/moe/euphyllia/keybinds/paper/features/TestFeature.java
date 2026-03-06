package moe.euphyllia.keybinds.paper.features;

import moe.euphyllia.keybinds.api.KeyPressEvent;
import moe.euphyllia.keybinds.api.PlayerKeybindModReadyEvent;
import moe.euphyllia.keybinds.api.features.BuiltinFeature;
import moe.euphyllia.keybinds.paper.api.KeybindAPI;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.event.EventHandler;
import org.lwjgl.glfw.GLFW;

public class TestFeature implements BuiltinFeature {

    @Override
    public String getConfigKey() {
        return "test";
    }

    @Override
    public String getKeybindId() {
        return "test";
    }

    @Override
    public int getDefaultKey() {
        return GLFW.GLFW_KEY_G; // 71
    }

    @EventHandler
    public void onModReady(final PlayerKeybindModReadyEvent event) {
        KeybindAPI.get().register(event.getPlayer(), getKeybindId(), getDefaultKey());
    }

    @EventHandler
    public void onKeyPress(final KeyPressEvent event) {
        if (!event.getKeybindId().equals(getKeybindId())) return;
        event.getPlayer().sendMessage(MiniMessage.miniMessage().deserialize("The 'test' button has been pressed"));
    }
}
