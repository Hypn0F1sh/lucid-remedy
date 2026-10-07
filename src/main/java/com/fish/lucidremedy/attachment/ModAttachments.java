package com.fish.lucidremedy.attachment;

import com.fish.lucidremedy.LucidRemedy;
import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.attachment.AttachmentType;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.NeoForgeRegistries;

import java.util.function.Supplier;

public class ModAttachments {
    public static final DeferredRegister<AttachmentType<?>> ATTACHMENTS =
        DeferredRegister.create(NeoForgeRegistries.ATTACHMENT_TYPES, LucidRemedy.MODID);

    public static final Supplier<AttachmentType<Integer>> LUCKY_COOLDOWN =
            ATTACHMENTS.register("lucky_cooldown", () -> AttachmentType.builder(() -> 0)
                    .serialize(Codec.INT.fieldOf("cooldown"))
                    .build());

    public static void register(IEventBus bus) {
        ATTACHMENTS.register(bus);
    }
}
