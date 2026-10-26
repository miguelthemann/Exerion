/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.core.Registry
 *  net.minecraft.core.registries.BuiltInRegistries
 *  net.minecraft.core.registries.Registries
 *  net.minecraft.resources.Identifier
 *  net.minecraft.resources.ResourceKey
 *  net.minecraft.world.item.BlockItem
 *  net.minecraft.world.item.Item$Properties
 *  net.minecraft.world.level.block.Block
 *  net.minecraft.world.level.block.SoundType
 *  net.minecraft.world.level.block.state.BlockBehaviour$Properties
 *  net.minecraft.world.level.material.MapColor
 */
package net.exerion.block;

import java.util.function.Function;
import net.exerion.Exerion;
import net.exerion.block.AlloySmelterBlock;
import net.exerion.block.BatBoxBlock;
import net.exerion.block.CableBlock;
import net.exerion.block.CoalBoilerBlock;
import net.exerion.block.CompressorBlock;
import net.exerion.block.CrusherBlock;
import net.exerion.block.DigitalStorageTerminalBlock;
import net.exerion.block.ElectricCrusherBlock;
import net.exerion.block.ElectricFurnaceBlock;
import net.exerion.block.ElectricGeneratorBlock;
import net.exerion.block.FrequencyEnderChestBlock;
import net.exerion.block.FrequencyEnderTankBlock;
import net.exerion.block.GearPressBlock;
import net.exerion.block.GeothermalGeneratorBlock;
import net.exerion.block.HandCrankBlock;
import net.exerion.block.MechanicalChuteBlock;
import net.exerion.block.MiningLaserBlock;
import net.exerion.block.ProgressionTerminalBlock;
import net.exerion.block.PumpBlock;
import net.exerion.block.ShaftBlock;
import net.exerion.block.SolarPanelBlock;
import net.exerion.block.SteamTurbineBlock;
import net.exerion.block.WaterWheelBlock;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;

