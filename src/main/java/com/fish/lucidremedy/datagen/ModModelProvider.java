package com.fish.lucidremedy.datagen;

import com.fish.lucidremedy.LucidRemedy;
import com.fish.lucidremedy.block.ModBlocks;
import com.fish.lucidremedy.item.ModItems;
import net.minecraft.client.data.models.BlockModelGenerators;
import net.minecraft.client.data.models.ItemModelGenerators;
import net.minecraft.client.data.models.ModelProvider;
import net.minecraft.client.data.models.model.ModelTemplates;
import net.minecraft.client.data.models.model.TexturedModel;
import net.minecraft.data.PackOutput;

public class ModModelProvider extends ModelProvider {
    public ModModelProvider(PackOutput output) {
        super(output, LucidRemedy.MODID);
    }

    @Override
    protected void registerModels(BlockModelGenerators blockModels, ItemModelGenerators itemModels) {
        itemModels.generateFlatItem(ModItems.LUCID_REMEDY.get(), ModelTemplates.FLAT_ITEM);
        itemModels.generateFlatItem(ModItems.RAGE_EMETIC.get(), ModelTemplates.FLAT_ITEM);
        itemModels.generateFlatItem(ModItems.EVASIUM.get(), ModelTemplates.FLAT_ITEM);
        itemModels.generateFlatItem(ModItems.CHISEL.get(), ModelTemplates.FLAT_ITEM);
        itemModels.generateFlatItem(ModItems.LUKES_CLOVER.get(), ModelTemplates.FLAT_ITEM);
        itemModels.generateFlatItem(ModItems.EPIDEMIC_SCALPEL.get(), ModelTemplates.FLAT_HANDHELD_ITEM);

        itemModels.generateFlatItem(ModItems.EGO_STONE_VITALITY.get(), ModelTemplates.FLAT_ITEM);
        itemModels.generateFlatItem(ModItems.EGO_STONE_WRATH.get(), ModelTemplates.FLAT_ITEM);
        itemModels.generateFlatItem(ModItems.EGO_STONE_AEGIS.get(), ModelTemplates.FLAT_ITEM);
        itemModels.generateFlatItem(ModItems.EGO_STONE_AGILITY.get(), ModelTemplates.FLAT_ITEM);
        itemModels.generateFlatItem(ModItems.EGO_STONE_SPARK.get(), ModelTemplates.FLAT_ITEM);

        //Blocks
        blockModels.createTrivialCube(ModBlocks.PHASE_GNEISS.get());
        blockModels.createTrivialCube(ModBlocks.PHASE_GNEISS_BRICKS.get());
        blockModels.createTrivialCube(ModBlocks.POLISHED_PHASE_GNEISS.get());

        blockModels.createTrivialCube(ModBlocks.GHOST_ASPEN_PLANKS.get());

        blockModels.woodProvider(ModBlocks.GHOST_ASPEN_LOG.get()).logWithHorizontal(ModBlocks.GHOST_ASPEN_LOG.get()).wood(ModBlocks.GHOST_ASPEN_WOOD.get());
        blockModels.woodProvider(ModBlocks.STRIPPED_GHOST_ASPEN_LOG.get()).logWithHorizontal(ModBlocks.STRIPPED_GHOST_ASPEN_LOG.get()).wood(ModBlocks.STRIPPED_GHOST_ASPEN_WOOD.get());

        blockModels.createTintedLeaves(ModBlocks.GHOST_ASPEN_LEAVES.get(), TexturedModel.LEAVES, -12012265);

        blockModels.createPlantWithDefaultItem(ModBlocks.GHOST_ASPEN_SAPLING.get(), ModBlocks.POTTED_GHOST_ASPEN_SAPLING.get(), BlockModelGenerators.PlantType.NOT_TINTED);

        //Debug
        itemModels.generateFlatItem(ModItems.FERAL_PACT.get(), ModelTemplates.FLAT_ITEM);
        itemModels.generateFlatItem(ModItems.WATER_PACT.get(), ModelTemplates.FLAT_ITEM);
        itemModels.generateFlatItem(ModItems.PLANE_SHIFT_PACT.get(), ModelTemplates.FLAT_ITEM);
    }
}
