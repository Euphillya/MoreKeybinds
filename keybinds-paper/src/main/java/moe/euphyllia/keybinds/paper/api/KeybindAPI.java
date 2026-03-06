package moe.euphyllia.keybinds.paper.api;

import io.netty.buffer.Unpooled;
import moe.euphyllia.keybinds.api.KeybindRegistry;
import net.minecraft.network.FriendlyByteBuf;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class KeybindAPI implements KeybindRegistry {

    public static final String HELLO = "keybinds:hello";
    public static final String PRESS = "keybinds:press";
    public static final String REGISTER = "keybinds:register";
    private static final Logger log = LoggerFactory.getLogger(KeybindAPI.class);
    private static KeybindAPI instance;

    private final Plugin plugin;

    private KeybindAPI(Plugin plugin) {
        this.plugin = plugin;
    }

    public static void init(Plugin plugin) {
        instance = new KeybindAPI(plugin);
    }

    public static void shutdown() {
        instance = null;
    }

    public static KeybindAPI get() {
        if (instance == null) {
            throw new IllegalStateException(
                    "KeybindAPI is not initialized. Make sure MoreKeybinds is loaded before your plugin."
            );
        }
        return instance;
    }

    public static boolean isAvailable() {
        return instance != null;
    }

    @Override
    public void register(Player player, String id, int defaultKey) {
        if (!player.isOnline()) {
            log.warn("Tried to register keybind '{}' for offline player '{}'.", id, player.getName());
            return;
        }

        FriendlyByteBuf buf = new FriendlyByteBuf(Unpooled.buffer());
        buf.writeUtf(id);
        buf.writeInt(defaultKey);

        byte[] data = new byte[buf.readableBytes()];
        buf.readBytes(data);

        try {
            player.sendPluginMessage(plugin, REGISTER, data);
        } catch (Exception e) {
        }
    }

}
