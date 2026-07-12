package com.fish.lucidremedy.effect;


import com.fish.lucidremedy.powers.Ascend;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class EvasiumEffect extends MobEffect {
    protected EvasiumEffect(MobEffectCategory category, int color, ParticleOptions particleOptions) {
        super(category, color, particleOptions);
    }
    public enum Mode{
        FALL,
        ASCEND
    }

    public static final Map<UUID, Mode> MODE = new HashMap<>();
    public static final Map<UUID, Boolean> INITIAL_FALL = new HashMap<>();

    @Override
    public boolean applyEffectTick(ServerLevel serverLevel, LivingEntity mob, int amplification) {
        BlockPos pos = mob.getOnPos().above(1);
        UUID uuid = mob.getUUID();
        mob.invulnerableTime = 20;
        if (MODE.get(uuid) == Mode.ASCEND) {
            if (mob.getDeltaMovement().y < 0) {
                Ascend.activate(serverLevel, mob);
                MODE.remove(uuid);
                mob.removeEffect(ModEffects.EVASIUM_EFFECT);
            }
        } else if (MODE.get(uuid) == Mode.FALL) {
            mob.noPhysics = true;
            boolean initial = INITIAL_FALL.getOrDefault(uuid, false);
            if (!initial) {
                if (serverLevel.getBlockState(pos).isSolid()) {
                    INITIAL_FALL.put(uuid, true);
                }
            }
            if (initial) {
                if (!serverLevel.getBlockState(pos).isSolid() || serverLevel.getBlockState(pos).getFluidState().getFluidType().getIsWaterLike()) {
                    mob.removeEffect(ModEffects.EVASIUM_EFFECT);
                    INITIAL_FALL.remove(uuid);
                    MODE.remove(uuid);
                    mob.invulnerableTime = 1;
                    mob.noPhysics = false;
                }
            }
        }
        return super.applyEffectTick(serverLevel, mob, amplification);
    }

    @Override
    public void onEffectAdded(LivingEntity mob, int amplifier) {
        BlockPos pos = mob.getOnPos().above(1);
        Level level = mob.level();
        UUID uuid = mob.getUUID();
        if (level.getBlockState(pos).getFluidState().getFluidType().getIsWaterLike() ||
                level.getBlockState(pos.above(1)).getFluidState().getFluidType().getIsWaterLike() ||
                mob.isInLiquid()) {

            double power = Math.min(3, (getWaterDepth(pos, level)* 0.5)+0.5);
            MODE.put(uuid, Mode.ASCEND);
            mob.push(new Vec3(0, power, 0));

            if (mob instanceof ServerPlayer player) {
                player.hurtMarked = true;
            }

        } else {
            MODE.put(uuid, Mode.FALL);
            INITIAL_FALL.put(uuid, false);
        }
        super.onEffectAdded(mob, amplifier);
    }

    @Override
    public boolean shouldApplyEffectTickThisTick(int tickCount, int amplification) {
        return true;
    }

    public double getWaterDepth(BlockPos pos, Level level) {
        double result = 0;
        for (int y = 0; y < 10; y++) {
            if (! level.getFluidState(pos.above(y)).getFluidType().getIsWaterLike()) {
                result = y;
                break;
            }
        }
        return result;
    }
}
