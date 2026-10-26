/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.brigadier.CommandDispatcher
 *  net.fabricmc.api.ModInitializer
 *  net.fabricmc.fabric.api.biome.v1.BiomeModifications
 *  net.fabricmc.fabric.api.biome.v1.BiomeSelectors
 *  net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback
 *  net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents
 *  net.fabricmc.fabric.api.networking.v1.ServerPlayConnectionEvents
 *  net.minecraft.commands.CommandSourceStack
 *  net.minecraft.core.BlockPos
 *  net.minecraft.core.component.DataComponents
 *  net.minecraft.core.registries.Registries
 *  net.minecraft.nbt.CompoundTag
 *  net.minecraft.network.chat.Component
 *  net.minecraft.resources.Identifier
 *  net.minecraft.resources.ResourceKey
 *  net.minecraft.server.level.ServerPlayer
 *  net.minecraft.sounds.SoundEvent
 *  net.minecraft.sounds.SoundEvents
 *  net.minecraft.sounds.SoundSource
 *  net.minecraft.world.effect.MobEffectInstance
 *  net.minecraft.world.effect.MobEffects
 *  net.minecraft.world.entity.EquipmentSlot
 *  net.minecraft.world.entity.player.Player
 *  net.minecraft.world.item.ItemStack
 *  net.minecraft.world.item.component.CustomData
 *  net.minecraft.world.level.block.entity.BlockEntity
 *  net.minecraft.world.level.levelgen.GenerationStep$Decoration
 *  net.minecraft.world.phys.BlockHitResult
 *  net.minecraft.world.phys.HitResult$Type
 *  org.slf4j.Logger
 *  org.slf4j.LoggerFactory
 */
package net.exerion;

