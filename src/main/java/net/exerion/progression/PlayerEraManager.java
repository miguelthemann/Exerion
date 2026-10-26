package net.exerion.progression;

import net.exerion.network.ExerionNetwork;
import net.exerion.progression.ModAttachments;
import net.exerion.progression.TechnologyEra;
import net.minecraft.ChatFormatting;
import net.minecraft.advancements.AdvancementHolder;
import net.minecraft.advancements.AdvancementProgress;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.resources.Identifier;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;

public class PlayerEraManager {
    public static TechnologyEra getPlayerEra(Player player) {
        if (player.isCreative() || player.isSpectator()) {
            return TechnologyEra.COSMIC;
        }
        if (player.hasAttached(ModAttachments.PLAYER_ERA)) {
            return player.getAttached(ModAttachments.PLAYER_ERA);
        }
        return TechnologyEra.STONE;
    }

    public static void setPlayerEra(ServerPlayer player, TechnologyEra era) {
        player.setAttached(ModAttachments.PLAYER_ERA, era);
        ExerionNetwork.syncEraToClient(player, era);
        MutableComponent message = Component.translatable("message.exerion.era_unlocked", era.getDisplayNameComponent()).withStyle(ChatFormatting.GREEN);
        player.sendSystemMessage(message);
        String eraName = era.name().toLowerCase();
        Identifier advId = Identifier.fromNamespaceAndPath("exerion", "era/" + eraName);
        AdvancementHolder adv = player.level().getServer().getAdvancements().get(advId);
        if (adv != null) {
            AdvancementProgress progress = player.getAdvancements().getOrStartProgress(adv);
            for (String criterion : progress.getRemainingCriteria()) {
                player.getAdvancements().award(adv, criterion);
            }
        }
    }
}

