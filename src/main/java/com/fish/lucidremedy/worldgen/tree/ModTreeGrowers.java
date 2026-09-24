package com.fish.lucidremedy.worldgen.tree;

import com.fish.lucidremedy.LucidRemedy;
import com.fish.lucidremedy.worldgen.ModConfiguredFeatures;
import net.minecraft.world.level.block.grower.TreeGrower;

import java.util.Optional;

public class ModTreeGrowers {
    public static final TreeGrower GHOST_ASPEN = new TreeGrower(LucidRemedy.MODID + ":ghost_aspen",
            Optional.empty(), Optional.of(ModConfiguredFeatures.GHOST_ASPEN_KEY), Optional.empty());
}
