/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.core.Registry
 *  net.minecraft.core.registries.BuiltInRegistries
 *  net.minecraft.core.registries.Registries
 *  net.minecraft.resources.Identifier
 *  net.minecraft.resources.ResourceKey
 *  net.minecraft.world.flag.FeatureFlags
 *  net.minecraft.world.inventory.MenuType
 */
package net.exerion.menu;

import net.exerion.Exerion;
import net.exerion.menu.AlloySmelterMenu;
import net.exerion.menu.BatBoxMenu;
import net.exerion.menu.CoalBoilerMenu;
import net.exerion.menu.CompressorMenu;
import net.exerion.menu.CrusherMenu;
import net.exerion.menu.DigitalStorageTerminalMenu;
import net.exerion.menu.ElectricCrusherMenu;
import net.exerion.menu.ElectricFurnaceMenu;
import net.exerion.menu.ElectricGeneratorMenu;
import net.exerion.menu.FrequencyEnderChestMenu;
import net.exerion.menu.GearPressMenu;
import net.exerion.menu.GeothermalGeneratorMenu;
import net.exerion.menu.MiningLaserMenu;
import net.exerion.menu.ProgressionTerminalMenu;
import net.exerion.menu.SolarPanelMenu;
import net.exerion.menu.SteamTurbineMenu;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.inventory.MenuType;

public class ModMenuTypes {
    public static final MenuType<CoalBoilerMenu> COAL_BOILER_MENU;
    public static final MenuType<ProgressionTerminalMenu> PROGRESSION_TERMINAL_MENU;
    public static final MenuType<SteamTurbineMenu> STEAM_TURBINE_MENU;
    public static final MenuType<CrusherMenu> CRUSHER_MENU;
    public static final MenuType<GearPressMenu> GEAR_PRESS_MENU;
    public static final MenuType<ElectricGeneratorMenu> ELECTRIC_GENERATOR_MENU;
    public static final MenuType<ElectricCrusherMenu> ELECTRIC_CRUSHER_MENU;
    public static final MenuType<SolarPanelMenu> SOLAR_PANEL_MENU;
    public static final MenuType<DigitalStorageTerminalMenu> DIGITAL_STORAGE_TERMINAL_MENU;
    public static final MenuType<BatBoxMenu> BATBOX_MENU;
    public static final MenuType<CompressorMenu> COMPRESSOR_MENU;
    public static final MenuType<AlloySmelterMenu> ALLOY_SMELTER_MENU;
    public static final MenuType<FrequencyEnderChestMenu> FREQUENCY_ENDER_CHEST_MENU;
    public static final MenuType<MiningLaserMenu> MINING_LASER_MENU;
    public static final MenuType<GeothermalGeneratorMenu> GEOTHERMAL_GENERATOR_MENU;
    public static final MenuType<ElectricFurnaceMenu> ELECTRIC_FURNACE_MENU;

    public static void registerMenuTypes() {
        Exerion.LOGGER.info("Registering Menu Types for exerion");
    }

