package com.fish.lucidremedy;


import com.fish.lucidremedy.attachment.ModAttachments;
import com.fish.lucidremedy.attribute.ModAttributes;
import com.fish.lucidremedy.data.UUIDs;
import com.fish.lucidremedy.item.ModItems;
import com.fish.lucidremedy.tags.ModItemTags;
import net.minecraft.core.Holder;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.util.TriState;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.event.entity.EntityAttributeModificationEvent;
import net.neoforged.neoforge.event.entity.player.ItemEntityPickupEvent;
import net.neoforged.neoforge.event.entity.player.PlayerEvent;
import net.neoforged.neoforge.event.tick.PlayerTickEvent;
import org.jspecify.annotations.NonNull;

import java.lang.reflect.Array;
import java.util.*;

import static java.lang.System.in;

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
    public static void playerClone(PlayerEvent.Clone event) {
        if (event.isWasDeath()) {
            HashSet<Holder<Attribute> > attributes = new HashSet<>();

            attributes.add(ModAttributes.HAS_WATER_BLINDNESS);
            attributes.add(ModAttributes.HAS_PACT_FERAL);
            attributes.add(ModAttributes.HAS_PLANE_SHIFT);

            for (Holder<Attribute> attribute : attributes) {
                var oldInstance = event.getOriginal().getAttribute(attribute);
                if (oldInstance != null) {
                    for (AttributeModifier modifier : oldInstance.getModifiers()) {
                        event.getEntity().getAttribute(attribute).addPermanentModifier(modifier);
                    }
                }
            }
        }
    }

    @SubscribeEvent
    public static void itemPickup(ItemEntityPickupEvent.Pre event) {
        if (event.getItemEntity().getItem().is(ModItemTags.GHOST_ITEM)) {
            Player player = event.getPlayer();
            if (player.getAttributeValue(ModAttributes.HAS_PLANE_SHIFT) > 0) {
                return;
            }
            event.setCanPickup(TriState.FALSE);
        }
    }

    @SubscribeEvent
    public static void playerTick(PlayerTickEvent.Post event) {
        int cooldown = event.getEntity().getData(ModAttachments.LUCKY_COOLDOWN);
        if (cooldown > 0) {
            event.getEntity().setData(ModAttachments.LUCKY_COOLDOWN, cooldown - 1);
        }
    }

    @SubscribeEvent
    public static void onPlayerJoin(PlayerEvent.PlayerLoggedInEvent event) {
        Player player = event.getEntity();
        if (player.level().isClientSide()) return;

        CompoundTag persistentData = player.getPersistentData();
        Optional<CompoundTag> persistentCustomDataOptional = persistentData.getCompound(Player.PERSISTED_NBT_TAG);
        CompoundTag persistentCustomData = persistentCustomDataOptional.orElse(new CompoundTag());

        if (!persistentCustomData.getBoolean("FirstSpawnItemsGiven").orElse(false)) {
            List<ItemStack> startingItems = new ArrayList<>();
            if (player.getUUID() == UUIDs.LUKEIEST) {
                startingItems.add(new ItemStack(ModItems.LUKES_CLOVER.get(), 1));
            }
            for (ItemStack startingItem : startingItems){
                if (!player.getInventory().add(startingItem)) {
                    player.drop(startingItem, false);
                }
            }

            persistentCustomData.putBoolean("FirstSpawnItemsGiven", true);
            persistentData.put(Player.PERSISTED_NBT_TAG, persistentCustomData);
        }
    }
}

