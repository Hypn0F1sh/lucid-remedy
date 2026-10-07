package com.fish.lucidremedy.block.custom;

import net.minecraft.core.Holder;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.level.block.Block;
import org.spongepowered.asm.mixin.Unique;

import javax.annotation.Nullable;
import java.util.Set;

public class AttributeGetter {

    public static @Nullable Holder<Attribute> getAttribute(Block block) {
        if (block instanceof AttributeDependentBlock b) {return b.getAttribute();}
        if (block instanceof AttributeDependentFlowerPotBlock b) {return b.getAttribute();}
        if (block instanceof AttributeDependentRotatedPillarBlock b) {return b.getAttribute();}
        if (block instanceof AttributeDependentSaplingBlock b) {return b.getAttribute();}
        if (block instanceof AttributeDependentTallFlowerBlock b) {return b.getAttribute();}
        if (block instanceof AttributeDependentUntintedParticleLeavesBlock b) {return b.getAttribute();}
        return null;
    }
}
