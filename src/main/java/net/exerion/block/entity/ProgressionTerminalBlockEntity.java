/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.core.BlockPos
 *  net.minecraft.network.chat.Component
 *  net.minecraft.server.level.ServerLevel
 *  net.minecraft.server.level.ServerPlayer
 *  net.minecraft.sounds.SoundEvents
 *  net.minecraft.sounds.SoundSource
 *  net.minecraft.world.Container
 *  net.minecraft.world.MenuProvider
 *  net.minecraft.world.SimpleContainer
 *  net.minecraft.world.entity.Entity
 *  net.minecraft.world.entity.item.ItemEntity
 *  net.minecraft.world.entity.player.Inventory
 *  net.minecraft.world.entity.player.Player
 *  net.minecraft.world.inventory.AbstractContainerMenu
 *  net.minecraft.world.item.ItemStack
 *  net.minecraft.world.item.Items
 *  net.minecraft.world.level.ItemLike
 *  net.minecraft.world.level.Level
 *  net.minecraft.world.level.block.entity.BlockEntity
 *  net.minecraft.world.level.block.state.BlockState
 *  net.minecraft.world.level.storage.ValueInput
 *  net.minecraft.world.level.storage.ValueOutput
 *  org.jetbrains.annotations.Nullable
 */
package net.exerion.block.entity;

import java.util.Objects;
import java.util.UUID;
import net.exerion.block.ModBlocks;
import net.exerion.block.entity.ModBlockEntityTypes;
import net.exerion.item.ModItems;
import net.exerion.menu.ProgressionTerminalMenu;
import net.exerion.network.ExerionNetwork;
import net.exerion.progression.PlayerEraManager;
import net.exerion.progression.TechnologyEra;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.Container;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;
import org.jetbrains.annotations.Nullable;

