package com.fish.lucidremedy.effect;

import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageType;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.neoforged.neoforge.common.NeoForgeMod;

public class InfectionEffect extends MobEffect{
    protected InfectionEffect(MobEffectCategory category, int color) {
        super(category, color);
    }

    @Override
    public boolean applyEffectTick(ServerLevel serverLevel, LivingEntity mob, int amplification) {
        if (mob.hasEffect(MobEffects.POISON)) {
            int duration = mob.getEffect(MobEffects.POISON).getDuration();
            mob.removeEffect(MobEffects.POISON);
            mob.addEffect(new MobEffectInstance(
                    ModEffects.INFECTION_EFFECT,
                    mob.getEffect(ModEffects.INFECTION_EFFECT).getDuration() + duration,
                    mob.getEffect(ModEffects.INFECTION_EFFECT).getAmplifier() + 1
            ));
        }
        mob.addEffect(new MobEffectInstance(
                ModEffects.INFECTION_EFFECT,
                mob.getEffect(ModEffects.INFECTION_EFFECT).getDuration(),
                mob.getEffect(ModEffects.INFECTION_EFFECT).getAmplifier() + 1
        ));
        return true;
    }

    @Override
    public boolean shouldApplyEffectTickThisTick(int tickCount, int amplification) {
        return tickCount%200 == 0;
    }
}
