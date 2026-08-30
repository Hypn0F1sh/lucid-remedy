package com.fish.lucidremedy.powers;

import com.fish.lucidremedy.misc.PerspectiveVisibility;
import com.fish.lucidremedy.payload.QuantumTunnelPayload;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;
import net.neoforged.neoforge.client.network.ClientPacketDistributor;

import java.util.List;
@SuppressWarnings("deprecation")
public class QuantumTunnel {

    static int range = 1024;
    static int maxAngleDeg = 70/2;

    public static void Tunnel(Entity entity, Level level) {
        BlockHitResult raycast = level.clip(new ClipContext(
                entity.getEyePosition(1.0F),
                entity.getEyePosition(1.0F).add(entity.getLookAngle().scale(range)),
                ClipContext.Block.OUTLINE,
                ClipContext.Fluid.NONE,
                entity
        ));

        if (raycast.getType() == HitResult.Type.BLOCK) {
            BlockPos hitPos = raycast.getBlockPos();
            Direction clickedFace = raycast.getDirection();
            BlockPos adjacentPos = hitPos.relative(clickedFace);
            Vec3 targetCoordinates = new Vec3(adjacentPos.getX() + 0.5, adjacentPos.getY(), adjacentPos.getZ() + 0.5);
            if (
                    level.getBlockState(adjacentPos.below(1)).blocksMotion() &&
                    !level.getBlockState(adjacentPos.above(1)).blocksMotion()
            ){
                ClientPacketDistributor.sendToServer(new QuantumTunnelPayload(targetCoordinates));
            }
        }
    }

    public static boolean CanTeleport(Entity target) {
        List<LivingEntity> entities = target.level().getEntitiesOfClass(LivingEntity.class, AABB.ofSize(target.position(), range*2, range*2, range*2));
        for (LivingEntity entity : entities) {
            if (entity != target) {
                if (PerspectiveVisibility.isVisible(entity, target, null, maxAngleDeg)) {
                    return false;
                }
            }
        }
        return true;
    }

    public static boolean CanTeleportTo(Entity target, Vec3 position) {
        List<LivingEntity> entities = target.level().getEntitiesOfClass(LivingEntity.class, AABB.ofSize(target.position(), range*2, range*2, range*2));
        for (LivingEntity entity : entities) {
            if (entity != target) {
                if (PerspectiveVisibility.isVisible(entity, target, position, maxAngleDeg)) {
                    return false;
                }
            }
        }
        return true;
    }
}
