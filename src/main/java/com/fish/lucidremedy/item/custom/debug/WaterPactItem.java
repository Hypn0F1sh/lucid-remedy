package com.fish.lucidremedy.item.custom.debug;

import ca.weblite.objc.Client;
import com.fish.lucidremedy.attribute.ModAttributes;
import com.fish.lucidremedy.powers.pacts.PactUtils;
import com.mojang.authlib.Environment;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.core.SectionPos;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LightLayer;
import net.minecraft.world.level.lighting.LevelLightEngine;
import net.minecraft.world.level.lighting.LightEngine;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.fml.loading.FMLEnvironment;

public class WaterPactItem extends Item {
    public WaterPactItem(Properties properties) {
        super(properties);
    }

    @Override
    public InteractionResult use(Level level, Player player, InteractionHand hand) {
        PactUtils.Toggle(ModAttributes.HAS_WATER_BLINDNESS, "water_blindness", player);
        Minecraft.getInstance().levelRenderer.allChanged();
        return super.use(level, player, hand);
    }
}
