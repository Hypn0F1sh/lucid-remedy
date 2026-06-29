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
            if (Math.abs(offset.x) > 0.3 && Math.abs(offset.z) > 0.3) {
                return new Vec3(0, 0, 0);
            }
            else if (Math.abs(offset.x) < 0.3 && Math.abs(offset.z) < 0.3) {
                return new Vec3(1, 1, 1);
            }
        }
        else if (axis == Direction.Axis.Z) {
            offset = new Vec3(offset.x, offset.y, 0);
            if (Math.abs(offset.x) > 0.3 && Math.abs(offset.y) > 0.3) {
                return new Vec3(0, 0, 0);
            }
            else if (Math.abs(offset.x) < 0.3 && Math.abs(offset.y) < 0.3) {
                return new Vec3(1, 1, 1);
            }
        }
        else if (axis == Direction.Axis.X) {
            offset = new Vec3(0, offset.y, offset.z);
            if (Math.abs(offset.y) > 0.3 && Math.abs(offset.z) > 0.3) {
                return new Vec3(0, 0, 0);
            }
            else if (Math.abs(offset.y) < 0.3 && Math.abs(offset.z) < 0.3) {
                return new Vec3(1, 1, 1);
            }
        }





        return new Vec3(0, 0, 0);
    }

    @Override
    public InteractionResult useOn(UseOnContext context) {
        BlockPos blockPos = context.getClickedPos();
        Direction direction = context.getClickedFace();
        Vec3 location = context.getClickLocation();

        System.out.println(blockPos);
        System.out.println(direction);
        System.out.println(location);

        return InteractionResult.SUCCESS;
    }
}
