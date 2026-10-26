package net.exerion.progression;

import net.fabricmc.fabric.api.event.player.PlayerBlockBreakEvents;
import net.fabricmc.fabric.api.event.player.UseBlockCallback;
import net.fabricmc.fabric.api.event.player.UseItemCallback;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.state.BlockState;

public class EraRestrictionHandler {
    public static void registerRestrictions() {
        PlayerBlockBreakEvents.BEFORE.register((world, player, pos, state, blockEntity) -> {
            if (player.isSpectator()) {
                return true;
            }
            TechnologyEra required = EraProgressionRegistry.getRequiredEra(state.getBlock());
            if (!PlayerEraManager.getPlayerEra(player).isAtLeast(required)) {
                if (!world.isClientSide()) {
                    MutableComponent message = Component.translatable("message.exerion.era_locked.block_break", required.getDisplayNameComponent()).withStyle(ChatFormatting.RED);
                    player.sendSystemMessage(message);
                }
                return false;
            }
            return true;
        });

        UseBlockCallback.EVENT.register((player, world, hand, hitResult) -> {
            if (player.isSpectator()) {
                return InteractionResult.PASS;
            }
            BlockState state = world.getBlockState(hitResult.getBlockPos());
            TechnologyEra required = EraProgressionRegistry.getRequiredEra(state.getBlock());
            if (!PlayerEraManager.getPlayerEra(player).isAtLeast(required)) {
                if (!world.isClientSide() && hand == InteractionHand.MAIN_HAND) {
                    MutableComponent message = Component.translatable("message.exerion.era_locked.block_use", required.getDisplayNameComponent()).withStyle(ChatFormatting.RED);
                    player.sendSystemMessage(message);
                }
                return InteractionResult.FAIL;
            }
            return InteractionResult.PASS;
        });

        UseItemCallback.EVENT.register((player, world, hand) -> {
            if (player.isSpectator()) {
                return InteractionResult.PASS;
            }
            ItemStack stack = player.getItemInHand(hand);
            if (!stack.isEmpty()) {
                TechnologyEra required = EraProgressionRegistry.getRequiredEra(stack.getItem());
                if (!PlayerEraManager.getPlayerEra(player).isAtLeast(required)) {
                    if (!world.isClientSide() && hand == InteractionHand.MAIN_HAND) {
                        MutableComponent message = Component.translatable("message.exerion.era_locked.item_use", required.getDisplayNameComponent()).withStyle(ChatFormatting.RED);
                        player.sendSystemMessage(message);
                    }
                    return InteractionResult.FAIL;
                }
            }
            return InteractionResult.PASS;
        });
    }
}
