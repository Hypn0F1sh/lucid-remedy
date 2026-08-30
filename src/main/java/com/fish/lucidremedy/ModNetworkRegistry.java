package com.fish.lucidremedy;

import com.fish.lucidremedy.payload.QuantumTunnelPayload;
import com.fish.lucidremedy.powers.QuantumTunnel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.phys.Vec3;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.network.event.RegisterPayloadHandlersEvent;
import net.neoforged.neoforge.network.registration.PayloadRegistrar;

@Mod(LucidRemedy.MODID)
@EventBusSubscriber
public class ModNetworkRegistry {

    @SubscribeEvent
    public static void registerPackets(RegisterPayloadHandlersEvent event) {
        PayloadRegistrar registrar = event.registrar(LucidRemedy.MODID);

        registrar.playToServer(
                QuantumTunnelPayload.TYPE,
                QuantumTunnelPayload.STREAM_CODEC,
                ModNetworkRegistry::handleTunnelOnServer
        );
    }

    private static void handleTunnelOnServer(QuantumTunnelPayload payload, net.neoforged.neoforge.network.handling.IPayloadContext context) {
        context.enqueueWork(() -> {
            if (context.player() instanceof ServerPlayer serverPlayer) {
                ServerLevel level = serverPlayer.level();
                double x = payload.targetPos().x;
                double y = payload.targetPos().y;
                double z = payload.targetPos().z;
                if (
                        QuantumTunnel.CanTeleport(serverPlayer) &&
                        QuantumTunnel.CanTeleportTo(serverPlayer, new Vec3(x, y, z))
                ) {
                    serverPlayer.teleportTo(x, y, z);
                }
            }
        });
    }
}