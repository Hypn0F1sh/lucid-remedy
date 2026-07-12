package com.fish.lucidremedy.events.moveLerpEvent;

import net.minecraft.world.phys.Vec3;

public class LerpMovementTask {

    public Vec3 start;
    public Vec3 end;
    public int duration;
    public float elapsed;

    public LerpMovementTask(Vec3 start, Vec3 end, int duration) {
        this.start = start;
        this.end = end;
        this.duration = duration;
        this.elapsed = 0;
    }
}
