package com.fish.lucidremedy.mixin;

import com.fish.lucidremedy.block.custom.AttributeDependentBlock;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.state.BlockBehaviour.BlockStateBase;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(BlockStateBase.class)
public class BlockStateMixin {

    @Inject(
            method = "getRenderShape",
            at = @At("HEAD"),
            cancellable = true
    )
    private void onGetRenderShape(CallbackInfoReturnable<RenderShape> cir) {
        BlockStateBase state = (BlockStateBase) (Object) this;

        if (state.getBlock() instanceof AttributeDependentBlock block) {

            LocalPlayer player = Minecraft.getInstance().player;
            if (player == null) {
                cir.setReturnValue(RenderShape.INVISIBLE);
                return;
            }

            var attributeInstance = player.getAttribute(block.attribute);
            if (attributeInstance != null && attributeInstance.getValue() > 0.0) {
                cir.setReturnValue(RenderShape.MODEL);
            } else {
                cir.setReturnValue(RenderShape.INVISIBLE);
            }
        }
    }
}
