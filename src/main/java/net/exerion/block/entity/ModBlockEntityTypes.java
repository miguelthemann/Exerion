package net.exerion.block.entity;

import net.exerion.Exerion;
import net.exerion.block.ModBlocks;
import net.exerion.block.entity.AlloySmelterBlockEntity;
import net.exerion.block.entity.BatBoxBlockEntity;
import net.exerion.block.entity.CableBlockEntity;
import net.exerion.block.entity.CoalBoilerBlockEntity;
import net.exerion.block.entity.CompressorBlockEntity;
import net.exerion.block.entity.CrusherBlockEntity;
import net.exerion.block.entity.DigitalStorageTerminalBlockEntity;
import net.exerion.block.entity.ElectricCrusherBlockEntity;
import net.exerion.block.entity.ElectricFurnaceBlockEntity;
import net.exerion.block.entity.ElectricGeneratorBlockEntity;
import net.exerion.block.entity.FrequencyEnderChestBlockEntity;
import net.exerion.block.entity.FrequencyEnderTankBlockEntity;
import net.exerion.block.entity.GearPressBlockEntity;
import net.exerion.block.entity.GeothermalGeneratorBlockEntity;
import net.exerion.block.entity.HandCrankBlockEntity;
import net.exerion.block.entity.MechanicalChuteBlockEntity;
import net.exerion.block.entity.MiningLaserBlockEntity;
import net.exerion.block.entity.ProgressionTerminalBlockEntity;
import net.exerion.block.entity.PumpBlockEntity;
import net.exerion.block.entity.ShaftBlockEntity;
import net.exerion.block.entity.SolarPanelBlockEntity;
import net.exerion.block.entity.SteamTurbineBlockEntity;
import net.exerion.block.entity.WaterWheelBlockEntity;
import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntityType;

public class ModBlockEntityTypes {
    public static final BlockEntityType<CoalBoilerBlockEntity> COAL_BOILER_ENTITY;
    public static final BlockEntityType<ProgressionTerminalBlockEntity> PROGRESSION_TERMINAL_ENTITY;
    public static final BlockEntityType<SteamTurbineBlockEntity> STEAM_TURBINE_ENTITY;
    public static final BlockEntityType<CrusherBlockEntity> CRUSHER_ENTITY;
    public static final BlockEntityType<GearPressBlockEntity> GEAR_PRESS_ENTITY;
    public static final BlockEntityType<ElectricGeneratorBlockEntity> ELECTRIC_GENERATOR_ENTITY;
    public static final BlockEntityType<CableBlockEntity> CABLE_ENTITY;
    public static final BlockEntityType<ElectricCrusherBlockEntity> ELECTRIC_CRUSHER_ENTITY;
    public static final BlockEntityType<SolarPanelBlockEntity> SOLAR_PANEL_ENTITY;
    public static final BlockEntityType<DigitalStorageTerminalBlockEntity> DIGITAL_STORAGE_TERMINAL_ENTITY;
    public static final BlockEntityType<BatBoxBlockEntity> BATBOX_ENTITY;
    public static final BlockEntityType<CompressorBlockEntity> COMPRESSOR_ENTITY;
    public static final BlockEntityType<AlloySmelterBlockEntity> ALLOY_SMELTER_ENTITY;
    public static final BlockEntityType<FrequencyEnderChestBlockEntity> FREQUENCY_ENDER_CHEST_ENTITY;
    public static final BlockEntityType<FrequencyEnderTankBlockEntity> FREQUENCY_ENDER_TANK_ENTITY;
    public static final BlockEntityType<MiningLaserBlockEntity> MINING_LASER_ENTITY;
    public static final BlockEntityType<GeothermalGeneratorBlockEntity> GEOTHERMAL_GENERATOR_ENTITY;
    public static final BlockEntityType<ElectricFurnaceBlockEntity> ELECTRIC_FURNACE_ENTITY;
    public static final BlockEntityType<ShaftBlockEntity> SHAFT_ENTITY;
    public static final BlockEntityType<HandCrankBlockEntity> HAND_CRANK_ENTITY;
    public static final BlockEntityType<WaterWheelBlockEntity> WATER_WHEEL_ENTITY;
    public static final BlockEntityType<MechanicalChuteBlockEntity> MECHANICAL_CHUTE_ENTITY;
    public static final BlockEntityType<PumpBlockEntity> PUMP_ENTITY;

    public static void registerBlockEntityTypes() {
        Exerion.LOGGER.info("Registering Block Entity Types for exerion");
    }

