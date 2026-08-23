package com.fish.lucidremedy.mixin;

import com.fish.lucidremedy.attribute.ModAttributes;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.client.resources.sounds.SoundInstance;
import net.minecraft.client.sounds.SoundEngine;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(SoundEngine.class)
public class SoundEngineMixin {

    @Inject(
            method = "play",
            at = @At("HEAD"),
            cancellable = true
    )
    private void waterblindness$blockWaterSounds(SoundInstance instance, CallbackInfoReturnable<SoundEngine.PlayResult> cir) {
        LocalPlayer player = Minecraft.getInstance().player;
        if (player !=  null) {
            if (player.getAttribute(ModAttributes.HAS_WATER_BLINDNESS).getValue() > 0) {
                String soundPath = instance.getIdentifier().getPath();
                if ((soundPath.contains("water") ||
                        soundPath.contains("swim") ||
                        soundPath.contains("splash") ||
                        soundPath.contains("bubble") ||
                        soundPath.contains("bucket.fill") ||
                        soundPath.contains("bucket.empty")) &&
                        ! soundPath.contains("lava") &&
                        ! soundPath.contains("drowned") &&
                        ! soundPath.contains("nautilus") &&
                        ! soundPath.contains("fish") &&
                        ! soundPath.contains("potion")) {

                    cir.setReturnValue(SoundEngine.PlayResult.NOT_STARTED);
                }
            }
        }
    }
}