package com.fish.lucidremedy.block;

import com.fish.lucidremedy.LucidRemedy;
import com.fish.lucidremedy.attribute.ModAttributes;
import com.fish.lucidremedy.block.custom.*;
import com.fish.lucidremedy.item.ModItems;
import com.fish.lucidremedy.worldgen.tree.ModTreeGrowers;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.material.PushReaction;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Function;

public class ModBlocks {
    public static final DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks(LucidRemedy.MODID);

    //public static final DeferredBlock<Block> WATERGRASS_BLOCK = BLOCKS.registerBlock("watergrass_block",
    //        properties -> new WatergrassBlock(properties));

    public static final DeferredBlock<Block> PHASE_GNEISS = registerBlock("phase_gneiss",
            properties -> new AttributeDependentBlock(properties.sound(SoundType.STONE),
                    ModAttributes.HAS_PLANE_SHIFT));

    public static final DeferredBlock<Block> PHASE_GNEISS_BRICKS = registerBlock("phase_gneiss_bricks",
            properties -> new AttributeDependentBlock(properties.sound(SoundType.STONE),
                    ModAttributes.HAS_PLANE_SHIFT));

    public static final DeferredBlock<Block> POLISHED_PHASE_GNEISS = registerBlock("polished_phase_gneiss",
            properties -> new AttributeDependentBlock(properties.sound(SoundType.STONE),
                    ModAttributes.HAS_PLANE_SHIFT));

    public static final DeferredBlock<Block> GHOST_ASPEN_LOG = registerBlock("ghost_aspen_log",
            properties -> new AttributeDependentFlammableRotatedPillarBlock(properties.
                    sound(SoundType.CHERRY_WOOD).ignitedByLava().
                    strength(2f),
                    ModAttributes.HAS_PLANE_SHIFT));

    public static final DeferredBlock<Block> GHOST_ASPEN_WOOD = registerBlock("ghost_aspen_wood",
            properties -> new AttributeDependentFlammableRotatedPillarBlock(properties.
                    sound(SoundType.CHERRY_WOOD).ignitedByLava().
                    strength(2f),
                    ModAttributes.HAS_PLANE_SHIFT));

    public static final DeferredBlock<Block> STRIPPED_GHOST_ASPEN_LOG = registerBlock("stripped_ghost_aspen_log",
            properties -> new AttributeDependentFlammableRotatedPillarBlock(properties.
                    sound(SoundType.CHERRY_WOOD).ignitedByLava().
                    strength(2f),
                    ModAttributes.HAS_PLANE_SHIFT));

    public static final DeferredBlock<Block> STRIPPED_GHOST_ASPEN_WOOD = registerBlock("stripped_ghost_aspen_wood",
            properties -> new AttributeDependentFlammableRotatedPillarBlock(properties.
                    sound(SoundType.CHERRY_WOOD).ignitedByLava().
                    strength(2f),
                    ModAttributes.HAS_PLANE_SHIFT));

    public static final DeferredBlock<Block> GHOST_ASPEN_PLANKS = registerBlock("ghost_aspen_planks",
            properties -> new AttributeDependentBlock(properties.mapColor(MapColor.WOOD).instrument(NoteBlockInstrument.BASS)
                    .strength(2.0F, 3.0F).sound(SoundType.WOOD).ignitedByLava(),
                    ModAttributes.HAS_PLANE_SHIFT) {
                @Override
                public boolean isFlammable(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
                    return true;
                }

                @Override
                public int getFlammability(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
                    return 20;
                }

                @Override
                public int getFireSpreadSpeed(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
                    return 5;
                }
            });

    public static final DeferredBlock<Block> GHOST_ASPEN_LEAVES = registerBlock("ghost_aspen_leaves",
            properties -> new AttributeDependentUntintedParticleLeavesBlock(0.01f, ParticleTypes.ASH,
                    properties.mapColor(MapColor.METAL).strength(0.2F).randomTicks().sound(SoundType.GRASS)
                            .noOcclusion().isValidSpawn(Blocks::ocelotOrParrot).ignitedByLava().pushReaction(PushReaction.DESTROY),
                    ModAttributes.HAS_PLANE_SHIFT) {
                @Override
                public boolean isFlammable(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
                    return true;
                }

                @Override
                public int getFlammability(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
                    return 60;
                }

                @Override
                public int getFireSpreadSpeed(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
                    return 30;
                }
            });

    public static final DeferredBlock<Block> GHOST_ASPEN_SAPLING = registerBlock("ghost_aspen_sapling",
            properties -> new AttributeDependentSaplingBlock(ModTreeGrowers.GHOST_ASPEN, properties.mapColor(MapColor.PLANT)
                    .noCollision().randomTicks().instabreak().sound(SoundType.GRASS)
                    .pushReaction(PushReaction.DESTROY),
                    ModAttributes.HAS_PLANE_SHIFT));

    public static final DeferredBlock<Block> POTTED_GHOST_ASPEN_SAPLING = BLOCKS.registerBlock("potted_ghost_aspen_sapling",
            properties -> new AttributeDependentFlowerPotBlock(() -> ((FlowerPotBlock) Blocks.FLOWER_POT), GHOST_ASPEN_SAPLING, properties
                    .instabreak().noOcclusion().pushReaction(PushReaction.DESTROY),
                    ModAttributes.HAS_PLANE_SHIFT));

    public static final DeferredBlock<Block> SOMEFLOWER = registerBlock("sunflower",
            properties -> new AttributeDependentTallFlowerBlock(properties.
                    mapColor(MapColor.PLANT).noCollision().instabreak().sound(SoundType.GRASS).
                    offsetType(BlockBehaviour.OffsetType.XZ).ignitedByLava().pushReaction(PushReaction.DESTROY),
                    ModAttributes.HAS_PLANE_SHIFT));

    public static final DeferredBlock<Block> CHALK = BLOCKS.registerBlock("chalk",
            properties -> new CarpetBlock(properties.noCollision().instabreak().pushReaction(PushReaction.DESTROY)));


    private static <T extends Block> DeferredBlock<T> registerBlock(String name, Function<BlockBehaviour.Properties, T> function) {
        DeferredBlock<T> toReturn = BLOCKS.registerBlock(name, function);
        registerBlockItem(name, toReturn);
        return toReturn;
    }

    private static <T extends Block> void registerBlockItem(String name, DeferredBlock<T> block) {
        ModItems.ITEMS.registerItem(name, properties -> new BlockItem(block.get(), properties.useBlockDescriptionPrefix()));
    }

    public static void register(IEventBus eventBus) {
        BLOCKS.register(eventBus);
    }
}
