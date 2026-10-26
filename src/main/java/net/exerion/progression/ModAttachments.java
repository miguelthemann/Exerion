/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.fabricmc.fabric.api.attachment.v1.AttachmentRegistry
 *  net.fabricmc.fabric.api.attachment.v1.AttachmentType
 *  net.minecraft.resources.Identifier
 */
package net.exerion.progression;

import net.exerion.Exerion;
import net.exerion.progression.TechnologyEra;
import net.fabricmc.fabric.api.attachment.v1.AttachmentRegistry;
import net.fabricmc.fabric.api.attachment.v1.AttachmentType;
import net.minecraft.resources.Identifier;

public class ModAttachments {
    public static final AttachmentType<TechnologyEra> PLAYER_ERA = AttachmentRegistry.create((Identifier)Identifier.fromNamespaceAndPath((String)"exerion", (String)"player_era"), builder -> builder.initializer(() -> TechnologyEra.STONE).persistent(TechnologyEra.CODEC).copyOnDeath());

    public static void registerAttachments() {
        Exerion.LOGGER.info("Registering Entity Attachments for Exerion Mod...");
    }
}

