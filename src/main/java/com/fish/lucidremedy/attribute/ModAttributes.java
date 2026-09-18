package com.fish.lucidremedy.attribute;

import com.fish.lucidremedy.LucidRemedy;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.RangedAttribute;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.common.BooleanAttribute;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModAttributes {
    public static final DeferredRegister<Attribute> ATTRIBUTES = DeferredRegister.create(
            BuiltInRegistries.ATTRIBUTE, LucidRemedy.MODID);

    public static final Holder<Attribute> HAS_PACT_FERAL = ATTRIBUTES.register("has_pact_feral", () -> new BooleanAttribute(
            "attributes.lucidremedy.has_pact_feral",
            false
            ).setSyncable(true)
    );

    public static final Holder<Attribute> HAS_WATER_BLINDNESS = ATTRIBUTES.register("has_water_blindness", () -> new BooleanAttribute(
            "attributes.lucidremedy.has_water_blindness",
            false
            ).setSyncable(true)
    );

    public static final Holder<Attribute> HAS_PLANE_SHIFT = ATTRIBUTES.register("has_plane_shift", () -> new BooleanAttribute(
                    "attributes.lucidremedy.has_plane_shift",
                    false
            ).setSyncable(true)
    );

    public static void register(IEventBus eventBus) {ATTRIBUTES.register(eventBus);
    }
}
