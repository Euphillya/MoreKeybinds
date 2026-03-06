package moe.euphyllia.keybinds.paper;

import io.netty.buffer.Unpooled;
import moe.euphyllia.keybinds.paper.api.KeyPressEvent;
import net.minecraft.network.FriendlyByteBuf;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.plugin.messaging.PluginMessageListener;

public class KeyBindsPlugin extends JavaPlugin implements PluginMessageListener, Listener {

    public static final String HELLO = "keybinds:hello";
    public static final String PRESS = "keybinds:press";
    public static final String REGISTER = "keybinds:register";

    @Override
    public void onEnable() {

        KeybindAPI.init(this);

        // Enregistre les canaux entrants (client → serveur)
        getServer().getMessenger().registerIncomingPluginChannel(this, HELLO, this);
        getServer().getMessenger().registerIncomingPluginChannel(this, PRESS, this);

        // Enregistre le canal sortant (serveur → client)
        getServer().getMessenger().registerOutgoingPluginChannel(this, REGISTER);

        Bukkit.getPluginManager().registerEvents(this, this);
    }

    @EventHandler
    public void onJoin(final PlayerJoinEvent event) {
        Player player = event.getPlayer();

        KeybindAPI.register(player, "test", 71);
        player.sendMessage("§aKeybind 'test' enregistré sur la touche G");
    }

    @EventHandler
    public void onKeyPress(KeyPressEvent event) {
        if (event.getKey().equals("test")) {
            event.getPlayer().sendMessage("§bTu as appuyé sur G!");
        }
    }

    @Override
    public void onPluginMessageReceived(String channel, Player player, byte[] message) {

        FriendlyByteBuf buf = new FriendlyByteBuf(Unpooled.wrappedBuffer(message));

        if (channel.equals(HELLO)) {
            String mod = buf.readUtf();
            int version = buf.readInt();
            getLogger().info("✅ " + player.getName() + " s'est connecté avec le mod " + mod + " v" + version);
        }

        if (channel.equals(PRESS)) {
            String key = buf.readUtf();
            getLogger().warning(player.getName() + " a pressé: " + key);

            new KeyPressEvent(player, key).callEvent();
        }
    }

    @Override
    public void onDisable() {

    }
}
