/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.fabricmc.fabric.api.event.player.PlayerBlockBreakEvents
 *  net.fabricmc.fabric.api.event.player.UseBlockCallback
 *  net.fabricmc.fabric.api.event.player.UseItemCallback
 *  net.minecraft.ChatFormatting
 *  net.minecraft.network.chat.Component
 *  net.minecraft.network.chat.MutableComponent
 *  net.minecraft.world.InteractionHand
 *  net.minecraft.world.InteractionResult
 *  net.minecraft.world.item.ItemStack
 *  net.minecraft.world.level.block.state.BlockState
 */
package net.exerion.progression;

import net.exerion.progression.EraProgressionRegistry;
import net.exerion.progression.TechnologyEra;
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
            BlockState blockState = world.getBlockState(pos);
            if (!EraProgressionRegistry.hasUnlockedRequiredEra(player, blockState.getBlock())) {
                if (!world.isClientSide()) {
                    TechnologyEra required = EraProgressionRegistry.getRequiredEra(blockState.getBlock());
                    MutableComponent message = Component.translatable((String)"message.exerion.era_locked.block_break", (Object[])new Object[]{required.getDisplayNameComponent()}).withStyle(ChatFormatting.RED);
                    player.sendSystemMessage((Component)message);
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
            if (!EraProgressionRegistry.hasUnlockedRequiredEra(player, state.getBlock())) {
                if (!world.isClientSide() && hand == InteractionHand.MAIN_HAND) {
                    TechnologyEra required = EraProgressionRegistry.getRequiredEra(state.getBlock());
                    MutableComponent message = Component.translatable((String)"message.exerion.era_locked.block_use", (Object[])new Object[]{required.getDisplayNameComponent()}).withStyle(ChatFormatting.RED);
                    player.sendSystemMessage((Component)message);
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
            if (!stack.isEmpty() && !EraProgressionRegistry.hasUnlockedRequiredEra(player, stack.getItem())) {
                if (!world.isClientSide() && hand == InteractionHand.MAIN_HAND) {
                    TechnologyEra required = EraProgressionRegistry.getRequiredEra(stack.getItem());
                    MutableComponent message = Component.translatable((String)"message.exerion.era_locked.item_use", (Object[])new Object[]{required.getDisplayNameComponent()}).withStyle(ChatFormatting.RED);
                    player.sendSystemMessage((Component)message);
                }
                return InteractionResult.FAIL;
            }
            return InteractionResult.PASS;
        });
    }
}

