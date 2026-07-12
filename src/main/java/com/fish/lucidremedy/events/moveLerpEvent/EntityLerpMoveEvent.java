package com.fish.lucidremedy.events.moveLerpEvent;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.phys.Vec3;
import net.neoforged.neoforge.event.entity.EntityEvent;

public class EntityLerpMoveEvent extends EntityEvent {

    public static Vec3 startPos;
    public static Vec3 endPos;
    public static int moveDuration;

    public EntityLerpMoveEvent(Entity entity, Vec3 start, Vec3 end, int duration) {
        super(entity);
        startPos = start;
        endPos = end;
        moveDuration = duration;
    }

    public static Vec3 getStart() {
        return startPos;
    }

    public static Vec3 getEnd() {
        return endPos;
    }

    public static int getDuration() {
        return moveDuration;
    }
}
