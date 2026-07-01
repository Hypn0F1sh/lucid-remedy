package com.fish.lucidremedy.food;

import com.fish.lucidremedy.effect.ModEffects;
import net.minecraft.core.Holder;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.ItemUseAnimation;
import net.minecraft.world.item.component.Consumable;
import net.minecraft.world.item.component.Consumables;
import net.minecraft.world.item.consume_effects.ApplyStatusEffectsConsumeEffect;
import net.neoforged.neoforge.common.data.SoundDefinition;

import java.util.List;

public class ModFoods {

    public static final FoodProperties LUCID_REMEDY = new FoodProperties.Builder().nutrition(0).saturationModifier(0).alwaysEdible().build();
    public static final Consumable LUCID_REMEDY_CONSUMABLE = Consumables.defaultDrink().consumeSeconds(3).hasConsumeParticles(false)
            .animation(ItemUseAnimation.DRINK)
            .sound(SoundEvents.HONEY_DRINK)
            .onConsume(new ApplyStatusEffectsConsumeEffect(
                    List.of(new MobEffectInstance(ModEffects.NONLUCID_EFFECT,600,1))
            )).build();
}
