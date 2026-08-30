package com.fish.lucidremedy.payload;

import com.fish.lucidremedy.LucidRemedy;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.Identifier;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.phys.Vec3;

import java.util.logging.Level;

public record QuantumTunnelPayload(Vec3 targetPos) implements CustomPacketPayload {

    public static final Type<QuantumTunnelPayload> TYPE = new Type<>(
            Identifier.fromNamespaceAndPath(LucidRemedy.MODID, "teleport_packet")
    );

    public static final StreamCodec<FriendlyByteBuf, QuantumTunnelPayload> STREAM_CODEC = StreamCodec.of(
            (buffer, value) -> {
                buffer.writeDouble(value.targetPos().x);
                buffer.writeDouble(value.targetPos().y);
                buffer.writeDouble(value.targetPos().z);
            },
            buffer -> {
                double x = buffer.readDouble();
                double y = buffer.readDouble();
                double z = buffer.readDouble();
                return new QuantumTunnelPayload(new Vec3(x, y, z));
            }
    );

    @Override
    public Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }
}