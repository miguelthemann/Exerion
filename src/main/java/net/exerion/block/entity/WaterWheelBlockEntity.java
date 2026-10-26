/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.core.BlockPos
 *  net.minecraft.core.Direction
 *  net.minecraft.core.particles.ParticleOptions
 *  net.minecraft.core.particles.ParticleTypes
 *  net.minecraft.server.level.ServerLevel
 *  net.minecraft.tags.FluidTags
 *  net.minecraft.world.level.Level
 *  net.minecraft.world.level.block.entity.BlockEntity
 *  net.minecraft.world.level.block.state.BlockState
 *  net.minecraft.world.level.block.state.properties.Property
 *  net.minecraft.world.level.storage.ValueInput
 *  net.minecraft.world.level.storage.ValueOutput
 */
package net.exerion.block.entity;

import net.exerion.block.WaterWheelBlock;
import net.exerion.block.entity.ModBlockEntityTypes;
import net.exerion.mechanical.IFMSource;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.FluidTags;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.Property;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;

public class WaterWheelBlockEntity
extends BlockEntity
implements IFMSource {
    public static final int FM_OUTPUT = 20;
    private boolean hasWater = false;
    private int checkTimer = 0;
    private static final int CHECK_INTERVAL = 20;
    public float visualRotationAngle = 0.0f;
    public static final float ROTATION_SPEED = 4.0f;

    public WaterWheelBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntityTypes.WATER_WHEEL_ENTITY, pos, state);
    }

    public boolean hasWater() {
        return this.hasWater;
    }

    @Override
    public int getOutputFM() {
        return this.hasWater ? 20 : 0;
    }

    @Override
    public boolean isGeneratingFM() {
        return this.hasWater;
    }

    public void tick(Level level, BlockPos pos, BlockState state) {
        if (level.isClientSide()) {
            if (((Boolean)state.getValue((Property)WaterWheelBlock.SPINNING)).booleanValue()) {
                this.visualRotationAngle = (float)level.getGameTime() * 4.0f % 360.0f;
            }
            return;
        }
        ++this.checkTimer;
        if (this.checkTimer >= 20) {
            this.checkTimer = 0;
            boolean waterFound = false;
            for (Direction dir : Direction.values()) {
                BlockPos neighborPos = pos.relative(dir);
                if (!level.getFluidState(neighborPos).is(FluidTags.WATER)) continue;
                waterFound = true;
                break;
            }
            if (this.hasWater != waterFound) {
                this.hasWater = waterFound;
                this.setChanged();
                boolean currentSpin = (Boolean)state.getValue((Property)WaterWheelBlock.SPINNING);
                if (currentSpin != this.hasWater) {
                    level.setBlock(pos, (BlockState)state.setValue((Property)WaterWheelBlock.SPINNING, (Comparable)Boolean.valueOf(this.hasWater)), 3);
                }
                if (this.hasWater && level instanceof ServerLevel) {
                    ServerLevel serverLevel = (ServerLevel)level;
                    serverLevel.sendParticles((ParticleOptions)ParticleTypes.SPLASH, (double)pos.getX() + 0.5, (double)pos.getY() + 0.5, (double)pos.getZ() + 0.5, 8, 0.4, 0.2, 0.4, 0.0);
                }
            }
        }
    }

    protected void saveAdditional(ValueOutput output) {
        super.saveAdditional(output);
        output.putBoolean("HasWater", this.hasWater);
    }

    protected void loadAdditional(ValueInput input) {
        super.loadAdditional(input);
        this.hasWater = input.getBooleanOr("HasWater", false);
    }
}

