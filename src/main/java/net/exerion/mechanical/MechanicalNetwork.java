/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.core.BlockPos
 *  net.minecraft.core.Direction
 *  net.minecraft.core.Direction$Axis
 *  net.minecraft.world.level.Level
 *  net.minecraft.world.level.block.entity.BlockEntity
 *  net.minecraft.world.level.block.state.BlockState
 */
package net.exerion.mechanical;

import java.util.HashSet;
import java.util.LinkedList;
import net.exerion.block.ShaftBlock;
import net.exerion.block.entity.ShaftBlockEntity;
import net.exerion.mechanical.IFMSource;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public class MechanicalNetwork {
    public static final int MAX_PROPAGATION_DISTANCE = 24;
    public static final int LOSS_INTERVAL = 4;

    public static int getAvailableFM(Level level, BlockPos machinePos) {
        if (level.isClientSide()) {
            return 0;
        }
        HashSet<BlockPos> visited = new HashSet<BlockPos>();
        LinkedList<long[]> queue = new LinkedList<long[]>();
        visited.add(machinePos);
        for (Direction dir : Direction.values()) {
            BlockPos neighbor = machinePos.relative(dir);
            if (visited.contains(neighbor)) continue;
            visited.add(neighbor);
            queue.add(new long[]{neighbor.getX(), neighbor.getY(), neighbor.getZ(), 1L});
        }
        int totalFM = 0;
        while (!queue.isEmpty()) {
            BlockState shaftState;
            long[] current = (long[])queue.poll();
            BlockPos pos = new BlockPos((int)current[0], (int)current[1], (int)current[2]);
            int distance = (int)current[3];
            if (distance > 24) continue;
            BlockEntity be = level.getBlockEntity(pos);
            if (be instanceof IFMSource) {
                IFMSource source = (IFMSource)be;
                int outputFM = source.getOutputFM();
                int loss = distance / 4;
                int effectiveFM = Math.max(0, outputFM - loss);
                totalFM += effectiveFM;
                continue;
            }
            if (!(be instanceof ShaftBlockEntity) || !(shaftState = level.getBlockState(pos)).hasProperty(ShaftBlock.AXIS)) continue;
            Direction.Axis axis = (Direction.Axis)shaftState.getValue(ShaftBlock.AXIS);
            for (Direction dir : Direction.values()) {
                BlockPos neighbor;
                if (dir.getAxis() != axis || visited.contains(neighbor = pos.relative(dir))) continue;
                visited.add(neighbor);
                queue.add(new long[]{neighbor.getX(), neighbor.getY(), neighbor.getZ(), distance + 1});
            }
        }
        return totalFM;
    }

    public static boolean hasActiveSource(Level level, BlockPos startPos) {
        if (level.isClientSide()) {
            return false;
        }
        HashSet<BlockPos> visited = new HashSet<BlockPos>();
        LinkedList<long[]> queue = new LinkedList<long[]>();
        visited.add(startPos);
        queue.add(new long[]{startPos.getX(), startPos.getY(), startPos.getZ(), 0L});
        while (!queue.isEmpty()) {
            BlockState shaftState;
            long[] current = (long[])queue.poll();
            BlockPos pos = new BlockPos((int)current[0], (int)current[1], (int)current[2]);
            int distance = (int)current[3];
            if (distance > 24) continue;
            BlockEntity be = level.getBlockEntity(pos);
            if (be instanceof IFMSource) {
                IFMSource source = (IFMSource)be;
                if (!source.isGeneratingFM()) continue;
                return true;
            }
            if (!(be instanceof ShaftBlockEntity) || !(shaftState = level.getBlockState(pos)).hasProperty(ShaftBlock.AXIS)) continue;
            Direction.Axis axis = (Direction.Axis)shaftState.getValue(ShaftBlock.AXIS);
            for (Direction dir : Direction.values()) {
                BlockPos neighbor;
                if (dir.getAxis() != axis || visited.contains(neighbor = pos.relative(dir))) continue;
                visited.add(neighbor);
                queue.add(new long[]{neighbor.getX(), neighbor.getY(), neighbor.getZ(), distance + 1});
            }
        }
        return false;
    }
}

