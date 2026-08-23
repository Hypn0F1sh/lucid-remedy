package com.fish.lucidremedy.item.custom.debug;

import com.fish.lucidremedy.attribute.ModAttributes;
import com.fish.lucidremedy.powers.pacts.PactUtils;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;

public class FeralPactItem extends Item {
    public FeralPactItem(Properties properties) {
        super(properties);
    }

    @Override
    public InteractionResult use(Level level, Player player, InteractionHand hand) {
        PactUtils.Toggle(ModAttributes.HAS_PACT_FERAL, "feral_pact", player);
        return super.use(level, player, hand);
    }
}
