package moe.euphyllia.keybinds.fabric;

import moe.euphyllia.keybinds.fabric.network.HelloPayload;
import moe.euphyllia.keybinds.fabric.network.KeyPressPayload;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayConnectionEvents;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.PayloadTypeRegistry;

public class KeyBindsFabricClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {

        PayloadTypeRegistry.playC2S().register(
                HelloPayload.TYPE,
                HelloPayload.CODEC
        );

        PayloadTypeRegistry.playC2S().register(
                KeyPressPayload.TYPE,
                KeyPressPayload.CODEC
        );

        KeyInputHandler.init();

        ClientPlayConnectionEvents.JOIN.register((handler, sender, client) -> {

            ClientPlayNetworking.send(
                    new HelloPayload("keybinds", 1)
            );

        });

    }
}