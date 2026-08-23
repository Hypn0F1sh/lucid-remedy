package com.fish.lucidremedy.mixin;

import com.fish.lucidremedy.attribute.ModAttributes;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.lighting.LightEngine;
import net.minecraft.world.level.lighting.SkyLightEngine;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(LightEngine.class)
public class LightEngineMixin {


    @Inject(
            method = "getOpacity",
            at = @At("HEAD"),
            cancellable = true
    )
    private void waterblindness$opacityOverride(BlockState state, CallbackInfoReturnable<Integer> cir) {
        if ((Object) this instanceof SkyLightEngine) {
            LocalPlayer player = Minecraft.getInstance().player;
            if (player != null) {
                if (player.getAttribute(ModAttributes.HAS_WATER_BLINDNESS).getValue() > 0) {
                    if (state.is(Blocks.WATER)) {
                        cir.setReturnValue(0);
                    }
                }
            }
        }
    }
}
