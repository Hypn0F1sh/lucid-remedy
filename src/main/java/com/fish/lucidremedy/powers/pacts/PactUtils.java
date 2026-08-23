package com.fish.lucidremedy.powers.pacts;

import com.fish.lucidremedy.LucidRemedy;
import com.fish.lucidremedy.attribute.ModAttributes;
import net.minecraft.core.Holder;
import net.minecraft.resources.Identifier;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.player.Player;

import java.util.Objects;

public class PactUtils {

    public static void Grant(Holder<Attribute> attribute, String id, Player player) {
        AttributeInstance attributeInstance = player.getAttribute(attribute);
        assert attributeInstance != null;
        AttributeModifier modifier = new AttributeModifier(
                Identifier.fromNamespaceAndPath(LucidRemedy.MODID, id),
                1.0D,
                AttributeModifier.Operation.ADD_VALUE
        );
        attributeInstance.addPermanentModifier(modifier);
    }


    public static void Revoke(Holder<Attribute> attribute, String id, Player player) {
        AttributeInstance attributeInstance = player.getAttribute(attribute);
        assert attributeInstance != null;
        attributeInstance.removeModifier(Identifier.fromNamespaceAndPath(LucidRemedy.MODID, id));
    }

    public static void Toggle(Holder<Attribute> attribute, String id, Player player) {
        if (Objects.requireNonNull(player.getAttribute(attribute)).getValue() > 0) {
            Revoke(attribute, id, player);
        } else {
            Grant(attribute, id, player);
        }
    }
}
