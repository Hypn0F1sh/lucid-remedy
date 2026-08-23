package com.fish.lucidremedy.item;

import com.fish.lucidremedy.LucidRemedy;
import com.fish.lucidremedy.food.ModFoods;
import com.fish.lucidremedy.item.custom.ChiselItem;
import com.fish.lucidremedy.item.custom.debug.FeralPactItem;
import com.fish.lucidremedy.item.custom.debug.WaterPactItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.ToolMaterial;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModItems {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(LucidRemedy.MODID);

    public static final DeferredItem<Item> LUCID_REMEDY = ITEMS.registerItem("lucid_remedy",
            properties -> new Item(properties.stacksTo(1).
                    food(ModFoods.LUCID_REMEDY, ModFoods.LUCID_REMEDY_CONSUMABLE).usingConvertsTo(Items.GLASS_BOTTLE)));

    public static final DeferredItem<Item> RAGE_EMETIC = ITEMS.registerItem("rage_emetic",
            properties -> new Item(properties.stacksTo(8).
                    food(ModFoods.RAGE_EMETIC, ModFoods.RAGE_EMETIC_CONSUMABLE).usingConvertsTo(Items.GLASS_BOTTLE)));

    public static final DeferredItem<Item> EVASIUM = ITEMS.registerItem("evasium",
            properties -> new Item(properties.stacksTo(8).
                    food(ModFoods.EVASIUM, ModFoods.EVASIUM_CONSUMABLE).usingConvertsTo(Items.GLASS_BOTTLE)));

    public static final DeferredItem<Item> EPIDEMIC_SCALPEL = ITEMS.registerItem("epidemic_scalpel",
            properties -> new Item(properties.stacksTo(1).durability(0)
                    .sword(ToolMaterial.IRON, -2, 9999995)));

    public static final DeferredItem<Item> CHISEL = ITEMS.registerItem("chisel",
            properties -> new ChiselItem(properties.stacksTo(1).useCooldown(0.1F)));

    public static final DeferredItem<Item> LUKES_CLOVER = ITEMS.registerItem("lukes_clover",
            properties -> new Item(properties.stacksTo(1)));


    // Debug items
    public static final DeferredItem<Item> FERAL_PACT = ITEMS.registerItem("feral_pact",
            properties -> new FeralPactItem(properties.stacksTo(1).useCooldown(0.1F)));

    public static final DeferredItem<Item> WATER_PACT = ITEMS.registerItem("water_pact",
            properties -> new WaterPactItem(properties.stacksTo(1).useCooldown(0.1F)));

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
