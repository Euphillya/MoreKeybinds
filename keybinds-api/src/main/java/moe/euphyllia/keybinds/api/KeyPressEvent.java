package moe.euphyllia.keybinds.api;

import org.bukkit.entity.Player;
import org.bukkit.event.HandlerList;
import org.bukkit.event.player.PlayerEvent;
import org.jetbrains.annotations.NotNull;

public class KeyPressEvent extends PlayerEvent {

    private static final HandlerList handlers = new HandlerList();
    private final String keybindId;

    public KeyPressEvent(Player player, String keybindId) {
        super(player);
        this.keybindId = keybindId;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }

    public String getKeybindId() {
        return keybindId;
    }

    public boolean is(KeybindDefinition definition) {
        return definition != null && definition.getId().equals(keybindId);
    }

    public boolean is(String id) {
        return keybindId.equals(id);
    }

    @Override
    public @NotNull HandlerList getHandlers() {
        return handlers;
    }

}
