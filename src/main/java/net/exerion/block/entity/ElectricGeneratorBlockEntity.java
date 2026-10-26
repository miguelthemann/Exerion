/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.core.BlockPos
 *  net.minecraft.network.chat.Component
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
 *  net.minecraft.world.inventory.ContainerData
 *  net.minecraft.world.item.ItemStack
 *  net.minecraft.world.item.Items
 *  net.minecraft.world.level.ItemLike
 *  net.minecraft.world.level.Level
 *  net.minecraft.world.level.block.entity.BlockEntity
 *  net.minecraft.world.level.block.state.BlockState
 *  net.minecraft.world.level.block.state.properties.Property
 *  net.minecraft.world.level.storage.ValueInput
 *  net.minecraft.world.level.storage.ValueOutput
 *  org.jetbrains.annotations.Nullable
 */
package net.exerion.block.entity;

import java.util.Objects;
import net.exerion.block.ElectricGeneratorBlock;
import net.exerion.block.entity.ModBlockEntityTypes;
import net.exerion.energy.IEnergyStorage;
import net.exerion.item.ModItems;
import net.exerion.menu.ElectricGeneratorMenu;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
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
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.Property;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;
import org.jetbrains.annotations.Nullable;

public class ElectricGeneratorBlockEntity
extends BlockEntity
implements MenuProvider,
IEnergyStorage {
    private final SimpleContainer inventory = new SimpleContainer(this, 2){
        final /* synthetic */ ElectricGeneratorBlockEntity this$0;
        {
            ElectricGeneratorBlockEntity electricGeneratorBlockEntity = this$0;
            Objects.requireNonNull(electricGeneratorBlockEntity);
            this.this$0 = electricGeneratorBlockEntity;
            super(arg0);
        }

        public void setChanged() {
            super.setChanged();
            this.this$0.setChanged();
        }
    };
    private int energy = 0;
    private final int maxEnergy = 10000;
    private int burnTime = 0;
    private int maxBurnTime = 0;
    private int generationRate = 0;
    protected final ContainerData dataAccess = new ContainerData(this){
        final /* synthetic */ ElectricGeneratorBlockEntity this$0;
        {
            ElectricGeneratorBlockEntity electricGeneratorBlockEntity = this$0;
            Objects.requireNonNull(electricGeneratorBlockEntity);
            this.this$0 = electricGeneratorBlockEntity;
        }

        public int get(int index) {
            return switch (index) {
                case 0 -> this.this$0.burnTime;
                case 1 -> this.this$0.maxBurnTime;
                case 2 -> this.this$0.energy;
                case 3 -> this.this$0.maxEnergy;
                default -> 0;
            };
        }

        public void set(int index, int value) {
            switch (index) {
                case 0: {
                    this.this$0.burnTime = value;
                    break;
                }
                case 1: {
                    this.this$0.maxBurnTime = value;
                    break;
                }
                case 2: {
                    this.this$0.energy = value;
                }
            }
        }

        public int getCount() {
            return 4;
        }
    };

    public ElectricGeneratorBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntityTypes.ELECTRIC_GENERATOR_ENTITY, pos, state);
    }

    public SimpleContainer getInventory() {
        return this.inventory;
    }

    @Override
    public int getEnergy() {
        return this.energy;
    }

    @Override
    public int getMaxEnergy() {
        return 10000;
    }

    @Override
    public void setEnergy(int energy) {
        this.energy = energy;
        this.setChanged();
    }

    public int getBurnTime() {
        return this.burnTime;
    }

    public int getMaxBurnTime() {
        return this.maxBurnTime;
    }

    public void tick(Level level, BlockPos pos, BlockState state) {
        int damage;
        ItemStack fuelSlot;
        if (level.isClientSide()) {
            return;
        }
        boolean wasGenerating = (Boolean)state.getValue((Property)ElectricGeneratorBlock.GENERATING);
        boolean isGenerating = false;
        if (this.burnTime > 0) {
            --this.burnTime;
            isGenerating = true;
            if (this.energy < 10000) {
                this.energy = Math.min(10000, this.energy + this.generationRate);
            }
            this.setChanged();
        }
        if (this.burnTime == 0 && this.energy < 10000 && !(fuelSlot = this.inventory.getItem(0)).isEmpty()) {
            if (fuelSlot.is((Object)Items.COAL) || fuelSlot.is((Object)Items.CHARCOAL)) {
                this.burnTime = 100;
                this.maxBurnTime = 100;
                this.generationRate = 10;
                fuelSlot.shrink(1);
                this.setChanged();
            } else if (fuelSlot.is((Object)Items.COAL_BLOCK)) {
                this.burnTime = 900;
                this.maxBurnTime = 900;
                this.generationRate = 12;
                fuelSlot.shrink(1);
                this.setChanged();
            } else if (fuelSlot.is((Object)ModItems.STEAM_CRYSTAL)) {
                this.burnTime = 200;
                this.maxBurnTime = 200;
                this.generationRate = 20;
                fuelSlot.shrink(1);
                this.setChanged();
            } else if (fuelSlot.is((Object)Items.LAVA_BUCKET)) {
                this.burnTime = 400;
                this.maxBurnTime = 400;
                this.generationRate = 20;
                this.inventory.setItem(0, new ItemStack((ItemLike)Items.BUCKET));
                this.setChanged();
            }
        }
        ItemStack chargeSlot = this.inventory.getItem(1);
        if (this.energy > 0 && !chargeSlot.isEmpty() && (chargeSlot.is((Object)ModItems.ERA_SCANNER) || chargeSlot.is((Object)ModItems.STEAM_BOOTS) || chargeSlot.is((Object)ModItems.ELECTRIC_PLATE) || chargeSlot.is((Object)ModItems.LOGIC_HELMET) || chargeSlot.is((Object)ModItems.ELECTRIC_MINING_DRILL) || chargeSlot.is((Object)ModItems.PLASMA_SWORD)) && (damage = chargeSlot.getDamageValue()) > 0) {
            int chargeAmount = Math.min(damage, 2);
            chargeSlot.setDamageValue(damage - chargeAmount);
            this.energy -= chargeAmount;
            this.setChanged();
            if (level.getGameTime() % 20L == 0L) {
                level.playSound(null, pos, SoundEvents.REDSTONE_TORCH_BURNOUT, SoundSource.BLOCKS, 0.4f, 1.2f);
            }
        }
        if (wasGenerating != isGenerating) {
            level.setBlock(pos, (BlockState)state.setValue((Property)ElectricGeneratorBlock.GENERATING, (Comparable)Boolean.valueOf(isGenerating)), 3);
        }
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
        return Component.translatable((String)"container.exerion.electric_generator");
    }

    @Nullable
    public AbstractContainerMenu createMenu(int containerId, Inventory playerInventory, Player player) {
        return new ElectricGeneratorMenu(containerId, playerInventory, (Container)this.inventory, this.dataAccess);
    }

    protected void saveAdditional(ValueOutput output) {
        super.saveAdditional(output);
        output.putInt("Energy", this.energy);
        output.putInt("BurnTime", this.burnTime);
        output.putInt("MaxBurnTime", this.maxBurnTime);
        output.putInt("GenRate", this.generationRate);
        ValueOutput invOutput = output.child("Inventory");
        for (int i = 0; i < this.inventory.getContainerSize(); ++i) {
            ItemStack stack = this.inventory.getItem(i);
            if (stack.isEmpty()) continue;
            invOutput.store("Slot" + i, ItemStack.CODEC, (Object)stack);
        }
    }

    protected void loadAdditional(ValueInput input) {
        super.loadAdditional(input);
        this.energy = input.getIntOr("Energy", 0);
        this.burnTime = input.getIntOr("BurnTime", 0);
        this.maxBurnTime = input.getIntOr("MaxBurnTime", 0);
        this.generationRate = input.getIntOr("GenRate", 0);
        ValueInput invInput = input.childOrEmpty("Inventory");
        for (int i = 0; i < this.inventory.getContainerSize(); ++i) {
            ItemStack stack = invInput.read("Slot" + i, ItemStack.CODEC).orElse(ItemStack.EMPTY);
            this.inventory.setItem(i, stack);
        }
    }
}

