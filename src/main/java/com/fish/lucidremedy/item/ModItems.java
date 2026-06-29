package com.fish.lucidremedy.item;

import com.fish.lucidremedy.LucidRemedy;
import com.fish.lucidremedy.food.ModFoods;
import com.fish.lucidremedy.item.custom.ChiselItem;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemUseAnimation;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.ToolMaterial;
import net.minecraft.world.item.component.Consumable;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModItems {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(LucidRemedy.MODID);

    public static final DeferredItem<Item> LUCID_REMEDY = ITEMS.registerItem("lucid_remedy",
            properties -> new Item(properties.stacksTo(1).
                    food(ModFoods.LUCID_REMEDY, ModFoods.LUCID_REMEDY_CONSUMABLE).usingConvertsTo(Items.GLASS_BOTTLE)));

    public static final DeferredItem<Item> EPIDEMIC_SCALPEL = ITEMS.registerItem("epidemic_scalpel",
            properties -> new Item(properties.stacksTo(1).durability(-1)
                    .sword(ToolMaterial.IRON, 2, 200)));

    public static final DeferredItem<Item> CHISEL = ITEMS.registerItem("chisel",
            properties -> new ChiselItem(properties.stacksTo(1).useCooldown(0.1F)));

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