public class ModBlocks {
    public static final Block EXERION_CASING = ModBlocks.registerBlock("exerion_casing", Block::new, BlockBehaviour.Properties.of().strength(5.0f, 6.0f).requiresCorrectToolForDrops().sound(SoundType.METAL).mapColor(MapColor.METAL));
    public static final Block EXERION_BLOCK = ModBlocks.registerBlock("exerion_block", Block::new, BlockBehaviour.Properties.of().strength(5.0f, 6.0f).requiresCorrectToolForDrops().sound(SoundType.METAL).mapColor(MapColor.METAL));
    public static final Block EXERION_ORE = ModBlocks.registerBlock("exerion_ore", Block::new, BlockBehaviour.Properties.of().strength(3.0f, 3.0f).requiresCorrectToolForDrops().sound(SoundType.STONE).mapColor(MapColor.STONE));
    public static final Block DEEPSLATE_EXERION_ORE = ModBlocks.registerBlock("deepslate_exerion_ore", Block::new, BlockBehaviour.Properties.of().strength(4.5f, 3.0f).requiresCorrectToolForDrops().sound(SoundType.DEEPSLATE).mapColor(MapColor.DEEPSLATE));
    public static final Block RAW_EXERION_BLOCK = ModBlocks.registerBlock("raw_exerion_block", Block::new, BlockBehaviour.Properties.of().strength(5.0f, 6.0f).requiresCorrectToolForDrops().sound(SoundType.METAL).mapColor(MapColor.RAW_IRON));
    public static final Block EXERION_FRAME = ModBlocks.registerBlock("exerion_frame", Block::new, BlockBehaviour.Properties.of().strength(3.0f, 3.0f).requiresCorrectToolForDrops().sound(SoundType.METAL).mapColor(MapColor.METAL).noOcclusion());
    public static final Block GEAR_PRESS = ModBlocks.registerBlock("gear_press", GearPressBlock::new, BlockBehaviour.Properties.of().strength(4.0f, 5.0f).requiresCorrectToolForDrops().sound(SoundType.METAL).mapColor(MapColor.METAL).noOcclusion());
    public static final Block COMPRESSOR = ModBlocks.registerBlock("compressor", CompressorBlock::new, BlockBehaviour.Properties.of().strength(5.0f, 6.0f).requiresCorrectToolForDrops().sound(SoundType.METAL).mapColor(MapColor.METAL).noOcclusion());
    public static final Block MINING_LASER = ModBlocks.registerBlock("mining_laser", MiningLaserBlock::new, BlockBehaviour.Properties.of().strength(6.0f, 8.0f).requiresCorrectToolForDrops().sound(SoundType.METAL).mapColor(MapColor.METAL).noOcclusion());
    public static final Block GEOTHERMAL_GENERATOR = ModBlocks.registerBlock("geothermal_generator", GeothermalGeneratorBlock::new, BlockBehaviour.Properties.of().strength(5.0f, 6.0f).requiresCorrectToolForDrops().sound(SoundType.METAL).mapColor(MapColor.METAL).noOcclusion());
    public static final Block ALLOY_SMELTER = ModBlocks.registerBlock("alloy_smelter", AlloySmelterBlock::new, BlockBehaviour.Properties.of().strength(5.0f, 6.0f).requiresCorrectToolForDrops().sound(SoundType.METAL).mapColor(MapColor.METAL).noOcclusion());
    public static final Block ELECTRIC_FURNACE = ModBlocks.registerBlock("electric_furnace", ElectricFurnaceBlock::new, BlockBehaviour.Properties.of().strength(5.0f, 6.0f).requiresCorrectToolForDrops().sound(SoundType.METAL).mapColor(MapColor.METAL).noOcclusion());
    public static final Block FREQUENCY_ENDER_CHEST = ModBlocks.registerBlock("frequency_ender_chest", FrequencyEnderChestBlock::new, BlockBehaviour.Properties.of().strength(5.0f, 6.0f).requiresCorrectToolForDrops().sound(SoundType.STONE).mapColor(MapColor.COLOR_BLACK).noOcclusion());
    public static final Block FREQUENCY_ENDER_TANK = ModBlocks.registerBlock("frequency_ender_tank", FrequencyEnderTankBlock::new, BlockBehaviour.Properties.of().strength(5.0f, 6.0f).requiresCorrectToolForDrops().sound(SoundType.GLASS).mapColor(MapColor.COLOR_BLACK).noOcclusion());
    public static final Block BATBOX = ModBlocks.registerBlock("batbox", BatBoxBlock::new, BlockBehaviour.Properties.of().strength(4.0f, 5.0f).requiresCorrectToolForDrops().sound(SoundType.WOOD).mapColor(MapColor.WOOD).noOcclusion());
    public static final Block COAL_BOILER = ModBlocks.registerBlock("coal_boiler", CoalBoilerBlock::new, BlockBehaviour.Properties.of().strength(5.0f, 6.0f).requiresCorrectToolForDrops().sound(SoundType.METAL).mapColor(MapColor.COLOR_BLACK).noOcclusion());
    public static final Block STEAM_TURBINE = ModBlocks.registerBlock("steam_turbine", SteamTurbineBlock::new, BlockBehaviour.Properties.of().strength(5.0f, 6.0f).requiresCorrectToolForDrops().sound(SoundType.METAL).mapColor(MapColor.METAL).noOcclusion());
    public static final Block CRUSHER = ModBlocks.registerBlock("crusher", CrusherBlock::new, BlockBehaviour.Properties.of().strength(4.0f, 5.0f).requiresCorrectToolForDrops().sound(SoundType.STONE).mapColor(MapColor.STONE).noOcclusion());
    public static final Block PROGRESSION_TERMINAL = ModBlocks.registerBlock("progression_terminal", ProgressionTerminalBlock::new, BlockBehaviour.Properties.of().strength(5.0f, 6.0f).requiresCorrectToolForDrops().sound(SoundType.METAL).mapColor(MapColor.COLOR_PURPLE));
    public static final Block DIGITAL_STORAGE_TERMINAL = ModBlocks.registerBlock("digital_storage_terminal", DigitalStorageTerminalBlock::new, BlockBehaviour.Properties.of().strength(5.0f, 6.0f).requiresCorrectToolForDrops().sound(SoundType.METAL).mapColor(MapColor.COLOR_PURPLE));
    public static final Block STEEL_BLOCK = ModBlocks.registerBlock("steel_block", Block::new, BlockBehaviour.Properties.of().strength(6.0f, 8.0f).requiresCorrectToolForDrops().sound(SoundType.METAL).mapColor(MapColor.METAL));
    public static final Block STEEL_CASING = ModBlocks.registerBlock("steel_casing", Block::new, BlockBehaviour.Properties.of().strength(5.0f, 6.0f).requiresCorrectToolForDrops().sound(SoundType.METAL).mapColor(MapColor.METAL));
    public static final Block ELECTRIC_GENERATOR = ModBlocks.registerBlock("electric_generator", ElectricGeneratorBlock::new, BlockBehaviour.Properties.of().strength(5.0f, 6.0f).requiresCorrectToolForDrops().sound(SoundType.METAL).mapColor(MapColor.METAL).noOcclusion());
    public static final Block COPPER_CABLE = ModBlocks.registerBlock("copper_cable", properties -> new CableBlock((BlockBehaviour.Properties)properties, 100, 500), BlockBehaviour.Properties.of().strength(1.0f, 1.0f).sound(SoundType.WOOL).mapColor(MapColor.COLOR_ORANGE).noOcclusion());
    public static final Block STEEL_CABLE = ModBlocks.registerBlock("steel_cable", properties -> new CableBlock((BlockBehaviour.Properties)properties, 500, 2500), BlockBehaviour.Properties.of().strength(1.5f, 1.5f).sound(SoundType.METAL).mapColor(MapColor.METAL).noOcclusion());
    public static final Block EXERION_CABLE = ModBlocks.registerBlock("exerion_cable", properties -> new CableBlock((BlockBehaviour.Properties)properties, 2000, 10000), BlockBehaviour.Properties.of().strength(2.0f, 2.0f).sound(SoundType.METAL).mapColor(MapColor.COLOR_PURPLE).noOcclusion());
    public static final Block ELECTRIC_CRUSHER = ModBlocks.registerBlock("electric_crusher", ElectricCrusherBlock::new, BlockBehaviour.Properties.of().strength(5.0f, 6.0f).requiresCorrectToolForDrops().sound(SoundType.METAL).mapColor(MapColor.METAL).noOcclusion());
    public static final Block COPPER_SOLAR_PANEL = ModBlocks.registerBlock("copper_solar_panel", properties -> new SolarPanelBlock((BlockBehaviour.Properties)properties, 100, 1000, 2), BlockBehaviour.Properties.of().strength(3.0f, 3.0f).sound(SoundType.METAL).mapColor(MapColor.COLOR_ORANGE).noOcclusion());
    public static final Block STEEL_SOLAR_PANEL = ModBlocks.registerBlock("steel_solar_panel", properties -> new SolarPanelBlock((BlockBehaviour.Properties)properties, 500, 5000, 10), BlockBehaviour.Properties.of().strength(4.0f, 4.0f).sound(SoundType.METAL).mapColor(MapColor.METAL).noOcclusion());
    public static final Block EXERION_SOLAR_PANEL = ModBlocks.registerBlock("exerion_solar_panel", properties -> new SolarPanelBlock((BlockBehaviour.Properties)properties, 2000, 20000, 40), BlockBehaviour.Properties.of().strength(5.0f, 5.0f).sound(SoundType.METAL).mapColor(MapColor.COLOR_PURPLE).noOcclusion());
    public static final Block SHAFT = ModBlocks.registerBlock("shaft", ShaftBlock::new, BlockBehaviour.Properties.of().strength(1.5f, 2.0f).sound(SoundType.WOOD).mapColor(MapColor.WOOD).noOcclusion());
    public static final Block HAND_CRANK = ModBlocks.registerBlock("hand_crank", HandCrankBlock::new, BlockBehaviour.Properties.of().strength(2.0f, 2.0f).sound(SoundType.WOOD).mapColor(MapColor.WOOD).noOcclusion());
    public static final Block WATER_WHEEL = ModBlocks.registerBlock("water_wheel", WaterWheelBlock::new, BlockBehaviour.Properties.of().strength(3.0f, 3.0f).sound(SoundType.WOOD).mapColor(MapColor.WOOD).noOcclusion());
    public static final Block MECHANICAL_CHUTE = ModBlocks.registerBlock("mechanical_chute", MechanicalChuteBlock::new, BlockBehaviour.Properties.of().strength(3.0f, 3.0f).sound(SoundType.METAL).mapColor(MapColor.METAL).noOcclusion());
    public static final Block PUMP = ModBlocks.registerBlock("pump", PumpBlock::new, BlockBehaviour.Properties.of().strength(3.0f, 3.0f).sound(SoundType.METAL).mapColor(MapColor.METAL).noOcclusion());

    private static Block registerBlock(String name, Function<BlockBehaviour.Properties, Block> factory, BlockBehaviour.Properties properties) {
        ResourceKey blockKey = ResourceKey.create((ResourceKey)Registries.BLOCK, (Identifier)Identifier.fromNamespaceAndPath((String)"exerion", (String)name));
        Block block = factory.apply(properties.setId(blockKey));
        ResourceKey itemKey = ResourceKey.create((ResourceKey)Registries.ITEM, (Identifier)Identifier.fromNamespaceAndPath((String)"exerion", (String)name));
        Registry.register((Registry)BuiltInRegistries.ITEM, (ResourceKey)itemKey, (Object)new BlockItem(block, new Item.Properties().setId(itemKey)));
        return (Block)Registry.register((Registry)BuiltInRegistries.BLOCK, (ResourceKey)blockKey, (Object)block);
    }

    public static void registerModBlocks() {
        Exerion.LOGGER.info("Registering Mod Blocks for exerion");
    }
}

