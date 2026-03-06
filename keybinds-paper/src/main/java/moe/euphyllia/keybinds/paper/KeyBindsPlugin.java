package moe.euphyllia.keybinds.paper;

import io.netty.buffer.Unpooled;
import moe.euphyllia.keybinds.api.KeyPressEvent;
import moe.euphyllia.keybinds.api.PlayerKeybindModReadyEvent;
import moe.euphyllia.keybinds.paper.api.KeybindAPI;
import moe.euphyllia.keybinds.paper.features.BuiltinFeature;
import moe.euphyllia.keybinds.paper.features.TestFeature;
import net.minecraft.network.FriendlyByteBuf;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.plugin.messaging.PluginMessageListener;
import org.jspecify.annotations.NonNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class KeyBindsPlugin extends JavaPlugin implements PluginMessageListener, Listener {

    private static final Logger log = LoggerFactory.getLogger(KeyBindsPlugin.class);

    private static final List<BuiltinFeature> FEATURES = List.of(
            new TestFeature()
    );

    @Override
    public void onEnable() {

        saveDefaultConfig();

        KeybindAPI.init(this);

        getServer().getMessenger().registerIncomingPluginChannel(this, KeybindAPI.HELLO, this);
        getServer().getMessenger().registerIncomingPluginChannel(this, KeybindAPI.PRESS, this);
        getServer().getMessenger().registerOutgoingPluginChannel(this, KeybindAPI.REGISTER);

        for (BuiltinFeature feature : FEATURES) {
            if (getConfig().getBoolean(feature.getConfigKey() + ".enable", false)) {
                getServer().getPluginManager().registerEvents(feature, this);
                log.info("Enabled built-in feature '{}'", feature.getConfigKey());
            }
        }
    }

    @Override
    public void onPluginMessageReceived(String channel, @NonNull Player player, byte @NonNull [] message) {
        FriendlyByteBuf buf = new FriendlyByteBuf(Unpooled.wrappedBuffer(message));

        if (channel.equals(KeybindAPI.HELLO)) {
            String mod = buf.readUtf();
            int version = buf.readInt();
            new PlayerKeybindModReadyEvent(player, mod, version).callEvent();

        } else if (channel.equals(KeybindAPI.PRESS)) {
            String key = buf.readUtf();
            new KeyPressEvent(player, key).callEvent();
        }
    }

    @Override
    public void onDisable() {
        KeybindAPI.shutdown();
    }
}
