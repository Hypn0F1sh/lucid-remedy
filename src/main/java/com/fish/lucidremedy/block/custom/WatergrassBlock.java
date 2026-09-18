package com.fish.lucidremedy.block.custom;

import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.Registries;
import net.minecraft.references.BlockIds;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.lighting.LightEngine;

import java.util.Optional;

public class WatergrassBlock extends SpreadingSnowyBlock {

    public static final MapCodec<WatergrassBlock> CODEC = simpleCodec(WatergrassBlock::new);

    public MapCodec<WatergrassBlock> codec() {
        return CODEC;
    }

    public WatergrassBlock(Properties properties) {
        super(properties, BlockIds.DIRT);
    }

    private static boolean canStayAlive(BlockState state, LevelReader level, BlockPos pos) {
        BlockPos above = pos.above();
        BlockState aboveState = level.getBlockState(above);
        if (!aboveState.getFluidState().is(FluidTags.WATER)) {
            return false;
        } else {
            int lightBlockInto = LightEngine.getLightBlockInto(state, aboveState, Direction.UP, aboveState.getLightDampening());
            return lightBlockInto < 15;
        }
    }

    private static boolean canPropagate(BlockState state, LevelReader level, BlockPos pos) {
        BlockPos above = pos.above();
        return canStayAlive(state, level, pos) && level.getFluidState(above).is(FluidTags.WATER);
    }

    @Override
    protected void randomTick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {
        Registry<Block> blocks = level.registryAccess().lookupOrThrow(Registries.BLOCK);
        Optional<Block> baseBlock = blocks.getOptional(BlockIds.DIRT);
        if (!baseBlock.isEmpty()) {
            if (!canStayAlive(state, level, pos)) {
                if (!level.isAreaLoaded(pos, 1)) {
                    return;
                }

                level.setBlockAndUpdate(pos, ((Block)baseBlock.get()).defaultBlockState());
            } else {
                if (!level.isAreaLoaded(pos, 3)) {
                    return;
                }

                if (level.getMaxLocalRawBrightness(pos.above()) >= 9) {
                    BlockState defaultBlockState = this.defaultBlockState();

                    for(int i = 0; i < 4; ++i) {
                        BlockPos testPos = pos.offset(random.nextInt(3) - 1, random.nextInt(5) - 3, random.nextInt(3) - 1);
                        if (level.getBlockState(testPos).is((Block)baseBlock.get()) && canPropagate(defaultBlockState, level, testPos)) {
                            level.setBlockAndUpdate(testPos, (BlockState)defaultBlockState.setValue(SNOWY, isSnowySetting(level.getBlockState(testPos.above()))));
                        }
                    }
                }
            }
        }

    }
}
