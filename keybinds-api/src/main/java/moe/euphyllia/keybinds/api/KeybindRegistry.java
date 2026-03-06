package moe.euphyllia.keybinds.api;

import org.bukkit.entity.Player;

public interface KeybindRegistry {

    void register(Player player, String id, int defaultKey);
}