import com.mojang.brigadier.CommandDispatcher;
import java.util.function.Predicate;
import net.exerion.block.ModBlocks;
import net.exerion.block.entity.CoalBoilerBlockEntity;
import net.exerion.block.entity.ModBlockEntityTypes;
import net.exerion.command.ExerionCommand;
import net.exerion.energy.IEnergyStorage;
import net.exerion.item.ModCreativeModeTabs;
import net.exerion.item.ModItems;
import net.exerion.menu.ModMenuTypes;
import net.exerion.network.ExerionNetwork;
import net.exerion.progression.EraProgressionRegistry;
import net.exerion.progression.ModAttachments;
import net.exerion.progression.PlayerEraManager;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.fabricmc.fabric.api.networking.v1.ServerPlayConnectionEvents;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.core.BlockPos;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.registries.Registries;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.component.CustomData;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.HitResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Exerion
implements ModInitializer {
    public static final String MOD_ID = "exerion";
    public static final Logger LOGGER = LoggerFactory.getLogger((String)"exerion");

    public void onInitialize() {
        LOGGER.info("Initializing Exerion Mod...");
        ModItems.registerModItems();
        ModBlocks.registerModBlocks();
        ModBlockEntityTypes.registerBlockEntityTypes();
        ModMenuTypes.registerMenuTypes();
        ModCreativeModeTabs.registerCreativeModeTabs();
        ModAttachments.registerAttachments();
        EraProgressionRegistry.init();
        ExerionNetwork.registerServer();
        CommandRegistrationCallback.EVENT.register((dispatcher, registryAccess, environment) -> ExerionCommand.register((CommandDispatcher<CommandSourceStack>)dispatcher));
        ServerPlayConnectionEvents.JOIN.register((handler, sender, server) -> ExerionNetwork.syncEraToClient(handler.getPlayer(), PlayerEraManager.getPlayerEra((Player)handler.getPlayer())));
        ServerTickEvents.START_SERVER_TICK.register(server -> {
            for (ServerPlayer player : server.getPlayerList().getPlayers()) {
                int fallBlocks;
                ItemStack boots;
                ItemStack plate;
                ItemStack helmet = player.getItemBySlot(EquipmentSlot.HEAD);
                if (!helmet.isEmpty() && helmet.is((Object)ModItems.LOGIC_HELMET)) {
                    if (!helmet.has(DataComponents.CUSTOM_DATA) || !((CustomData)helmet.get(DataComponents.CUSTOM_DATA)).copyTag().contains("exerion_init")) {
                        helmet.setDamageValue(helmet.getMaxDamage());
                        CompoundTag tag = helmet.has(DataComponents.CUSTOM_DATA) ? ((CustomData)helmet.get(DataComponents.CUSTOM_DATA)).copyTag() : new CompoundTag();
                        tag.putBoolean("exerion_init", true);
                        helmet.set(DataComponents.CUSTOM_DATA, (Object)CustomData.of((CompoundTag)tag));
                    }
                    if (helmet.getDamageValue() < helmet.getMaxDamage()) {
                        BlockHitResult hit;
                        if (player.tickCount % 80 == 0) {
                            player.addEffect(new MobEffectInstance(MobEffects.NIGHT_VISION, 220, 0, false, false, false));
                            helmet.setDamageValue(Math.min(helmet.getMaxDamage(), helmet.getDamageValue() + 1));
                        }
                        if (player.tickCount % 4 == 0 && (hit = (BlockHitResult)player.pick(6.0, 1.0f, false)).getType() == HitResult.Type.BLOCK) {
                            BlockPos targetPos = hit.getBlockPos();
                            BlockEntity be = player.level().getBlockEntity(targetPos);
                            if (be != null) {
                                String msg = null;
                                if (be instanceof IEnergyStorage) {
                                    IEnergyStorage energyStorage = (IEnergyStorage)be;
                                    msg = "\u00a7b[HUD] " + be.getBlockState().getBlock().getName().getString() + " \u00a77| \u00a7eEnergia: \u00a7f" + energyStorage.getEnergy() + " / " + energyStorage.getMaxEnergy() + " EU";
                                } else if (be instanceof CoalBoilerBlockEntity) {
                                    CoalBoilerBlockEntity boiler = (CoalBoilerBlockEntity)be;
                                    boolean isBurning = boiler.getBurnTime() > 0;
                                    msg = "\u00a7b[HUD] Caldeira a Carv\u00e3o \u00a77| \u00a7eCombust\u00e3o: " + (isBurning ? "\u00a7aA Queimar" : "\u00a7cDesligada");
                                }
                                if (msg != null) {
                                    player.sendOverlayMessage((Component)Component.literal(msg));
                                }
                            }
                        }
                    }
                }
                if (!(plate = player.getItemBySlot(EquipmentSlot.CHEST)).isEmpty() && plate.is((Object)ModItems.ELECTRIC_PLATE)) {
                    if (!plate.has(DataComponents.CUSTOM_DATA) || !((CustomData)plate.get(DataComponents.CUSTOM_DATA)).copyTag().contains("exerion_init")) {
                        plate.setDamageValue(plate.getMaxDamage());
                        CompoundTag tag = plate.has(DataComponents.CUSTOM_DATA) ? ((CustomData)plate.get(DataComponents.CUSTOM_DATA)).copyTag() : new CompoundTag();
                        tag.putBoolean("exerion_init", true);
                        plate.set(DataComponents.CUSTOM_DATA, (Object)CustomData.of((CompoundTag)tag));
                    }
                    if (plate.getDamageValue() < plate.getMaxDamage()) {
                        if (player.hurtTime == 10) {
                            player.addEffect(new MobEffectInstance(MobEffects.ABSORPTION, 80, 1, false, false, true));
                            plate.setDamageValue(Math.min(plate.getMaxDamage(), plate.getDamageValue() + 50));
                            player.level().playSound(null, player.getX(), player.getY(), player.getZ(), (SoundEvent)SoundEvents.SHIELD_BLOCK.value(), SoundSource.PLAYERS, 0.6f, 1.2f);
                        }
                        if (player.tickCount % 2 == 0) {
                            for (int i = 0; i < player.getInventory().getContainerSize(); ++i) {
                                ItemStack invStack = player.getInventory().getItem(i);
                                if (invStack.isEmpty() || invStack == plate || !invStack.is((Object)ModItems.ERA_SCANNER) && !invStack.is((Object)ModItems.ELECTRIC_MINING_DRILL) || invStack.getDamageValue() <= 0) continue;
                                invStack.setDamageValue(invStack.getDamageValue() - 1);
                                plate.setDamageValue(Math.min(plate.getMaxDamage(), plate.getDamageValue() + 1));
                                break;
                            }
                        }
                    }
                }
                if ((boots = player.getItemBySlot(EquipmentSlot.FEET)).isEmpty() || !boots.is((Object)ModItems.STEAM_BOOTS)) continue;
                if (!boots.has(DataComponents.CUSTOM_DATA) || !((CustomData)boots.get(DataComponents.CUSTOM_DATA)).copyTag().contains("exerion_init")) {
                    boots.setDamageValue(boots.getMaxDamage());
                    CompoundTag tag = boots.has(DataComponents.CUSTOM_DATA) ? ((CustomData)boots.get(DataComponents.CUSTOM_DATA)).copyTag() : new CompoundTag();
                    tag.putBoolean("exerion_init", true);
                    boots.set(DataComponents.CUSTOM_DATA, (Object)CustomData.of((CompoundTag)tag));
                }
                if (boots.getDamageValue() >= boots.getMaxDamage() || !(player.fallDistance > 0.0) || (fallBlocks = (int)player.fallDistance) <= 0) continue;
                player.fallDistance = 0.0;
                boots.setDamageValue(Math.min(boots.getMaxDamage(), boots.getDamageValue() + fallBlocks * 3));
                player.level().playSound(null, player.getX(), player.getY(), player.getZ(), SoundEvents.FIRE_EXTINGUISH, SoundSource.PLAYERS, 0.5f, 1.5f);
            }
        });
        BiomeModifications.addFeature((Predicate)BiomeSelectors.foundInOverworld(), (GenerationStep.Decoration)GenerationStep.Decoration.UNDERGROUND_ORES, (ResourceKey)ResourceKey.create((ResourceKey)Registries.PLACED_FEATURE, (Identifier)Identifier.fromNamespaceAndPath((String)MOD_ID, (String)"exerion_ore")));
        LOGGER.info("Exerion Mod initialized successfully!");
    }
}

