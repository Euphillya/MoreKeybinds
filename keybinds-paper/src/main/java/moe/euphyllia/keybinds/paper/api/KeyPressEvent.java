package moe.euphyllia.keybinds.paper.api;

import org.bukkit.entity.Player;
import org.bukkit.event.HandlerList;
import org.bukkit.event.player.PlayerEvent;
import org.jspecify.annotations.NonNull;

public class KeyPressEvent extends PlayerEvent {

    private static final HandlerList handlers = new HandlerList();
    private final String key;

    public KeyPressEvent(Player player, String key) {
        super(player);
        this.key = key;
    }

    /**
     * @return L'ID de la touche pressée
     */
    public String getKey() {
        return key;
    }

    @Override
    public @NonNull HandlerList getHandlers() {
        return handlers;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }
}
