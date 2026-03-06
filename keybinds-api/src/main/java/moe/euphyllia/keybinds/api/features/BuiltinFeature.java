package moe.euphyllia.keybinds.api.features;

import org.bukkit.event.Listener;

public interface BuiltinFeature extends Listener {

    /**
     * The config key used to enable/disable this feature (e.g. {@code "test"}).
     * Must match the key in {@code config.yml}.
     *
     * @return the config key for this feature
     */
    String getConfigKey();

    /**
     * The keybind ID sent to the client (e.g. {@code "test"}).
     *
     * @return the keybind ID
     */
    String getKeybindId();

    /**
     * The default GLFW key code for this feature (e.g. {@code 71} for G).
     *
     * @return the default key code
     */
    int getDefaultKey();
}
