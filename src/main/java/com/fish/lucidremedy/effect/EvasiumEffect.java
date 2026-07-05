package com.fish.lucidremedy.effect;


import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class EvasiumEffect extends MobEffect {
    protected EvasiumEffect(MobEffectCategory category, int color, ParticleOptions particleOptions) {
        super(category, color, particleOptions);
    }

    public static final Map<UUID, Boolean> INITIAL_FALL = new HashMap<>();

    @Override
    public boolean applyEffectTick(ServerLevel serverLevel, LivingEntity mob, int amplification) {
        BlockPos pos = mob.getOnPos().above(1);
        UUID uuid = mob.getUUID();
        mob.noPhysics = true;
        boolean initial = INITIAL_FALL.getOrDefault(uuid, false);
        if ( ! initial) {
            if (serverLevel.getBlockState(pos).isSolid()) {INITIAL_FALL.put(uuid, true);}
        }
        if (initial) {
        if (! serverLevel.getBlockState(pos).isSolid() || serverLevel.getBlockState(pos).getFluidState().getFluidType().getIsWaterLike()) {
            mob.removeEffect(ModEffects.EVASIUM_EFFECT);
            INITIAL_FALL.remove(uuid);
            mob.noPhysics = false;
        }}
        return super.applyEffectTick(serverLevel, mob, amplification);
    }

    @Override
    public void onEffectAdded(LivingEntity mob, int amplifier) {
        UUID uuid = mob.getUUID();
        INITIAL_FALL.put(uuid, false);
        super.onEffectAdded(mob, amplifier);
    }

    @Override
    public boolean shouldApplyEffectTickThisTick(int tickCount, int amplification) {
        return true;
    }
}
