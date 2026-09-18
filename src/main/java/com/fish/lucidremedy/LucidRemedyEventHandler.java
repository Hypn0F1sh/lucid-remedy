package com.fish.lucidremedy;


import com.fish.lucidremedy.attribute.ModAttributes;
import net.minecraft.core.Holder;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.event.entity.EntityAttributeModificationEvent;
import net.neoforged.neoforge.event.entity.player.PlayerEvent;
import org.jspecify.annotations.NonNull;

import java.lang.reflect.Array;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

@Mod(LucidRemedy.MODID)
@EventBusSubscriber
public class LucidRemedyEventHandler {

    @SubscribeEvent // on the mod event bus
    public static void modifyDefaultAttributes(EntityAttributeModificationEvent event) {
        event.add(
                EntityType.PLAYER,
                ModAttributes.HAS_PACT_FERAL
        );
        event.add(
                EntityType.PLAYER,
                ModAttributes.HAS_WATER_BLINDNESS
        );
        event.add(
                EntityType.PLAYER,
                ModAttributes.HAS_PLANE_SHIFT
        );
    }

    @SubscribeEvent
    public static void playerDeath(PlayerEvent.Clone event) {
        if (event.isWasDeath()) {
            HashSet<Holder<Attribute> > attributes = new HashSet<>();

            attributes.add(ModAttributes.HAS_WATER_BLINDNESS);
            attributes.add(ModAttributes.HAS_PACT_FERAL);

            for (Holder<Attribute> attribute : attributes) {
                var oldInstance = event.getOriginal().getAttribute(attribute);
                if (oldInstance != null) {
                    for (AttributeModifier modifier : oldInstance.getModifiers()) {
                        event.getEntity().getAttribute(ModAttributes.HAS_WATER_BLINDNESS).addPermanentModifier(modifier);
                    }
                }
            }
        }
    }
}

