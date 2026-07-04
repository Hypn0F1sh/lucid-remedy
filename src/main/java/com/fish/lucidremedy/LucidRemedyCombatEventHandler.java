package com.fish.lucidremedy;


import com.fish.lucidremedy.attribute.ModAttributes;
import com.fish.lucidremedy.effect.ModEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.event.entity.EntityAttributeModificationEvent;
import net.neoforged.neoforge.event.entity.living.LivingIncomingDamageEvent;

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
        }
    }


}

