package com.fish.lucidremedy.mixin;

import com.fish.lucidremedy.attribute.ModAttributes;
import com.fish.lucidremedy.effect.EvasiumEffect;
import com.fish.lucidremedy.effect.ModEffects;
import com.fish.lucidremedy.events.moveLerpEvent.MoveLerpEventHandler;
import net.minecraft.tags.FluidTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityFluidInteraction;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.phys.Vec3;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.UUID;

@Mixin(Entity.class)
public abstract class EntityMixin {

    @Shadow @Final private EntityFluidInteraction fluidInteraction;

    @Inject(
            method = "collide(Lnet/minecraft/world/phys/Vec3;)Lnet/minecraft/world/phys/Vec3;",
            at = @At("HEAD"),
            cancellable = true
    )
    private void evasium$noBlockCollision(Vec3 movement, CallbackInfoReturnable<Vec3> cir) {

        Entity self = (Entity)(Object)this;
        UUID uuid = self.getUUID();
        EvasiumEffect.Mode mode = EvasiumEffect.MODE.getOrDefault(uuid, EvasiumEffect.Mode.FALL);

        if (self instanceof LivingEntity living &&
                living.hasEffect(ModEffects.EVASIUM_EFFECT) && mode == EvasiumEffect.Mode.FALL) {

            cir.setReturnValue(movement);
        } else if(MoveLerpEventHandler.MOVING.get(uuid) != null) {
            if (MoveLerpEventHandler.MOVING.get(uuid)) {

                cir.setReturnValue(movement);
            }
        }
    }

    @Inject(
            method = "isInWater",
            at = @At("HEAD"),
            cancellable = true
    )
    public void waterblindness$ignoreWater(CallbackInfoReturnable<Boolean> cir) {
        Entity self = (Entity)(Object)this;
        if (self instanceof Player player) {
            if (player.getAttribute(ModAttributes.HAS_WATER_BLINDNESS).getValue() > 0) {
                ((EntityAccessor) player).setWasTouchingWater(false);
                cir.setReturnValue(false);
            }
        }
    }

    @Inject(
            method = "isEyeInFluid",
            at = @At("HEAD"),
            cancellable = true
    )
    private void waterblindness$stopEyeFluidDetection(TagKey<Fluid> type, CallbackInfoReturnable<Boolean> cir) {
        Entity self = (Entity)(Object)this;
        if (self instanceof Player player) {
            if (player.getAttribute(ModAttributes.HAS_WATER_BLINDNESS).getValue() > 0) {
                if (type == FluidTags.WATER) {
                    cir.setReturnValue(false);
                }
            }
        }
    }

    @Inject(
            method = "updateFluidInteraction",
            at = @At("HEAD"),
            cancellable = true
    )
    private void waterblindness$stopFluidUpdate(CallbackInfoReturnable<Boolean> cir) {
        Entity self = (Entity)(Object)this;
        if (self instanceof Player player) {
            if (player.getAttribute(ModAttributes.HAS_WATER_BLINDNESS).getValue() > 0) {
                if (this.fluidInteraction.isInFluid(FluidTags.WATER)) {
                    cir.setReturnValue(false);
                }
            }
        }
    }
}
