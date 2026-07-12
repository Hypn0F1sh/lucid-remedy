package com.fish.lucidremedy;


import com.fish.lucidremedy.attribute.ModAttributes;
import com.fish.lucidremedy.effect.ModEffects;
import com.fish.lucidremedy.item.ModItems;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.event.entity.EntityAttributeModificationEvent;
import net.neoforged.neoforge.event.entity.living.LivingDamageEvent;
import net.neoforged.neoforge.event.entity.living.LivingIncomingDamageEvent;
import net.neoforged.neoforge.event.entity.player.AttackEntityEvent;

import java.util.Objects;

@Mod(LucidRemedy.MODID)
@EventBusSubscriber
public class LucidRemedyCombatEventHandler {

    @SubscribeEvent
    public static void onAttackerHitEnemy(LivingIncomingDamageEvent event) {

        LivingEntity victim = event.getEntity();

        Entity attackerEntity = event.getSource().getEntity();

        if (attackerEntity instanceof LivingEntity attacker) {

            if (attacker.hasEffect(ModEffects.TEMPER_RETCH_EFFECT)) {
                victim.igniteForTicks(60);
                attacker.igniteForTicks(40);
            }

            if (event.getSource().getWeaponItem() != null) {
                if (event.getSource().getWeaponItem().is(ModItems.EPIDEMIC_SCALPEL)) {
                    victim.invulnerableTime = 1;
                }
            }
        }
    }

    @SubscribeEvent
    public static void onDamage(LivingDamageEvent.Pre event) {
        if (event.getEntity().hasEffect(ModEffects.EVASIUM_EFFECT)) {
            event.getContainer().setNewDamage(0.0f);
        }
    }

    @SubscribeEvent
    public static void onAttack(AttackEntityEvent event) {
        event.setCanceled(event.getEntity().hasEffect(ModEffects.EVASIUM_EFFECT));
    }
}

