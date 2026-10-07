package com.fish.lucidremedy.datagen;

import com.fish.lucidremedy.LucidRemedy;
import com.fish.lucidremedy.block.ModBlocks;
import com.fish.lucidremedy.item.ModItems;
import net.minecraft.client.data.models.BlockModelGenerators;
import net.minecraft.client.data.models.ItemModelGenerators;
import net.minecraft.client.data.models.ModelProvider;
import net.minecraft.client.data.models.model.*;
import net.minecraft.client.data.models.model.ModelTemplates;
import net.minecraft.client.data.models.model.TexturedModel;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.Identifier;
import net.minecraft.world.level.block.Block;

import java.util.Set;
import java.util.stream.Stream;

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

        //Ignored Items
        itemModels.itemModelOutput.accept(ModItems.CHALK.get(),
                ItemModelUtils.plainModel(Identifier.fromNamespaceAndPath(LucidRemedy.MODID, "item/chalk")));
        itemModels.itemModelOutput.accept(ModBlocks.MOONFLOWER.get().asItem(),
                ItemModelUtils.plainModel(Identifier.fromNamespaceAndPath(LucidRemedy.MODID, "item/moonflower")));

        //Blocks
        blockModels.createTrivialCube(ModBlocks.SPIRIT_GNEISS.get());
        blockModels.createTrivialCube(ModBlocks.SPIRIT_GNEISS_BRICKS.get());
        blockModels.createTrivialCube(ModBlocks.POLISHED_SPIRIT_GNEISS.get());

        blockModels.createTrivialBlock(ModBlocks.CHALK.get(), TexturedModel.CARPET);

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

    private static final Set<Block> IGNORED_BLOCKS = Set.of(
            ModBlocks.MOONFLOWER.get()
    );

    @Override
    protected Stream<? extends Holder<Block>> getKnownBlocks() {
        return BuiltInRegistries.BLOCK.listElements().filter((holder) -> holder.getKey().identifier().getNamespace().equals(this.modId))
                .filter(holder -> !IGNORED_BLOCKS.contains(holder.value()));
    }
}
