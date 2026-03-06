package moe.euphyllia.keybinds.fabric;

import com.mojang.blaze3d.platform.InputConstants;
import moe.euphyllia.keybinds.fabric.network.KeyPressPayload;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.minecraft.client.KeyMapping;
import net.minecraft.resources.Identifier;
import org.lwjgl.glfw.GLFW;

public class KeyInputHandler {

    private static KeyMapping testKey;

    public static void init() {

        KeyMapping.Category category = KeyMapping.Category.register(Identifier.fromNamespaceAndPath("server", "play.euphyllia.moe"));
        testKey = KeyBindingHelper.registerKeyBinding(
                new KeyMapping(
                        "Button Test",
                        InputConstants.Type.KEYSYM,
                        GLFW.GLFW_KEY_G,
                        category
                )
        );

        ClientTickEvents.END_CLIENT_TICK.register(client -> {

            while (testKey.consumeClick()) {

                ClientPlayNetworking.send(
                        new KeyPressPayload("test")
                );

            }

        });

    }
}