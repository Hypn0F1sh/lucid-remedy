package com.fish.lucidremedy.item.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.phys.Vec3;

public class ChiselItem extends Item {
    public ChiselItem(Properties properties) {
        super(properties);
    }

    public Vec3 getFaceDetail(Direction.Axis axis, Vec3 offset) {
        if (axis == Direction.Axis.Y) {
            offset = new Vec3(offset.x, 0, offset.z);
            if (Math.abs(offset.x) < 0.15 && Math.abs(offset.z) < 0.15) {
                return new Vec3(0, 0, 0);
            }
        }
        else if (axis == Direction.Axis.Z) {
            offset = new Vec3(offset.x, offset.y, 0);
            if (Math.abs(offset.x) < 0.15 && Math.abs(offset.y) < 0.15) {
                return new Vec3(0, 0, 0);
            }
        }
        else if (axis == Direction.Axis.X) {
            offset = new Vec3(0, offset.y, offset.z);
            if (Math.abs(offset.y) < 0.15 && Math.abs(offset.z) < 0.15) {
                return new Vec3(0, 0, 0);
            }
        }
        float ax = Math.abs((float)offset.x);
        float ay = Math.abs((float)offset.y);
        float az = Math.abs((float)offset.z);

        if (ax > ay) {
            if (ax > az) {
                if (offset.x > 0) { return new Vec3(1, 0, 0);} else { return new Vec3(-1, 0, 0);}
            } else if (offset.z > 0) { return new Vec3(0, 0, 1);} else { return new Vec3(0, 0, -1);}
        } else if (ay > az) {
            if (offset.y > 0) { return new Vec3(0, 1, 0);} else { return new Vec3(0, -1, 0);}
        } else  if (offset.z > 0) { return new Vec3(0, 0, 1);} else { return new Vec3(0, 0, -1);}
    }

    @Override
    public InteractionResult useOn(UseOnContext context) {
        BlockPos blockPos = context.getClickedPos();
        Direction direction = context.getClickedFace();
        Vec3 location = context.getClickLocation();
        Vec3 offset = location.add(blockPos.getCenter().multiply(new Vec3(-1, -1, -1)));

        System.out.println(blockPos);
        System.out.println(direction);
        System.out.println(location);
        System.out.println(offset);
        System.out.println(getFaceDetail(direction.getAxis(), offset));

        return InteractionResult.SUCCESS;
    }
}