public class ProgressionTerminalBlockEntity
extends BlockEntity
implements MenuProvider {
    private final SimpleContainer inventory = new SimpleContainer(this, 5){
        final /* synthetic */ ProgressionTerminalBlockEntity this$0;
        {
            ProgressionTerminalBlockEntity progressionTerminalBlockEntity = this$0;
            Objects.requireNonNull(progressionTerminalBlockEntity);
            this.this$0 = progressionTerminalBlockEntity;
            super(arg0);
        }

        public void setChanged() {
            super.setChanged();
            this.this$0.setChanged();
        }
    };
    private UUID lastPlayerUUID = null;

    public ProgressionTerminalBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntityTypes.PROGRESSION_TERMINAL_ENTITY, pos, state);
    }

    public SimpleContainer getInventory() {
        return this.inventory;
    }

    public void setLastPlayerUUID(UUID uuid) {
        this.lastPlayerUUID = uuid;
    }

    public UUID getLastPlayerUUID() {
        return this.lastPlayerUUID;
    }

    public boolean tryAdvanceEra(ServerPlayer player) {
        TechnologyEra currentEra = PlayerEraManager.getPlayerEra((Player)player);
        ServerLevel level = player.level();
        if (currentEra == TechnologyEra.STONE) {
            if (this.hasItems(new ItemStack((ItemLike)Items.RAW_COPPER, 64), new ItemStack((ItemLike)Items.COAL, 64), new ItemStack((ItemLike)Items.RAW_IRON, 64))) {
                this.consumeItems(new ItemStack((ItemLike)Items.RAW_COPPER, 64), new ItemStack((ItemLike)Items.COAL, 64), new ItemStack((ItemLike)Items.RAW_IRON, 64));
                PlayerEraManager.setPlayerEra(player, TechnologyEra.COPPER);
                ExerionNetwork.syncEraToClient(player, TechnologyEra.COPPER);
                level.playSound(null, player.blockPosition(), SoundEvents.PLAYER_LEVELUP, SoundSource.PLAYERS, 1.0f, 1.0f);
                return true;
            }
            player.sendSystemMessage((Component)Component.literal((String)"\u00a7c[Exerion] Not enough resources! Need: 64x Raw Copper + 64x Coal + 64x Raw Iron"));
            return false;
        }
        if (currentEra == TechnologyEra.COPPER) {
            if (this.hasItems(new ItemStack((ItemLike)ModItems.COPPER_GEAR, 64), new ItemStack((ItemLike)Items.IRON_BLOCK, 32), new ItemStack((ItemLike)Items.REDSTONE, 16))) {
                this.consumeItems(new ItemStack((ItemLike)ModItems.COPPER_GEAR, 64), new ItemStack((ItemLike)Items.IRON_BLOCK, 32), new ItemStack((ItemLike)Items.REDSTONE, 16));
                PlayerEraManager.setPlayerEra(player, TechnologyEra.IRON);
                ExerionNetwork.syncEraToClient(player, TechnologyEra.IRON);
                level.playSound(null, player.blockPosition(), SoundEvents.PLAYER_LEVELUP, SoundSource.PLAYERS, 1.0f, 1.1f);
                return true;
            }
            player.sendSystemMessage((Component)Component.literal((String)"\u00a7c[Exerion] Not enough resources! Need: 64x Copper Gear + 32x Iron Block + 16x Redstone Dust"));
            return false;
        }
        if (currentEra == TechnologyEra.IRON) {
            if (this.hasItems(new ItemStack((ItemLike)ModItems.IRON_GEAR, 64), new ItemStack((ItemLike)ModBlocks.SHAFT.asItem(), 32), new ItemStack((ItemLike)ModBlocks.WATER_WHEEL.asItem(), 16))) {
                this.consumeItems(new ItemStack((ItemLike)ModItems.IRON_GEAR, 64), new ItemStack((ItemLike)ModBlocks.SHAFT.asItem(), 32), new ItemStack((ItemLike)ModBlocks.WATER_WHEEL.asItem(), 16));
                PlayerEraManager.setPlayerEra(player, TechnologyEra.MECHANICAL);
                ExerionNetwork.syncEraToClient(player, TechnologyEra.MECHANICAL);
                level.playSound(null, player.blockPosition(), SoundEvents.PLAYER_LEVELUP, SoundSource.PLAYERS, 1.0f, 1.1f);
                return true;
            }
            player.sendSystemMessage((Component)Component.literal((String)"\u00a7c[Exerion] Not enough resources! Need: 64x Iron Gear + 32x Shaft + 16x Water Wheel"));
            return false;
        }
        if (currentEra == TechnologyEra.MECHANICAL) {
            if (this.hasItems(new ItemStack((ItemLike)ModItems.STEEL_INGOT, 64), new ItemStack((ItemLike)ModItems.STEAM_CRYSTAL, 64), new ItemStack((ItemLike)ModBlocks.COAL_BOILER.asItem(), 16))) {
                this.consumeItems(new ItemStack((ItemLike)ModItems.STEEL_INGOT, 64), new ItemStack((ItemLike)ModItems.STEAM_CRYSTAL, 64), new ItemStack((ItemLike)ModBlocks.COAL_BOILER.asItem(), 16));
                PlayerEraManager.setPlayerEra(player, TechnologyEra.STEEL);
                ExerionNetwork.syncEraToClient(player, TechnologyEra.STEEL);
                level.playSound(null, player.blockPosition(), SoundEvents.PLAYER_LEVELUP, SoundSource.PLAYERS, 1.0f, 1.2f);
                return true;
            }
            player.sendSystemMessage((Component)Component.literal((String)"\u00a7c[Exerion] Not enough resources! Need: 64x Steel Ingot + 64x Steam Crystal + 16x Coal Boiler"));
            return false;
        }
        if (currentEra == TechnologyEra.STEEL) {
            if (this.hasItems(new ItemStack((ItemLike)ModItems.CIRCUIT_BOARD, 64), new ItemStack((ItemLike)ModItems.EXERION_PLATE, 64), new ItemStack((ItemLike)ModBlocks.STEEL_CABLE.asItem(), 64))) {
                this.consumeItems(new ItemStack((ItemLike)ModItems.CIRCUIT_BOARD, 64), new ItemStack((ItemLike)ModItems.EXERION_PLATE, 64), new ItemStack((ItemLike)ModBlocks.STEEL_CABLE.asItem(), 64));
                PlayerEraManager.setPlayerEra(player, TechnologyEra.INDUSTRIAL);
                ExerionNetwork.syncEraToClient(player, TechnologyEra.INDUSTRIAL);
                level.playSound(null, player.blockPosition(), SoundEvents.PLAYER_LEVELUP, SoundSource.PLAYERS, 1.0f, 1.2f);
                return true;
            }
            player.sendSystemMessage((Component)Component.literal((String)"\u00a7c[Exerion] Not enough resources! Need: 64x Circuit Board + 64x Exerion Plate + 64x Steel Cable"));
            return false;
        }
        if (currentEra == TechnologyEra.INDUSTRIAL) {
            if (this.hasItems(new ItemStack((ItemLike)ModItems.LOGIC_CHIP, 64), new ItemStack((ItemLike)ModItems.EXERION_GEAR, 64), new ItemStack((ItemLike)ModBlocks.ELECTRIC_GENERATOR.asItem(), 32))) {
                this.consumeItems(new ItemStack((ItemLike)ModItems.LOGIC_CHIP, 64), new ItemStack((ItemLike)ModItems.EXERION_GEAR, 64), new ItemStack((ItemLike)ModBlocks.ELECTRIC_GENERATOR.asItem(), 32));
                PlayerEraManager.setPlayerEra(player, TechnologyEra.DIGITAL);
                ExerionNetwork.syncEraToClient(player, TechnologyEra.DIGITAL);
                level.playSound(null, player.blockPosition(), SoundEvents.PLAYER_LEVELUP, SoundSource.PLAYERS, 1.0f, 1.3f);
                return true;
            }
            player.sendSystemMessage((Component)Component.literal((String)"\u00a7c[Exerion] Not enough resources! Need: 64x Logic Chip + 64x Exerion Gear + 32x Electric Generator"));
            return false;
        }
        if (currentEra == TechnologyEra.DIGITAL) {
            if (this.hasItems(new ItemStack((ItemLike)ModBlocks.EXERION_SOLAR_PANEL.asItem(), 64), new ItemStack((ItemLike)ModItems.LOGIC_CHIP, 64))) {
                this.consumeItems(new ItemStack((ItemLike)ModBlocks.EXERION_SOLAR_PANEL.asItem(), 64), new ItemStack((ItemLike)ModItems.LOGIC_CHIP, 64));
                PlayerEraManager.setPlayerEra(player, TechnologyEra.ADVANCED);
                ExerionNetwork.syncEraToClient(player, TechnologyEra.ADVANCED);
                level.playSound(null, player.blockPosition(), SoundEvents.PLAYER_LEVELUP, SoundSource.PLAYERS, 1.0f, 1.4f);
                return true;
            }
            player.sendSystemMessage((Component)Component.literal((String)"\u00a7c[Exerion] Not enough resources! Need: 64x Solar Panel + 64x Logic Chip"));
            return false;
        }
        if (currentEra == TechnologyEra.ADVANCED) {
            if (this.hasItems(new ItemStack((ItemLike)ModItems.EXERION_CORE, 64), new ItemStack((ItemLike)ModItems.PLASMA_SWORD, 1))) {
                this.consumeItems(new ItemStack((ItemLike)ModItems.EXERION_CORE, 64), new ItemStack((ItemLike)ModItems.PLASMA_SWORD, 1));
                PlayerEraManager.setPlayerEra(player, TechnologyEra.QUANTUM);
                ExerionNetwork.syncEraToClient(player, TechnologyEra.QUANTUM);
                level.playSound(null, player.blockPosition(), SoundEvents.PLAYER_LEVELUP, SoundSource.PLAYERS, 1.0f, 1.5f);
                return true;
            }
            player.sendSystemMessage((Component)Component.literal((String)"\u00a7c[Exerion] Not enough resources! Need: 64x Exerion Core + 1x Plasma Sword"));
            return false;
        }
        if (currentEra == TechnologyEra.QUANTUM) {
            if (this.hasItems(new ItemStack((ItemLike)ModItems.EXERION_CORE, 64))) {
                this.consumeItems(new ItemStack((ItemLike)ModItems.EXERION_CORE, 64));
                PlayerEraManager.setPlayerEra(player, TechnologyEra.COSMIC);
                ExerionNetwork.syncEraToClient(player, TechnologyEra.COSMIC);
                level.playSound(null, player.blockPosition(), SoundEvents.PLAYER_LEVELUP, SoundSource.PLAYERS, 1.0f, 1.6f);
                return true;
            }
            player.sendSystemMessage((Component)Component.literal((String)"\u00a7c[Exerion] Not enough resources! Need: 64x Exerion Core"));
            return false;
        }
        player.sendSystemMessage((Component)Component.literal((String)"\u00a76[Exerion] \u00a7fYou are already at the maximum era: \u00a7dCosmic Age\u00a7f!"));
        return false;
    }

    private boolean hasItems(ItemStack ... required) {
        for (ItemStack req : required) {
            int totalFound = 0;
            for (int i = 0; i < this.inventory.getContainerSize(); ++i) {
                ItemStack slot = this.inventory.getItem(i);
                if (!slot.is((Object)req.getItem())) continue;
                totalFound += slot.getCount();
            }
            if (totalFound >= req.getCount()) continue;
            return false;
        }
        return true;
    }

    private void consumeItems(ItemStack ... toConsume) {
        for (ItemStack req : toConsume) {
            int remaining = req.getCount();
            for (int i = 0; i < this.inventory.getContainerSize() && remaining > 0; ++i) {
                ItemStack slot = this.inventory.getItem(i);
                if (!slot.is((Object)req.getItem())) continue;
                int take = Math.min(remaining, slot.getCount());
                slot.shrink(take);
                remaining -= take;
                if (!slot.isEmpty()) continue;
                this.inventory.setItem(i, ItemStack.EMPTY);
            }
        }
        this.setChanged();
    }

    public void dropContents(Level level, BlockPos pos) {
        for (int i = 0; i < this.inventory.getContainerSize(); ++i) {
            ItemStack stack = this.inventory.getItem(i);
            if (stack.isEmpty()) continue;
            ItemEntity entity = new ItemEntity(level, (double)pos.getX() + 0.5, (double)pos.getY() + 0.5, (double)pos.getZ() + 0.5, stack);
            level.addFreshEntity((Entity)entity);
        }
        this.inventory.clearContent();
    }

    public void preRemoveSideEffects(BlockPos pos, BlockState state) {
        super.preRemoveSideEffects(pos, state);
        if (this.level != null) {
            this.dropContents(this.level, pos);
        }
    }

    public Component getDisplayName() {
        return Component.translatable((String)"container.exerion.progression_terminal");
    }

    @Nullable
    public AbstractContainerMenu createMenu(int containerId, Inventory playerInventory, Player player) {
        return new ProgressionTerminalMenu(containerId, playerInventory, (Container)this.inventory, this);
    }

    protected void saveAdditional(ValueOutput output) {
        super.saveAdditional(output);
        if (this.lastPlayerUUID != null) {
            output.putString("LastPlayer", this.lastPlayerUUID.toString());
        }
        ValueOutput invOutput = output.child("Inventory");
        for (int i = 0; i < this.inventory.getContainerSize(); ++i) {
            if (this.inventory.getItem(i).isEmpty()) continue;
            invOutput.store("Slot" + i, ItemStack.CODEC, (Object)this.inventory.getItem(i));
        }
    }

    protected void loadAdditional(ValueInput input) {
        super.loadAdditional(input);
        input.getString("LastPlayer").ifPresent(s -> {
            try {
                this.lastPlayerUUID = UUID.fromString(s);
            }
            catch (IllegalArgumentException e) {
                this.lastPlayerUUID = null;
            }
        });
        ValueInput invInput = input.childOrEmpty("Inventory");
        for (int i = 0; i < this.inventory.getContainerSize(); ++i) {
            ItemStack stack = invInput.read("Slot" + i, ItemStack.CODEC).orElse(ItemStack.EMPTY);
            this.inventory.setItem(i, stack);
        }
    }
}

