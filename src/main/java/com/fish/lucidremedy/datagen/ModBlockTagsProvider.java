package com.fish.lucidremedy.datagen;

import com.fish.lucidremedy.LucidRemedy;
import com.fish.lucidremedy.block.ModBlocks;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.BlockTags;
import net.neoforged.neoforge.common.data.BlockTagsProvider;

import java.util.concurrent.CompletableFuture;

public class ModBlockTagsProvider extends BlockTagsProvider {
    public ModBlockTagsProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider) {
        super(output, lookupProvider, LucidRemedy.MODID);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {
        tag(BlockTags.LEAVES)
                .add(ModBlocks.GHOST_ASPEN_LEAVES.get());
        tag(BlockTags.PLANKS)
                .add(ModBlocks.GHOST_ASPEN_PLANKS.get());
        tag(BlockTags.LOGS_THAT_BURN)
                .add(ModBlocks.GHOST_ASPEN_LOG.get())
                .add(ModBlocks.GHOST_ASPEN_WOOD.get())
                .add(ModBlocks.STRIPPED_GHOST_ASPEN_LOG.get())
                .add(ModBlocks.STRIPPED_GHOST_ASPEN_WOOD.get());

        tag(BlockTags.FLOWER_POTS)
                .add(ModBlocks.POTTED_GHOST_ASPEN_SAPLING.get());
    }
}