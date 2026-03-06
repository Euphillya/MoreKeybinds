package moe.euphyllia.keybinds.api;

import org.bukkit.entity.Player;
import org.bukkit.event.HandlerList;
import org.bukkit.event.player.PlayerEvent;
import org.jetbrains.annotations.NotNull;

public class PlayerKeybindModReadyEvent extends PlayerEvent {

    private static final HandlerList handlers = new HandlerList();
    private final String modName;
    private final int version;

    /**
     * @param player  the player who sent the handshake
     * @param modName the mod identifier reported by the client
     * @param version the mod version reported by the client
     */
    public PlayerKeybindModReadyEvent(Player player, String modName, int version) {
        super(player);
        this.modName = modName;
        this.version = version;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }

    /**
     * Returns the mod identifier reported by the client during the handshake.
     *
     * @return the mod name (e.g. {@code "morekeyBinds"})
     */
    public String getModName() {
        return modName;
    }

    /**
     * Returns the mod version reported by the client during the handshake.
     *
     * @return the mod version (e.g. {@code 2})
     */
    public int getVersion() {
        return version;
    }

    @Override
    public @NotNull HandlerList getHandlers() {
        return handlers;
    }
}
