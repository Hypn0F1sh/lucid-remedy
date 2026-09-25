package com.fish.lucidremedy.datagen;

import com.fish.lucidremedy.LucidRemedy;
import com.fish.lucidremedy.block.ModBlocks;
import com.fish.lucidremedy.tags.ModItemTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.common.data.ItemTagsProvider;

import java.util.concurrent.CompletableFuture;

public class ModItemTagsProvider extends ItemTagsProvider {
    public ModItemTagsProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider) {
        super(output, lookupProvider, LucidRemedy.MODID);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {
        tag(ModItemTags.GHOST_ITEM)
                .add(ModBlocks.GHOST_ASPEN_LEAVES.asItem())
                .add(ModBlocks.GHOST_ASPEN_LOG.asItem())
                .add(ModBlocks.GHOST_ASPEN_WOOD.asItem())
                .add(ModBlocks.GHOST_ASPEN_PLANKS.asItem())
                .add(ModBlocks.GHOST_ASPEN_SAPLING.asItem())
                .add(ModBlocks.STRIPPED_GHOST_ASPEN_LOG.asItem())
                .add(ModBlocks.STRIPPED_GHOST_ASPEN_WOOD.asItem())
                .add(ModBlocks.PHASE_GNEISS.asItem())
                .add(ModBlocks.PHASE_GNEISS_BRICKS.asItem())
                .add(ModBlocks.POLISHED_PHASE_GNEISS.asItem());
    }
}
