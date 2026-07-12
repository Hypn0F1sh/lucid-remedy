package com.fish.lucidremedy.events.moveLerpEvent;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Pose;
import net.minecraft.world.phys.Vec3;

public class MoveEntityLerp {

    public static boolean Move(Entity entity, Vec3 start, Vec3 end, int duration, float elapsed) {
        boolean result = true;
        float fraction = elapsed / (float) duration;
        Vec3 difference = end.subtract(start);
        Vec3 position = start.add(difference.scale(fraction));
        entity.setPos(position.x, position.y, position.z);
        entity.setDeltaMovement(0,0,0);
        entity.setPose(Pose.STANDING);
        entity.invulnerableTime = 20;
        if (elapsed >= duration) {
            result = false;
            entity.setOnGround(true);
            entity.setDeltaMovement(0,0,0);
        }
        return result;
    }
}
