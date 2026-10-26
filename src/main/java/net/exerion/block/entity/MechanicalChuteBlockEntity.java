/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.core.BlockPos
 *  net.minecraft.core.Direction
 *  net.minecraft.world.Container
 *  net.minecraft.world.WorldlyContainer
 *  net.minecraft.world.item.ItemStack
 *  net.minecraft.world.level.Level
 *  net.minecraft.world.level.block.entity.BlockEntity
 *  net.minecraft.world.level.block.state.BlockState
 */
package net.exerion.block.entity;

import net.exerion.block.entity.ModBlockEntityTypes;
import net.exerion.mechanical.IFMConsumer;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.Container;
import net.minecraft.world.WorldlyContainer;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public class MechanicalChuteBlockEntity
extends BlockEntity
implements IFMConsumer {
    private int transferCooldown = 0;

    public MechanicalChuteBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntityTypes.MECHANICAL_CHUTE_ENTITY, pos, state);
    }

    @Override
    public int getRequiredFM() {
        return 4;
    }

    public void tick(Level level, BlockPos pos, BlockState state) {
        if (level.isClientSide()) {
            return;
        }
        if (this.transferCooldown > 0) {
            --this.transferCooldown;
            return;
        }
        BlockEntity aboveEntity = level.getBlockEntity(pos.above());
        BlockEntity belowEntity = level.getBlockEntity(pos.below());
        if (aboveEntity instanceof Container) {
            Container belowContainer;
            boolean transferred;
            Container aboveContainer = (Container)aboveEntity;
            if (belowEntity instanceof Container && (transferred = this.transferItem(aboveContainer, belowContainer = (Container)belowEntity, Direction.DOWN))) {
                this.transferCooldown = 10;
            }
        }
    }

    private boolean transferItem(Container source, Container destination, Direction direction) {
        for (int i = 0; i < source.getContainerSize(); ++i) {
            WorldlyContainer worldlySource;
            ItemStack stackInSlot = source.getItem(i);
            if (stackInSlot.isEmpty() || source instanceof WorldlyContainer && !(worldlySource = (WorldlyContainer)source).canTakeItemThroughFace(i, stackInSlot, Direction.DOWN)) continue;
            ItemStack stackToTransfer = stackInSlot.copy();
            stackToTransfer.setCount(1);
            ItemStack remaining = this.insertItem(destination, stackToTransfer, direction);
            if (!remaining.isEmpty()) continue;
            source.removeItem(i, 1);
            return true;
        }
        return false;
    }

    private ItemStack insertItem(Container destination, ItemStack stack, Direction direction) {
        for (int i = 0; i < destination.getContainerSize(); ++i) {
            int space;
            WorldlyContainer worldlyDest;
            if (destination instanceof WorldlyContainer && !(worldlyDest = (WorldlyContainer)destination).canPlaceItemThroughFace(i, stack, direction.getOpposite())) continue;
            ItemStack stackInSlot = destination.getItem(i);
            if (stackInSlot.isEmpty()) {
                destination.setItem(i, stack);
                return ItemStack.EMPTY;
            }
            if (!ItemStack.isSameItemSameComponents((ItemStack)stackInSlot, (ItemStack)stack) || (space = stackInSlot.getMaxStackSize() - stackInSlot.getCount()) < stack.getCount()) continue;
            stackInSlot.grow(stack.getCount());
            return ItemStack.EMPTY;
        }
        return stack;
    }
}

