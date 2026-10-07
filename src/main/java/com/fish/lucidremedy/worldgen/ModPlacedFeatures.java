package com.fish.lucidremedy.worldgen;

import com.fish.lucidremedy.LucidRemedy;
import com.fish.lucidremedy.block.ModBlocks;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.data.worldgen.placement.VegetationPlacements;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.levelgen.blockpredicates.BlockPredicate;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.placement.*;

import java.util.List;

public class ModPlacedFeatures {

    public static final ResourceKey<PlacedFeature> GHOST_ASPEN_PLACED_KEY = registerKey("ghost_aspen_placed");
    public static final ResourceKey<PlacedFeature> PATCH_MOONFLOWER = registerKey("patch_moonflower");

    public static void bootstrap(BootstrapContext<PlacedFeature> context) {
        var configuredFeatures = context.lookup(Registries.CONFIGURED_FEATURE);

        register(context, GHOST_ASPEN_PLACED_KEY, configuredFeatures.getOrThrow(ModConfiguredFeatures.GHOST_ASPEN_KEY),
                VegetationPlacements.treePlacement(PlacementUtils.countExtra(6, 0.2f, 2),
                        ModBlocks.GHOST_ASPEN_SAPLING.get()));

        register(context, PATCH_MOONFLOWER, configuredFeatures.getOrThrow(ModConfiguredFeatures.MOONFLOWER_KEY),
                List.of(new PlacementModifier[]{RarityFilter.onAverageOnceEvery(3),
                        InSquarePlacement.spread(),
                        PlacementUtils.HEIGHTMAP,
                        BiomeFilter.biome(),
                        CountPlacement.of(96),
                        RandomOffsetPlacement.ofTriangle(7, 3),
                        BlockPredicateFilter.forPredicate(BlockPredicate.ONLY_IN_AIR_PREDICATE)
                }));
    }

    private static ResourceKey<PlacedFeature> registerKey(String name) {
        return ResourceKey.create(Registries.PLACED_FEATURE, Identifier.fromNamespaceAndPath(LucidRemedy.MODID, name));
    }

    private static void register(BootstrapContext<PlacedFeature> context, ResourceKey<PlacedFeature> key,
                                 Holder<ConfiguredFeature<?, ?>> configuration, List<PlacementModifier> modifiers) {
        context.register(key, new PlacedFeature(configuration, List.copyOf(modifiers)));
    }
}
