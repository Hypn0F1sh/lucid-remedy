package com.fish.lucidremedy.item.custom;

import com.fish.lucidremedy.block.ModBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;

public class ChalkItem extends Item {
    public ChalkItem(Properties properties) {
        super(properties);
    }

    @Override
    public InteractionResult useOn(UseOnContext context) {
        if (context.getClickedFace() == Direction.UP) {
            BlockPos pos = context.getClickedPos();
            Level level = context.getLevel();
            if (level.getBlockState(pos).isSolid()) {
                if (level.getBlockState(pos.above(1)).canBeReplaced()) {
                    level.setBlock(pos.above(1), ModBlocks.CHALK.get().defaultBlockState(), 1);
                    return InteractionResult.SUCCESS;
                }
            }
        }
        return InteractionResult.FAIL;
    }
}
