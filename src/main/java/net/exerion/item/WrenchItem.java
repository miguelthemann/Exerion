package net.exerion.item;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.Property;

public class WrenchItem
extends Item {
    public WrenchItem(Item.Properties properties) {
        super(properties);
    }

    public InteractionResult useOn(UseOnContext context) {
        Level level = context.getLevel();
        BlockPos pos = context.getClickedPos();
        BlockState state = level.getBlockState(pos);
        Player player = context.getPlayer();
        if (player == null) {
            return InteractionResult.PASS;
        }
        String blockId = BuiltInRegistries.BLOCK.getKey(state.getBlock()).getNamespace();
        if (!blockId.equals("exerion")) {
            return InteractionResult.PASS;
        }
        if (!level.isClientSide()) {
            if (player.isSecondaryUseActive()) {
                level.destroyBlock(pos, true);
            } else if (state.hasProperty(BlockStateProperties.FACING)) {
                Direction current = (Direction)state.getValue(BlockStateProperties.FACING);
                Direction next = this.getNextDirection(current);
                level.setBlock(pos, (BlockState)state.setValue(BlockStateProperties.FACING, next), 3);
            } else if (state.hasProperty(BlockStateProperties.HORIZONTAL_FACING)) {
                Direction current = (Direction)state.getValue(BlockStateProperties.HORIZONTAL_FACING);
                Direction next = this.getNextHorizontalDirection(current);
                level.setBlock(pos, (BlockState)state.setValue(BlockStateProperties.HORIZONTAL_FACING, next), 3);
            }
        }
        return InteractionResult.SUCCESS;
    }

    private Direction getNextDirection(Direction dir) {
        return Direction.values()[(dir.ordinal() + 1) % Direction.values().length];
    }

    private Direction getNextHorizontalDirection(Direction dir) {
        return switch (dir) {
            case Direction.NORTH -> Direction.EAST;
            case Direction.EAST -> Direction.SOUTH;
            case Direction.SOUTH -> Direction.WEST;
            case Direction.WEST -> Direction.NORTH;
            default -> Direction.NORTH;
        };
    }
}