    static {
        ResourceKey coalBoilerKey = ResourceKey.create((ResourceKey)Registries.BLOCK_ENTITY_TYPE, (Identifier)Identifier.fromNamespaceAndPath((String)"exerion", (String)"coal_boiler"));
        COAL_BOILER_ENTITY = (BlockEntityType)Registry.register((Registry)BuiltInRegistries.BLOCK_ENTITY_TYPE, (ResourceKey)coalBoilerKey, FabricBlockEntityTypeBuilder.create(CoalBoilerBlockEntity::new, (Block[])new Block[]{ModBlocks.COAL_BOILER}).build());
        ResourceKey terminalKey = ResourceKey.create((ResourceKey)Registries.BLOCK_ENTITY_TYPE, (Identifier)Identifier.fromNamespaceAndPath((String)"exerion", (String)"progression_terminal"));
        PROGRESSION_TERMINAL_ENTITY = (BlockEntityType)Registry.register((Registry)BuiltInRegistries.BLOCK_ENTITY_TYPE, (ResourceKey)terminalKey, FabricBlockEntityTypeBuilder.create(ProgressionTerminalBlockEntity::new, (Block[])new Block[]{ModBlocks.PROGRESSION_TERMINAL}).build());
        ResourceKey steamTurbineKey = ResourceKey.create((ResourceKey)Registries.BLOCK_ENTITY_TYPE, (Identifier)Identifier.fromNamespaceAndPath((String)"exerion", (String)"steam_turbine"));
        STEAM_TURBINE_ENTITY = (BlockEntityType)Registry.register((Registry)BuiltInRegistries.BLOCK_ENTITY_TYPE, (ResourceKey)steamTurbineKey, FabricBlockEntityTypeBuilder.create(SteamTurbineBlockEntity::new, (Block[])new Block[]{ModBlocks.STEAM_TURBINE}).build());
        ResourceKey crusherKey = ResourceKey.create((ResourceKey)Registries.BLOCK_ENTITY_TYPE, (Identifier)Identifier.fromNamespaceAndPath((String)"exerion", (String)"crusher"));
        CRUSHER_ENTITY = (BlockEntityType)Registry.register((Registry)BuiltInRegistries.BLOCK_ENTITY_TYPE, (ResourceKey)crusherKey, FabricBlockEntityTypeBuilder.create(CrusherBlockEntity::new, (Block[])new Block[]{ModBlocks.CRUSHER}).build());
        ResourceKey gearPressKey = ResourceKey.create((ResourceKey)Registries.BLOCK_ENTITY_TYPE, (Identifier)Identifier.fromNamespaceAndPath((String)"exerion", (String)"gear_press"));
        GEAR_PRESS_ENTITY = (BlockEntityType)Registry.register((Registry)BuiltInRegistries.BLOCK_ENTITY_TYPE, (ResourceKey)gearPressKey, FabricBlockEntityTypeBuilder.create(GearPressBlockEntity::new, (Block[])new Block[]{ModBlocks.GEAR_PRESS}).build());
        ResourceKey electricGeneratorKey = ResourceKey.create((ResourceKey)Registries.BLOCK_ENTITY_TYPE, (Identifier)Identifier.fromNamespaceAndPath((String)"exerion", (String)"electric_generator"));
        ELECTRIC_GENERATOR_ENTITY = (BlockEntityType)Registry.register((Registry)BuiltInRegistries.BLOCK_ENTITY_TYPE, (ResourceKey)electricGeneratorKey, FabricBlockEntityTypeBuilder.create(ElectricGeneratorBlockEntity::new, (Block[])new Block[]{ModBlocks.ELECTRIC_GENERATOR}).build());
        ResourceKey cableKey = ResourceKey.create((ResourceKey)Registries.BLOCK_ENTITY_TYPE, (Identifier)Identifier.fromNamespaceAndPath((String)"exerion", (String)"cable"));
        CABLE_ENTITY = (BlockEntityType)Registry.register((Registry)BuiltInRegistries.BLOCK_ENTITY_TYPE, (ResourceKey)cableKey, FabricBlockEntityTypeBuilder.create(CableBlockEntity::new, (Block[])new Block[]{ModBlocks.COPPER_CABLE, ModBlocks.STEEL_CABLE, ModBlocks.EXERION_CABLE}).build());
        ResourceKey electricCrusherKey = ResourceKey.create((ResourceKey)Registries.BLOCK_ENTITY_TYPE, (Identifier)Identifier.fromNamespaceAndPath((String)"exerion", (String)"electric_crusher"));
        ELECTRIC_CRUSHER_ENTITY = (BlockEntityType)Registry.register((Registry)BuiltInRegistries.BLOCK_ENTITY_TYPE, (ResourceKey)electricCrusherKey, FabricBlockEntityTypeBuilder.create(ElectricCrusherBlockEntity::new, (Block[])new Block[]{ModBlocks.ELECTRIC_CRUSHER}).build());
        ResourceKey solarPanelKey = ResourceKey.create((ResourceKey)Registries.BLOCK_ENTITY_TYPE, (Identifier)Identifier.fromNamespaceAndPath((String)"exerion", (String)"solar_panel"));
        SOLAR_PANEL_ENTITY = (BlockEntityType)Registry.register((Registry)BuiltInRegistries.BLOCK_ENTITY_TYPE, (ResourceKey)solarPanelKey, FabricBlockEntityTypeBuilder.create(SolarPanelBlockEntity::new, (Block[])new Block[]{ModBlocks.COPPER_SOLAR_PANEL, ModBlocks.STEEL_SOLAR_PANEL, ModBlocks.EXERION_SOLAR_PANEL}).build());
        ResourceKey digitalStorageTerminalKey = ResourceKey.create((ResourceKey)Registries.BLOCK_ENTITY_TYPE, (Identifier)Identifier.fromNamespaceAndPath((String)"exerion", (String)"digital_storage_terminal"));
        DIGITAL_STORAGE_TERMINAL_ENTITY = (BlockEntityType)Registry.register((Registry)BuiltInRegistries.BLOCK_ENTITY_TYPE, (ResourceKey)digitalStorageTerminalKey, FabricBlockEntityTypeBuilder.create(DigitalStorageTerminalBlockEntity::new, (Block[])new Block[]{ModBlocks.DIGITAL_STORAGE_TERMINAL}).build());
        ResourceKey batboxKey = ResourceKey.create((ResourceKey)Registries.BLOCK_ENTITY_TYPE, (Identifier)Identifier.fromNamespaceAndPath((String)"exerion", (String)"batbox"));
        BATBOX_ENTITY = (BlockEntityType)Registry.register((Registry)BuiltInRegistries.BLOCK_ENTITY_TYPE, (ResourceKey)batboxKey, FabricBlockEntityTypeBuilder.create(BatBoxBlockEntity::new, (Block[])new Block[]{ModBlocks.BATBOX}).build());
        ResourceKey compressorKey = ResourceKey.create((ResourceKey)Registries.BLOCK_ENTITY_TYPE, (Identifier)Identifier.fromNamespaceAndPath((String)"exerion", (String)"compressor"));
        COMPRESSOR_ENTITY = (BlockEntityType)Registry.register((Registry)BuiltInRegistries.BLOCK_ENTITY_TYPE, (ResourceKey)compressorKey, FabricBlockEntityTypeBuilder.create(CompressorBlockEntity::new, (Block[])new Block[]{ModBlocks.COMPRESSOR}).build());
        ResourceKey alloySmelterKey = ResourceKey.create((ResourceKey)Registries.BLOCK_ENTITY_TYPE, (Identifier)Identifier.fromNamespaceAndPath((String)"exerion", (String)"alloy_smelter"));
        ALLOY_SMELTER_ENTITY = (BlockEntityType)Registry.register((Registry)BuiltInRegistries.BLOCK_ENTITY_TYPE, (ResourceKey)alloySmelterKey, FabricBlockEntityTypeBuilder.create(AlloySmelterBlockEntity::new, (Block[])new Block[]{ModBlocks.ALLOY_SMELTER}).build());
        ResourceKey frequencyEnderChestKey = ResourceKey.create((ResourceKey)Registries.BLOCK_ENTITY_TYPE, (Identifier)Identifier.fromNamespaceAndPath((String)"exerion", (String)"frequency_ender_chest"));
        FREQUENCY_ENDER_CHEST_ENTITY = (BlockEntityType)Registry.register((Registry)BuiltInRegistries.BLOCK_ENTITY_TYPE, (ResourceKey)frequencyEnderChestKey, FabricBlockEntityTypeBuilder.create(FrequencyEnderChestBlockEntity::new, (Block[])new Block[]{ModBlocks.FREQUENCY_ENDER_CHEST}).build());
        ResourceKey frequencyEnderTankKey = ResourceKey.create((ResourceKey)Registries.BLOCK_ENTITY_TYPE, (Identifier)Identifier.fromNamespaceAndPath((String)"exerion", (String)"frequency_ender_tank"));
        FREQUENCY_ENDER_TANK_ENTITY = (BlockEntityType)Registry.register((Registry)BuiltInRegistries.BLOCK_ENTITY_TYPE, (ResourceKey)frequencyEnderTankKey, FabricBlockEntityTypeBuilder.create(FrequencyEnderTankBlockEntity::new, (Block[])new Block[]{ModBlocks.FREQUENCY_ENDER_TANK}).build());
        ResourceKey miningLaserKey = ResourceKey.create((ResourceKey)Registries.BLOCK_ENTITY_TYPE, (Identifier)Identifier.fromNamespaceAndPath((String)"exerion", (String)"mining_laser"));
        MINING_LASER_ENTITY = (BlockEntityType)Registry.register((Registry)BuiltInRegistries.BLOCK_ENTITY_TYPE, (ResourceKey)miningLaserKey, FabricBlockEntityTypeBuilder.create(MiningLaserBlockEntity::new, (Block[])new Block[]{ModBlocks.MINING_LASER}).build());
        ResourceKey geothermalGeneratorKey = ResourceKey.create((ResourceKey)Registries.BLOCK_ENTITY_TYPE, (Identifier)Identifier.fromNamespaceAndPath((String)"exerion", (String)"geothermal_generator"));
        GEOTHERMAL_GENERATOR_ENTITY = (BlockEntityType)Registry.register((Registry)BuiltInRegistries.BLOCK_ENTITY_TYPE, (ResourceKey)geothermalGeneratorKey, FabricBlockEntityTypeBuilder.create(GeothermalGeneratorBlockEntity::new, (Block[])new Block[]{ModBlocks.GEOTHERMAL_GENERATOR}).build());
        ResourceKey electricFurnaceKey = ResourceKey.create((ResourceKey)Registries.BLOCK_ENTITY_TYPE, (Identifier)Identifier.fromNamespaceAndPath((String)"exerion", (String)"electric_furnace"));
        ELECTRIC_FURNACE_ENTITY = (BlockEntityType)Registry.register((Registry)BuiltInRegistries.BLOCK_ENTITY_TYPE, (ResourceKey)electricFurnaceKey, FabricBlockEntityTypeBuilder.create(ElectricFurnaceBlockEntity::new, (Block[])new Block[]{ModBlocks.ELECTRIC_FURNACE}).build());
        ResourceKey shaftKey = ResourceKey.create((ResourceKey)Registries.BLOCK_ENTITY_TYPE, (Identifier)Identifier.fromNamespaceAndPath((String)"exerion", (String)"shaft"));
        SHAFT_ENTITY = (BlockEntityType)Registry.register((Registry)BuiltInRegistries.BLOCK_ENTITY_TYPE, (ResourceKey)shaftKey, FabricBlockEntityTypeBuilder.create(ShaftBlockEntity::new, (Block[])new Block[]{ModBlocks.SHAFT}).build());
        ResourceKey handCrankKey = ResourceKey.create((ResourceKey)Registries.BLOCK_ENTITY_TYPE, (Identifier)Identifier.fromNamespaceAndPath((String)"exerion", (String)"hand_crank"));
        HAND_CRANK_ENTITY = (BlockEntityType)Registry.register((Registry)BuiltInRegistries.BLOCK_ENTITY_TYPE, (ResourceKey)handCrankKey, FabricBlockEntityTypeBuilder.create(HandCrankBlockEntity::new, (Block[])new Block[]{ModBlocks.HAND_CRANK}).build());
        ResourceKey waterWheelKey = ResourceKey.create((ResourceKey)Registries.BLOCK_ENTITY_TYPE, (Identifier)Identifier.fromNamespaceAndPath((String)"exerion", (String)"water_wheel"));
        WATER_WHEEL_ENTITY = (BlockEntityType)Registry.register((Registry)BuiltInRegistries.BLOCK_ENTITY_TYPE, (ResourceKey)waterWheelKey, FabricBlockEntityTypeBuilder.create(WaterWheelBlockEntity::new, (Block[])new Block[]{ModBlocks.WATER_WHEEL}).build());
        ResourceKey mechanicalChuteKey = ResourceKey.create((ResourceKey)Registries.BLOCK_ENTITY_TYPE, (Identifier)Identifier.fromNamespaceAndPath((String)"exerion", (String)"mechanical_chute"));
        MECHANICAL_CHUTE_ENTITY = (BlockEntityType)Registry.register((Registry)BuiltInRegistries.BLOCK_ENTITY_TYPE, (ResourceKey)mechanicalChuteKey, FabricBlockEntityTypeBuilder.create(MechanicalChuteBlockEntity::new, (Block[])new Block[]{ModBlocks.MECHANICAL_CHUTE}).build());
        ResourceKey pumpKey = ResourceKey.create((ResourceKey)Registries.BLOCK_ENTITY_TYPE, (Identifier)Identifier.fromNamespaceAndPath((String)"exerion", (String)"pump"));
        PUMP_ENTITY = (BlockEntityType)Registry.register((Registry)BuiltInRegistries.BLOCK_ENTITY_TYPE, (ResourceKey)pumpKey, FabricBlockEntityTypeBuilder.create(PumpBlockEntity::new, (Block[])new Block[]{ModBlocks.PUMP}).build());
    }
}

