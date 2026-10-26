package net.exerion.compat.jei;

import java.util.ArrayList;
import java.util.List;
import net.exerion.block.ModBlocks;
import net.exerion.compat.jei.CrusherJeiRecipe;
import net.exerion.item.ModItems;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.ItemLike;

public class ExerionRecipeMaker {
    public static List<CrusherJeiRecipe> getCrusherRecipes() {
        ArrayList<CrusherJeiRecipe> list = new ArrayList<CrusherJeiRecipe>();
        list.add(new CrusherJeiRecipe(new ItemStack((ItemLike)Items.RAW_IRON), new ItemStack((ItemLike)ModItems.IRON_DUST, 2)));
        list.add(new CrusherJeiRecipe(new ItemStack((ItemLike)Items.RAW_COPPER), new ItemStack((ItemLike)ModItems.COPPER_DUST, 2)));
        list.add(new CrusherJeiRecipe(new ItemStack((ItemLike)Items.IRON_INGOT), new ItemStack((ItemLike)ModItems.IRON_DUST)));
        list.add(new CrusherJeiRecipe(new ItemStack((ItemLike)Items.COPPER_INGOT), new ItemStack((ItemLike)ModItems.COPPER_DUST)));
        list.add(new CrusherJeiRecipe(new ItemStack((ItemLike)Items.COAL), new ItemStack((ItemLike)ModItems.COAL_DUST)));
        list.add(new CrusherJeiRecipe(new ItemStack((ItemLike)Items.IRON_ORE), new ItemStack((ItemLike)Items.RAW_IRON, 2)));
        list.add(new CrusherJeiRecipe(new ItemStack((ItemLike)Items.DEEPSLATE_IRON_ORE), new ItemStack((ItemLike)Items.RAW_IRON, 2)));
        list.add(new CrusherJeiRecipe(new ItemStack((ItemLike)Items.COPPER_ORE), new ItemStack((ItemLike)Items.RAW_COPPER, 2)));
        list.add(new CrusherJeiRecipe(new ItemStack((ItemLike)Items.DEEPSLATE_COPPER_ORE), new ItemStack((ItemLike)Items.RAW_COPPER, 2)));
        list.add(new CrusherJeiRecipe(new ItemStack(ModBlocks.EXERION_ORE), new ItemStack((ItemLike)ModItems.RAW_EXERION, 2)));
        list.add(new CrusherJeiRecipe(new ItemStack(ModBlocks.DEEPSLATE_EXERION_ORE), new ItemStack((ItemLike)ModItems.RAW_EXERION, 2)));
        return list;
    }
}

