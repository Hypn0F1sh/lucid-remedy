package com.fish.lucidremedy.block;

import com.fish.lucidremedy.LucidRemedy;
import com.fish.lucidremedy.attribute.ModAttributes;
import com.fish.lucidremedy.block.custom.AttributeDependentBlock;
import com.fish.lucidremedy.block.custom.WatergrassBlock;
import com.fish.lucidremedy.item.ModItems;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
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
            properties -> new AttributeDependentBlock(properties.noOcclusion(), ModAttributes.HAS_PLANE_SHIFT));

    public static final DeferredBlock<Block> PHASE_GNEISS_BRICKS = registerBlock("phase_gneiss_bricks",
            properties -> new AttributeDependentBlock(properties.noOcclusion(), ModAttributes.HAS_PLANE_SHIFT));

    public static final DeferredBlock<Block> POLISHED_PHASE_GNEISS = registerBlock("polished_phase_gneiss",
            properties -> new AttributeDependentBlock(properties.noOcclusion(), ModAttributes.HAS_PLANE_SHIFT));

    public static final DeferredBlock<Block> CHALK = BLOCKS.registerBlock("chalk",
            properties -> new Block(properties.noCollision().instabreak().pushReaction(PushReaction.DESTROY)));


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
