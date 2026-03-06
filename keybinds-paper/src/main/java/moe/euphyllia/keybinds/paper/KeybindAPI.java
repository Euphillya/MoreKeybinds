package moe.euphyllia.keybinds.paper;

import io.netty.buffer.Unpooled;
import net.minecraft.network.FriendlyByteBuf;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class KeybindAPI {

    public static final String REGISTER = "keybinds:register";
    private static final Logger LOGGER = LoggerFactory.getLogger(KeybindAPI.class);

    private static Plugin plugin;

    public static void init(Plugin pluginInstance) {
        plugin = pluginInstance;
        LOGGER.info("KeybindAPI initialisée");
    }

    /**
     * Enregistre une touche dynamique pour un joueur
     * @param player Le joueur
     * @param id L'ID de la touche
     * @param defaultKey Le code de la touche par défaut (ex: 71 pour G)
     */
    public static void register(Player player, String id, int defaultKey) {

        if (plugin == null) {
            throw new IllegalStateException("KeybindAPI not initialized");
        }

        if (!player.isOnline()) {
            LOGGER.warn("Tentative d'enregistrer une touche pour un joueur offline: {}", player.getName());
            return;
        }

        FriendlyByteBuf buf = new FriendlyByteBuf(Unpooled.buffer());

        buf.writeUtf(id);
        buf.writeInt(defaultKey);

        byte[] data = new byte[buf.readableBytes()];
        buf.readBytes(data);

        try {
            player.sendPluginMessage(plugin, REGISTER, data);
            LOGGER.info("Keybind '{}' envoyé à {}", id, player.getName());
        } catch (Exception e) {
            LOGGER.error("Erreur lors de l'envoi du keybind: {}", e.getMessage(), e);
        }
    }
}
