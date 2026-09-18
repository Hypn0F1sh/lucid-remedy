package com.fish.lucidremedy.mixin;

import com.fish.lucidremedy.item.ModItems;
import com.fish.lucidremedy.item.UUIDItem;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.UUID;

@Mixin(ItemEntity.class)
public abstract class ItemEntityMixin {

    @Shadow private int age;

    @Shadow public abstract ItemStack getItem();

    @Shadow @Final private static int INFINITE_LIFETIME;

    @Inject(
            method = "tick",
            at = @At("HEAD")
    )
    private void uuidItem$tick(CallbackInfo ci) {
        if (this.getItem().getItem() instanceof UUIDItem uuidItem) {
            this.age = INFINITE_LIFETIME;
        }
    }

    @Inject(
            method = "getDefaultGravity",
            at = @At("HEAD"),
            cancellable = true
    )
    private void uuidItem$gravity(CallbackInfoReturnable<Double> cir) {
        if (
                this.getItem().is(ModItems.EGO_STONE_VITALITY) ||
                this.getItem().is(ModItems.EGO_STONE_WRATH) ||
                this.getItem().is(ModItems.EGO_STONE_AEGIS) ||
                this.getItem().is(ModItems.EGO_STONE_AGILITY) ||
                this.getItem().is(ModItems.EGO_STONE_SPARK)
        ) {
            cir.setReturnValue(0.0D);
        }
    }
}
