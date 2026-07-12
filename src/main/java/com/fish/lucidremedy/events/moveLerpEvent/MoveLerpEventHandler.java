package com.fish.lucidremedy.events.moveLerpEvent;

import com.fish.lucidremedy.LucidRemedy;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.entity.living.LivingDamageEvent;
import net.neoforged.neoforge.event.entity.player.AttackEntityEvent;
import net.neoforged.neoforge.event.level.BlockEvent;
import net.neoforged.neoforge.event.tick.EntityTickEvent;
import net.neoforged.neoforge.event.tick.PlayerTickEvent;
import org.apache.commons.lang3.ObjectUtils;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Mod(LucidRemedy.MODID)
@EventBusSubscriber
public class MoveLerpEventHandler {

    public MoveLerpEventHandler() {
        NeoForge.EVENT_BUS.register(MoveLerpEventHandler.class);
    }

    public static final Map<UUID, LerpMovementTask> ACTIVE_LERP = new HashMap<>();

    public static final Map<UUID, Boolean> MOVING = new HashMap<>();

    @SubscribeEvent
    public static void onEntityLerpMoveEvent(EntityLerpMoveEvent event) {

        Entity entity = event.getEntity();

        LerpMovementTask task = new LerpMovementTask(
                event.getStart(),
                event.getEnd(),
                event.getDuration()
        );

        ACTIVE_LERP.put(entity.getUUID(), task);
    }

    @SubscribeEvent
    public static void onEntityTickEvent(EntityTickEvent.Post event) {

        Entity entity = event.getEntity();

        MOVING.remove(entity.getUUID());

        LerpMovementTask task = ACTIVE_LERP.get(entity.getUUID());
        if (task == null) return;

        MOVING.put(entity.getUUID(), MoveEntityLerp.Move(entity, task.start, task.end, task.duration, task.elapsed));

        if (Math.abs(task.duration - task.elapsed) <= 0.5) {
            ACTIVE_LERP.remove(entity.getUUID());
        }

        if (task.duration - task.elapsed <= 20) {
            task.elapsed += (float) ((task.duration - task.elapsed)*0.05);
        } else task.elapsed++;

    }

    @SubscribeEvent
    public static void onBlockBreak(BlockEvent.BreakEvent event) {
        event.setCanceled(MOVING.get(event.getPlayer().getUUID()));
    }

    @SubscribeEvent
    public static void onBlockPlace(BlockEvent.EntityPlaceEvent event) {
        assert event.getEntity() != null;
        event.setCanceled(MOVING.get(event.getEntity().getUUID()));
    }

    @SubscribeEvent
    public static void onAttack(AttackEntityEvent event) {
        event.setCanceled(MOVING.get(event.getEntity().getUUID()));
    }

    @SubscribeEvent
    public static void onDamage(LivingDamageEvent.Pre event) {
        if (MOVING.get(event.getEntity().getUUID()) != null) {
            if (MOVING.get(event.getEntity().getUUID())) {
                event.setNewDamage(0);
            }
        }
    }
}
