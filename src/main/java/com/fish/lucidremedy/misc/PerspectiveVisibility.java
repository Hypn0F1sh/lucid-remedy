package com.fish.lucidremedy.misc;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;

import java.util.ArrayList;
import java.util.List;

public class PerspectiveVisibility {

    public static boolean isVisible(Entity viewer, Entity target, Vec3 simulatedPos, double maxAngleDeg) {
        Level level = viewer.level();
        Vec3 eyePos = viewer.getEyePosition(1.0F);
        Vec3 lookVec = viewer.getLookAngle().normalize();

        AABB targetBox = target.getBoundingBox();
        if (simulatedPos != null) {

            Vec3 offset = simulatedPos.subtract(target.position());
            targetBox = targetBox.move(offset);
        }

        List<Vec3> samplePoints = getBoxSamplePoints(targetBox);

        for (Vec3 point : samplePoints) {

            Vec3 toPoint = point.subtract(eyePos).normalize();

            double dotProduct = lookVec.dot(toPoint);
            double angleDeg = Math.toDegrees(Math.acos(Math.clamp(dotProduct, -1, 1)));

            if (angleDeg < maxAngleDeg) {
                BlockHitResult raycast = level.clip(new ClipContext(
                        eyePos,
                        point,
                        ClipContext.Block.VISUAL,
                        ClipContext.Fluid.NONE,
                        viewer
                ));

                if (raycast.getType() == HitResult.Type.MISS) {
                    return true;
                }
            }
        }
        return false;
    }

    private static List<Vec3> getBoxSamplePoints(AABB box) {
        List<Vec3> points = new ArrayList<>(9);
        points.add(box.getCenter());
        points.add(new Vec3(box.minX, box.minY, box.minZ));
        points.add(new Vec3(box.maxX, box.minY, box.minZ));
        points.add(new Vec3(box.minX, box.maxY, box.minZ));
        points.add(new Vec3(box.minX, box.minY, box.maxZ));
        points.add(new Vec3(box.maxX, box.maxY, box.minZ));
        points.add(new Vec3(box.maxX, box.minY, box.maxZ));
        points.add(new Vec3(box.minX, box.maxY, box.maxZ));
        points.add(new Vec3(box.maxX, box.maxY, box.maxZ));
        return points;
    }
}