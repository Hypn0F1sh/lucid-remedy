package com.fish.lucidremedy.block;

import com.fish.lucidremedy.LucidRemedy;
import com.fish.lucidremedy.block.custom.EngravingBlock;
import com.google.common.eventbus.EventBus;
import net.minecraft.world.level.block.Block;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModBlocks {
    public static final DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks(LucidRemedy.MODID);

    public static final DeferredBlock<Block> ENGRAVING = BLOCKS.registerBlock("engraving",
            properties -> new EngravingBlock(properties));

    public static void register(IEventBus eventBus) {
        BLOCKS.register(eventBus);
    }
}
