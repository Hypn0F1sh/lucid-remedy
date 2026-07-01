package com.fish.lucidremedy.attribute;

import com.fish.lucidremedy.LucidRemedy;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.RangedAttribute;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModAttributes {
    public static final DeferredRegister<Attribute> ATTRIBUTES = DeferredRegister.create(
            BuiltInRegistries.ATTRIBUTE, LucidRemedy.MODID);

    public static final Holder<Attribute> NONLUCID = ATTRIBUTES.register("nonlucid", () -> new RangedAttribute(
            "attributes.lucidremedy.nonlucid",
            0,
            0,
            1));

    public static void register(IEventBus eventBus) {ATTRIBUTES.register(eventBus);
    }
}
