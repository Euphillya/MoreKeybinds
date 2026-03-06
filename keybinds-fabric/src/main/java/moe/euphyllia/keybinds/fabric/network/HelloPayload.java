package moe.euphyllia.keybinds.fabric.network;

import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.Identifier;
import org.jspecify.annotations.NonNull;

public record HelloPayload(String mod, int version) implements CustomPacketPayload {

    public static final Identifier ID = Identifier.parse("keybinds:hello");
    public static final Type<HelloPayload> TYPE = new Type<>(ID);

    public static final StreamCodec<RegistryFriendlyByteBuf, HelloPayload> CODEC =
            StreamCodec.of(
                    (buf, payload) -> {
                        buf.writeUtf(payload.mod());
                        buf.writeInt(payload.version());
                    },
                    buf -> new HelloPayload(
                            buf.readUtf(),
                            buf.readInt()
                    )
            );

    @Override
    public @NonNull Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }
}