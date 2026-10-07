package com.fish.lucidremedy.mixin;

import com.fish.lucidremedy.block.custom.*;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.core.Holder;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.state.BlockBehaviour.BlockStateBase;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Set;

@Mixin(BlockStateBase.class)
public class BlockStateMixin {

    @Inject(
            method = "getRenderShape",
            at = @At("HEAD"),
            cancellable = true
    )
    private void onGetRenderShape(CallbackInfoReturnable<RenderShape> cir) {
        BlockStateBase state = (BlockStateBase) (Object) this;
        var block = state.getBlock();
        Holder<Attribute> attribute = AttributeGetter.getAttribute(block);
        if (attribute != null) {

            LocalPlayer player = Minecraft.getInstance().player;
            if (player == null) {
                cir.setReturnValue(RenderShape.INVISIBLE);
                return;
            }

            var attributeInstance = player.getAttribute(attribute);
            if (attributeInstance != null && attributeInstance.getValue() > 0.0) {
                cir.setReturnValue(RenderShape.MODEL);
            } else {
                cir.setReturnValue(RenderShape.INVISIBLE);
            }
        }
    }
}
