/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.core.BlockPos
 *  net.minecraft.core.Direction
 *  net.minecraft.world.level.Level
 *  net.minecraft.world.level.block.entity.BlockEntity
 *  net.minecraft.world.level.block.state.BlockState
 */
package net.exerion.block.entity;

import net.exerion.block.entity.GeothermalGeneratorBlockEntity;
import net.exerion.block.entity.ModBlockEntityTypes;
import net.exerion.fluid.GlobalFluidNetwork;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public class FrequencyEnderTankBlockEntity
extends BlockEntity {
    private String frequency = "DEFAULT";
    private int extractCooldown = 0;

    public FrequencyEnderTankBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntityTypes.FREQUENCY_ENDER_TANK_ENTITY, pos, state);
    }

    public String getFrequency() {
        return this.frequency;
    }

    public void setFrequency(String frequency) {
        this.frequency = frequency;
        this.setChanged();
    }

    public void tick(Level level, BlockPos pos, BlockState state) {
        if (level.isClientSide()) {
            return;
        }
        if (this.extractCooldown > 0) {
            --this.extractCooldown;
            return;
        }
        int currentLava = GlobalFluidNetwork.getLavaAmount(this.frequency);
        if (currentLava > 0) {
            for (Direction dir : Direction.values()) {
                int amountToPush;
                int extracted;
                GeothermalGeneratorBlockEntity geoGenerator;
                int space;
                BlockEntity neighbor = level.getBlockEntity(pos.relative(dir));
                if (!(neighbor instanceof GeothermalGeneratorBlockEntity) || (space = (geoGenerator = (GeothermalGeneratorBlockEntity)neighbor).getMaxLavaBuffer() - geoGenerator.getLavaBuffer()) <= 0 || (extracted = GlobalFluidNetwork.extractLava(this.frequency, amountToPush = Math.min(space, Math.min(100, currentLava)))) <= 0) continue;
                geoGenerator.setLavaBuffer(geoGenerator.getLavaBuffer() + extracted);
                this.extractCooldown = 5;
                break;
            }
        }
    }
}

