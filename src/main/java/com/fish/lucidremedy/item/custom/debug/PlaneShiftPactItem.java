package com.fish.lucidremedy.item.custom.debug;

import com.fish.lucidremedy.attribute.ModAttributes;
import com.fish.lucidremedy.powers.pacts.PactUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;

public class PlaneShiftPactItem extends Item {
    public PlaneShiftPactItem(Properties properties) {
        super(properties);
    }

    @Override
    public InteractionResult use(Level level, Player player, InteractionHand hand) {
        PactUtils.Toggle(ModAttributes.HAS_PLANE_SHIFT, "plane_shift", player);
        Minecraft.getInstance().levelRenderer.allChanged();
        return super.use(level, player, hand);
    }
}
