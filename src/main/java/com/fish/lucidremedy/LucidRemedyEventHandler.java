package com.fish.lucidremedy;


import com.fish.lucidremedy.attribute.ModAttributes;
import com.fish.lucidremedy.effect.ModEffects;
import com.fish.lucidremedy.item.ModItems;
import net.minecraft.world.entity.EntityType;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.event.entity.EntityAttributeModificationEvent;
import net.neoforged.neoforge.event.entity.living.LivingDamageEvent;

import java.util.Objects;

@Mod(LucidRemedy.MODID)
@EventBusSubscriber
public class LucidRemedyEventHandler {

    @SubscribeEvent // on the mod event bus
    public static void modifyDefaultAttributes(EntityAttributeModificationEvent event) {
        event.add(
                EntityType.PLAYER,
                ModAttributes.NONLUCID
        );
    }
}

