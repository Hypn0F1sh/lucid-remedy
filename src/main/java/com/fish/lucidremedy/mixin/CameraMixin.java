package com.fish.lucidremedy.mixin;

import com.fish.lucidremedy.attribute.ModAttributes;
import net.minecraft.client.Camera;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.core.BlockPos;
import net.minecraft.tags.FluidTags;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.FogType;
import net.minecraft.world.phys.Vec3;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Camera.class)
public class CameraMixin {

    @Shadow private boolean initialized;

    @Shadow private Level level;

    @Shadow @Final private BlockPos.MutableBlockPos blockPosition;

    @Shadow private Vec3 position;

    @Shadow private Entity entity;

    @Inject(
            method = "getFluidInCamera",
            at = @At("HEAD"),
            cancellable = true
    )
    public void waterblindness$ignoreWater(CallbackInfoReturnable<FogType> cir) {
        LocalPlayer player = Minecraft.getInstance().player;
        if (player.getAttribute(ModAttributes.HAS_WATER_BLINDNESS).getValue() > 0) {
            if (this.initialized) {
                FluidState fluidState1 = this.level.getFluidState(this.blockPosition);
                if (fluidState1.is(FluidTags.WATER) && this.position.y < (double) ((float) this.blockPosition.getY() + fluidState1.getHeight(this.level, this.blockPosition))) {
                    cir.setReturnValue(FogType.ATMOSPHERIC);
                }
            }
        }
    }
}