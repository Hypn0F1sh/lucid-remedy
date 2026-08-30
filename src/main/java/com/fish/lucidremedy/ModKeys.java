package com.fish.lucidremedy;

import com.mojang.blaze3d.platform.InputConstants;
import net.minecraft.client.KeyMapping;
import net.minecraft.resources.Identifier;
import net.neoforged.neoforge.client.event.RegisterKeyMappingsEvent;
import net.neoforged.neoforge.client.settings.KeyConflictContext;
import org.lwjgl.glfw.GLFW;

public class ModKeys {

    public static KeyMapping.Category LUCIDREMEDY_CATEGORY = new KeyMapping.Category(Identifier.fromNamespaceAndPath(LucidRemedy.MODID, "category"));

    public static final KeyMapping ACTIVATE = new KeyMapping(
            "key.lucidremedy.activate",
            KeyConflictContext.IN_GAME,
            InputConstants.Type.KEYSYM,
            GLFW.GLFW_KEY_R,
            LUCIDREMEDY_CATEGORY
    );

    public static void register(RegisterKeyMappingsEvent event) {
        event.registerCategory(LUCIDREMEDY_CATEGORY);
        event.register(ACTIVATE);
    }
}
