package com.fish.lucidremedy.block.custom;


import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.IntegerProperty;

public class EngravingBlock extends Block {
    public static final IntegerProperty CROSS_DOWN = IntegerProperty.create("cross_d", 0, 16);
    public static final IntegerProperty CROSS_UP = IntegerProperty.create("cross_u", 0, 16);
    public static final IntegerProperty CROSS_NORTH = IntegerProperty.create("cross_n", 0, 16);
    public static final IntegerProperty CROSS_SOUTH = IntegerProperty.create("cross_s", 0, 16);
    public static final IntegerProperty CROSS_EAST = IntegerProperty.create("cross_e", 0, 16);
    public static final IntegerProperty CROSS_WEST = IntegerProperty.create("cross_w", 0, 16);

    public EngravingBlock(Properties properties) {
        super(properties);
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(CROSS_DOWN);
        builder.add(CROSS_UP);
        builder.add(CROSS_NORTH);
        builder.add(CROSS_SOUTH);
        builder.add(CROSS_EAST);
        builder.add(CROSS_WEST);
    }
}
