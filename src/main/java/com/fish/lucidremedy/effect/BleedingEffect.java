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

public class BleedingEffect extends MobEffect{
    protected BleedingEffect(MobEffectCategory category, int color) {
        super(category, color);
    }

    @Override
    public boolean applyEffectTick(ServerLevel serverLevel, LivingEntity mob, int amplification) {
        Registry<DamageType> dTypeReg = mob.damageSources().damageTypes;
        Holder.Reference<DamageType> dType = (Holder.Reference)dTypeReg.get(NeoForgeMod.POISON_DAMAGE).orElse(dTypeReg.getOrThrow(DamageTypes.MAGIC));
        float damage = (float) (mob.getHealth() * 0.05);
        mob.hurtServer(serverLevel, new DamageSource(dType), damage);
        if (mob.hasEffect(MobEffects.POISON)) {
            mob.removeEffect(MobEffects.POISON);
            mob.addEffect(new MobEffectInstance(
                    ModEffects.INFECTION_EFFECT,
                    1200
            ));
        }
        return true;
    }

    @Override
    public boolean shouldApplyEffectTickThisTick(int tickCount, int amplification) {
        int interval = 20 >> amplification;
        return interval > 0 ? tickCount % interval == 0 : true;
    }
}
