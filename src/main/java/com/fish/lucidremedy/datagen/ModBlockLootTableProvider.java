package com.fish.lucidremedy.datagen;

import com.fish.lucidremedy.block.ModBlocks;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.level.block.Block;

import java.util.Set;

public class ModBlockLootTableProvider extends BlockLootSubProvider {
    public ModBlockLootTableProvider(HolderLookup.Provider registries) {
        super(Set.of(), FeatureFlags.REGISTRY.allFlags(), registries);
    }

    @Override
    protected void generate() {
        dropSelf(ModBlocks.SPIRIT_GNEISS.get());
        dropSelf(ModBlocks.POLISHED_SPIRIT_GNEISS.get());
        dropSelf(ModBlocks.SPIRIT_GNEISS_BRICKS.get());

        dropSelf(ModBlocks.GHOST_ASPEN_LOG.get());
        dropSelf(ModBlocks.GHOST_ASPEN_WOOD.get());
        dropSelf(ModBlocks.STRIPPED_GHOST_ASPEN_WOOD.get());
        dropSelf(ModBlocks.STRIPPED_GHOST_ASPEN_LOG.get());
        dropSelf(ModBlocks.GHOST_ASPEN_PLANKS.get());
        dropSelf(ModBlocks.GHOST_ASPEN_SAPLING.get());

        dropSelf(ModBlocks.MOONFLOWER.get());

        dropPottedContents(ModBlocks.POTTED_GHOST_ASPEN_SAPLING.get());

        add(ModBlocks.GHOST_ASPEN_LEAVES.get(),createShearsOrSilkTouchOnlyDrop(ModBlocks.GHOST_ASPEN_LEAVES.get()));

        //No Drop
        add(ModBlocks.CHALK.get(), noDrop());
    }

    @Override
    protected Iterable<Block> getKnownBlocks() {
        return ModBlocks.BLOCKS.getEntries().stream().map(Holder::value)::iterator;
    }
}
