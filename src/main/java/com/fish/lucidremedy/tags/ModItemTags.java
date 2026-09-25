package com.fish.lucidremedy.tags;

import com.fish.lucidremedy.LucidRemedy;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;

public class ModItemTags {

    public static final TagKey<Item> GHOST_ITEM = bind("ghost_item");

    private  ModItemTags() {
    }

    private static TagKey<Item> bind(String name) {
        return TagKey.create(Registries.ITEM, Identifier.fromNamespaceAndPath(LucidRemedy.MODID,name));
    }

    public static TagKey<Item> create(Identifier name) {
        return TagKey.create(Registries.ITEM, name);
    }
}
