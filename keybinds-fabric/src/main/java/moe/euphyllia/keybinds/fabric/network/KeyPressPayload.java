package moe.euphyllia.keybinds.fabric.network;

import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.Identifier;

public record KeyPressPayload(String key) implements CustomPacketPayload {

    public static final Identifier ID = Identifier.parse("keybinds:press");
    public static final Type<KeyPressPayload> TYPE = new Type<>(ID);

    public static final StreamCodec<RegistryFriendlyByteBuf, KeyPressPayload> CODEC =
            StreamCodec.of(
                    (buf, payload) -> buf.writeUtf(payload.key()),
                    buf -> new KeyPressPayload(buf.readUtf())
            );

    @Override
    public Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }
}