package com.fish.lucidremedy;


import com.fish.lucidremedy.attribute.ModAttributes;
import net.minecraft.world.entity.EntityType;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.event.entity.EntityAttributeModificationEvent;

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