    static {
        ResourceKey coalBoilerKey = ResourceKey.create((ResourceKey)Registries.MENU, (Identifier)Identifier.fromNamespaceAndPath((String)"exerion", (String)"coal_boiler"));
        COAL_BOILER_MENU = (MenuType)Registry.register((Registry)BuiltInRegistries.MENU, (ResourceKey)coalBoilerKey, (Object)new MenuType(CoalBoilerMenu::new, FeatureFlags.DEFAULT_FLAGS));
        ResourceKey terminalKey = ResourceKey.create((ResourceKey)Registries.MENU, (Identifier)Identifier.fromNamespaceAndPath((String)"exerion", (String)"progression_terminal"));
        PROGRESSION_TERMINAL_MENU = (MenuType)Registry.register((Registry)BuiltInRegistries.MENU, (ResourceKey)terminalKey, (Object)new MenuType(ProgressionTerminalMenu::new, FeatureFlags.DEFAULT_FLAGS));
        ResourceKey steamTurbineKey = ResourceKey.create((ResourceKey)Registries.MENU, (Identifier)Identifier.fromNamespaceAndPath((String)"exerion", (String)"steam_turbine"));
        STEAM_TURBINE_MENU = (MenuType)Registry.register((Registry)BuiltInRegistries.MENU, (ResourceKey)steamTurbineKey, (Object)new MenuType(SteamTurbineMenu::new, FeatureFlags.DEFAULT_FLAGS));
        ResourceKey crusherKey = ResourceKey.create((ResourceKey)Registries.MENU, (Identifier)Identifier.fromNamespaceAndPath((String)"exerion", (String)"crusher"));
        CRUSHER_MENU = (MenuType)Registry.register((Registry)BuiltInRegistries.MENU, (ResourceKey)crusherKey, (Object)new MenuType(CrusherMenu::new, FeatureFlags.DEFAULT_FLAGS));
        ResourceKey gearPressKey = ResourceKey.create((ResourceKey)Registries.MENU, (Identifier)Identifier.fromNamespaceAndPath((String)"exerion", (String)"gear_press"));
        GEAR_PRESS_MENU = (MenuType)Registry.register((Registry)BuiltInRegistries.MENU, (ResourceKey)gearPressKey, (Object)new MenuType(GearPressMenu::new, FeatureFlags.DEFAULT_FLAGS));
        ResourceKey electricGeneratorKey = ResourceKey.create((ResourceKey)Registries.MENU, (Identifier)Identifier.fromNamespaceAndPath((String)"exerion", (String)"electric_generator"));
        ELECTRIC_GENERATOR_MENU = (MenuType)Registry.register((Registry)BuiltInRegistries.MENU, (ResourceKey)electricGeneratorKey, (Object)new MenuType(ElectricGeneratorMenu::new, FeatureFlags.DEFAULT_FLAGS));
        ResourceKey electricCrusherKey = ResourceKey.create((ResourceKey)Registries.MENU, (Identifier)Identifier.fromNamespaceAndPath((String)"exerion", (String)"electric_crusher"));
        ELECTRIC_CRUSHER_MENU = (MenuType)Registry.register((Registry)BuiltInRegistries.MENU, (ResourceKey)electricCrusherKey, (Object)new MenuType(ElectricCrusherMenu::new, FeatureFlags.DEFAULT_FLAGS));
        ResourceKey solarPanelKey = ResourceKey.create((ResourceKey)Registries.MENU, (Identifier)Identifier.fromNamespaceAndPath((String)"exerion", (String)"solar_panel"));
        SOLAR_PANEL_MENU = (MenuType)Registry.register((Registry)BuiltInRegistries.MENU, (ResourceKey)solarPanelKey, (Object)new MenuType(SolarPanelMenu::new, FeatureFlags.DEFAULT_FLAGS));
        ResourceKey digitalStorageTerminalKey = ResourceKey.create((ResourceKey)Registries.MENU, (Identifier)Identifier.fromNamespaceAndPath((String)"exerion", (String)"digital_storage_terminal"));
        DIGITAL_STORAGE_TERMINAL_MENU = (MenuType)Registry.register((Registry)BuiltInRegistries.MENU, (ResourceKey)digitalStorageTerminalKey, (Object)new MenuType(DigitalStorageTerminalMenu::new, FeatureFlags.DEFAULT_FLAGS));
        ResourceKey batboxKey = ResourceKey.create((ResourceKey)Registries.MENU, (Identifier)Identifier.fromNamespaceAndPath((String)"exerion", (String)"batbox"));
        BATBOX_MENU = (MenuType)Registry.register((Registry)BuiltInRegistries.MENU, (ResourceKey)batboxKey, (Object)new MenuType(BatBoxMenu::new, FeatureFlags.DEFAULT_FLAGS));
        ResourceKey compressorKey = ResourceKey.create((ResourceKey)Registries.MENU, (Identifier)Identifier.fromNamespaceAndPath((String)"exerion", (String)"compressor"));
        COMPRESSOR_MENU = (MenuType)Registry.register((Registry)BuiltInRegistries.MENU, (ResourceKey)compressorKey, (Object)new MenuType(CompressorMenu::new, FeatureFlags.DEFAULT_FLAGS));
        ResourceKey alloySmelterKey = ResourceKey.create((ResourceKey)Registries.MENU, (Identifier)Identifier.fromNamespaceAndPath((String)"exerion", (String)"alloy_smelter"));
        ALLOY_SMELTER_MENU = (MenuType)Registry.register((Registry)BuiltInRegistries.MENU, (ResourceKey)alloySmelterKey, (Object)new MenuType(AlloySmelterMenu::new, FeatureFlags.DEFAULT_FLAGS));
        ResourceKey frequencyEnderChestKey = ResourceKey.create((ResourceKey)Registries.MENU, (Identifier)Identifier.fromNamespaceAndPath((String)"exerion", (String)"frequency_ender_chest"));
        FREQUENCY_ENDER_CHEST_MENU = (MenuType)Registry.register((Registry)BuiltInRegistries.MENU, (ResourceKey)frequencyEnderChestKey, (Object)new MenuType(FrequencyEnderChestMenu::new, FeatureFlags.DEFAULT_FLAGS));
        ResourceKey miningLaserKey = ResourceKey.create((ResourceKey)Registries.MENU, (Identifier)Identifier.fromNamespaceAndPath((String)"exerion", (String)"mining_laser"));
        MINING_LASER_MENU = (MenuType)Registry.register((Registry)BuiltInRegistries.MENU, (ResourceKey)miningLaserKey, (Object)new MenuType(MiningLaserMenu::new, FeatureFlags.DEFAULT_FLAGS));
        ResourceKey geothermalGeneratorKey = ResourceKey.create((ResourceKey)Registries.MENU, (Identifier)Identifier.fromNamespaceAndPath((String)"exerion", (String)"geothermal_generator"));
        GEOTHERMAL_GENERATOR_MENU = (MenuType)Registry.register((Registry)BuiltInRegistries.MENU, (ResourceKey)geothermalGeneratorKey, (Object)new MenuType(GeothermalGeneratorMenu::new, FeatureFlags.DEFAULT_FLAGS));
        ResourceKey electricFurnaceKey = ResourceKey.create((ResourceKey)Registries.MENU, (Identifier)Identifier.fromNamespaceAndPath((String)"exerion", (String)"electric_furnace"));
        ELECTRIC_FURNACE_MENU = (MenuType)Registry.register((Registry)BuiltInRegistries.MENU, (ResourceKey)electricFurnaceKey, (Object)new MenuType(ElectricFurnaceMenu::new, FeatureFlags.DEFAULT_FLAGS));
    }
}

