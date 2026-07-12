package com.fish.lucidremedy.effect;

import com.fish.lucidremedy.LucidRemedy;
import com.fish.lucidremedy.attribute.ModAttributes;
import com.mojang.serialization.MapCodec;
import net.minecraft.core.Holder;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.resources.Identifier;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModEffects {
    public static final DeferredRegister<MobEffect> MOB_EFFECTS = DeferredRegister.create(BuiltInRegistries.MOB_EFFECT, LucidRemedy.MODID);

    public static final Holder<MobEffect> NONLUCID_EFFECT = MOB_EFFECTS.register("nonlucid",
            () -> new NonLucidEffect(MobEffectCategory.NEUTRAL, 0x000000)
                    .addAttributeModifier(ModAttributes.NONLUCID,
                            Identifier.fromNamespaceAndPath(LucidRemedy.MODID, "nonlucid"),
                            1,
                            AttributeModifier.Operation.ADD_VALUE));

    public static final Holder<MobEffect> TEMPER_RETCH_EFFECT = MOB_EFFECTS.register("temper_retch",
            () -> new TemperRetchEffect(MobEffectCategory.NEUTRAL, 0xff8800, ParticleTypes.LAVA)
                    .addAttributeModifier(Attributes.ATTACK_DAMAGE,
                            Identifier.fromNamespaceAndPath(LucidRemedy.MODID, "temper_retch"),
                            1,
                            AttributeModifier.Operation.ADD_VALUE));

    public static final Holder<MobEffect> EVASIUM_EFFECT = MOB_EFFECTS.register("evasium",
            () -> new EvasiumEffect(MobEffectCategory.NEUTRAL, 0xff8800, ParticleTypes.SCRAPE)
                    );


    public static void register(IEventBus eventBus) {
        MOB_EFFECTS.register(eventBus);
    }
}
