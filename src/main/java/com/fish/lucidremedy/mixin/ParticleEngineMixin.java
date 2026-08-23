package com.fish.lucidremedy.mixin;

import com.fish.lucidremedy.attribute.ModAttributes;
import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.particle.ParticleEngine;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ParticleEngine.class)
public class ParticleEngineMixin {

    @Inject(
            method = "createParticle",
            at = @At("HEAD"),
            cancellable = true
    )
    private void waterblindness$blockWaterParticles(ParticleOptions options, double x, double y, double z, double xSpeed, double ySpeed, double zSpeed, CallbackInfoReturnable<Particle> cir) {
        LocalPlayer player = Minecraft.getInstance().player;
        if (player.getAttribute(ModAttributes.HAS_WATER_BLINDNESS).getValue() > 0) {
            if (options.getType() == ParticleTypes.DRIPPING_WATER || options.getType() == ParticleTypes.FALLING_WATER) {
                cir.setReturnValue(null);
                return;
            }

            if (options.getType() == ParticleTypes.SPLASH || options.getType() == ParticleTypes.UNDERWATER) {
                cir.setReturnValue(null);
                return;
            }

            if (options.getType() == ParticleTypes.BUBBLE || options.getType() == ParticleTypes.FISHING || options.getType() == ParticleTypes.BUBBLE_COLUMN_UP) {
                cir.setReturnValue(null);
            }
        }
    }
}