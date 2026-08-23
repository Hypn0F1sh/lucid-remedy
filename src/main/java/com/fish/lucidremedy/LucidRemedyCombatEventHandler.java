package com.fish.lucidremedy;


import com.fish.lucidremedy.attribute.ModAttributes;
import com.fish.lucidremedy.effect.ModEffects;
import com.fish.lucidremedy.item.ModItems;
import com.fish.lucidremedy.powers.pacts.PactUtils;
import com.lowdragmc.photon.client.fx.EntityEffectExecutor;
import com.lowdragmc.photon.client.fx.FX;
import com.lowdragmc.photon.client.fx.FXHelper;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.resources.Identifier;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageType;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.common.NeoForgeMod;
import net.neoforged.neoforge.common.Tags;
import net.neoforged.neoforge.event.entity.living.LivingDamageEvent;
import net.neoforged.neoforge.event.entity.living.LivingEntityUseItemEvent;
import net.neoforged.neoforge.event.entity.living.LivingIncomingDamageEvent;
import net.neoforged.neoforge.event.entity.player.AttackEntityEvent;

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
        if (event.getEntity().hasEffect(ModEffects.INFECTION_EFFECT)){
            float mult = (float) ((event.getEntity().getEffect(ModEffects.INFECTION_EFFECT).getAmplifier() + 1) * 0.1);
            float damage = event.getContainer().getNewDamage();
            event.getContainer().setNewDamage(damage * mult + 1);
            event.getEntity().removeEffect(ModEffects.INFECTION_EFFECT);
        }
    }

    @SubscribeEvent
    public static void onAttack(AttackEntityEvent event) {
        event.setCanceled(event.getEntity().hasEffect(ModEffects.EVASIUM_EFFECT));
        if (event.getEntity() instanceof Player player) {
            if (player.getAttributeValue(ModAttributes.HAS_PACT_FERAL) > 0) {
                if (event.getTarget() instanceof LivingEntity target) {
                    FX fx = FXHelper.getFX(Identifier.fromNamespaceAndPath("lucidremedy", "slash"));
                    new EntityEffectExecutor(fx, target.level(), target, EntityEffectExecutor.AutoRotate.NONE).start();
                    target.forceAddEffect(new MobEffectInstance(
                            ModEffects.BLEEDING_EFFECT,
                            100
                    ), player);
                }
            }
        }
    }

    @SubscribeEvent
    public static void onUse(LivingEntityUseItemEvent event) {

    }

    @SubscribeEvent
    public static void onUseEnd(LivingEntityUseItemEvent.Finish event) {
        if (event.getEntity() instanceof Player player) {
            if (player.getAttributeValue(ModAttributes.HAS_PACT_FERAL) > 0) {
                if (    event.getItem().is(Tags.Items.FOODS) &&
                        !event.getItem().is(Tags.Items.FOODS_RAW_MEAT) &&
                        !event.getItem().is(Tags.Items.FOODS_COOKED_MEAT) &&
                        !event.getItem().is(Tags.Items.DRINKS)
                ) {
                    MinecraftServer server = player.level().getServer();
                    if (server != null) {
                        ServerLevel serverLevel = server.getLevel(player.level().dimension());
                        if (serverLevel != null) {
                            Registry<DamageType> dTypeReg = player.damageSources().damageTypes;
                            Holder.Reference<DamageType> dType = (Holder.Reference)dTypeReg.get(DamageTypes.MAGIC).orElse(dTypeReg.getOrThrow(DamageTypes.MAGIC));
                            player.hurtServer(serverLevel, new DamageSource(dType), 10.0F);
                        }
                    }
                    PactUtils.Revoke(ModAttributes.HAS_PACT_FERAL, "feral_pact", player);
                }
            }
        }
    }
}

