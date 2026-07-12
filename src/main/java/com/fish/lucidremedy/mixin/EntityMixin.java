package com.fish.lucidremedy.mixin;

import com.fish.lucidremedy.effect.EvasiumEffect;
import com.fish.lucidremedy.effect.ModEffects;
import com.fish.lucidremedy.events.moveLerpEvent.MoveLerpEventHandler;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.phys.Vec3;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.UUID;

@Mixin(Entity.class)
public abstract class EntityMixin {

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
}
