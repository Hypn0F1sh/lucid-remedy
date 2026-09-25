package com.fish.lucidremedy;

import com.fish.lucidremedy.powers.QuantumTunnel;
import net.minecraft.client.Minecraft;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.MinecartItem;
import net.minecraft.world.level.Level;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.neoforge.client.event.ClientTickEvent;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;
import net.neoforged.neoforge.client.event.RegisterKeyMappingsEvent;
import net.neoforged.neoforge.client.event.RenderLivingEvent;
import net.neoforged.neoforge.client.gui.ConfigurationScreen;
import net.neoforged.neoforge.client.gui.IConfigScreenFactory;
import net.neoforged.neoforge.event.server.ServerAboutToStartEvent;

import java.io.Console;

// This class will not load on dedicated servers. Accessing client side code from here is safe.
@Mod(value = LucidRemedy.MODID, dist = Dist.CLIENT)
// You can use EventBusSubscriber to automatically register all static methods in the class annotated with @SubscribeEvent
@EventBusSubscriber(modid = LucidRemedy.MODID, value = Dist.CLIENT)
public class LucidRemedyClient {
    public LucidRemedyClient(ModContainer container) {
        // Allows NeoForge to create a config screen for this mod's configs.
        // The config screen is accessed by going to the Mods screen > clicking on your mod > clicking on config.
        // Do not forget to add translations for your config options to the en_us.json file.
        container.registerExtensionPoint(IConfigScreenFactory.class, ConfigurationScreen::new);
    }

    public static boolean ACTIVATE_KEY_WAS_DOWN;

    @SubscribeEvent
    static void onClientSetup(FMLClientSetupEvent event) {
        // Some client setup code
        LucidRemedy.LOGGER.info("HELLO FROM CLIENT SETUP");
        LucidRemedy.LOGGER.info("MINECRAFT NAME >> {}", Minecraft.getInstance().getUser().getName());
    }

    @SubscribeEvent
    public static void registerBindings(RegisterKeyMappingsEvent event) {
        ModKeys.register(event);
    }

    @SubscribeEvent // on the game event bus only on the physical client
    public static void onClientTick(ClientTickEvent.Post event) {
        Minecraft minecraft = Minecraft.getInstance();
        Player player = minecraft.player;
        if (player != null) {
            if (ModKeys.ACTIVATE.isDown()) {
                com.fish.lucidremedy.LucidRemedyClient.ACTIVATE_KEY_WAS_DOWN = true;
            } else {
                if (ACTIVATE_KEY_WAS_DOWN) {
                    if (player != null) {
                        Level level = player.level();
                        QuantumTunnel.Tunnel(player, level);
                    }
                }
                ACTIVATE_KEY_WAS_DOWN = false;
            }
            if (player.getMainHandItem() == ItemStack.EMPTY) {
                if (minecraft.options.keyUse.isDown()) {

                }
            }
        }
    }
}
