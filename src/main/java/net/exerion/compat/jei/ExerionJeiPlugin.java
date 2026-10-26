/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  mezz.jei.api.IModPlugin
 *  mezz.jei.api.JeiPlugin
 *  mezz.jei.api.recipe.category.IRecipeCategory
 *  mezz.jei.api.recipe.types.IRecipeType
 *  mezz.jei.api.registration.IRecipeCatalystRegistration
 *  mezz.jei.api.registration.IRecipeCategoryRegistration
 *  mezz.jei.api.registration.IRecipeRegistration
 *  net.minecraft.resources.Identifier
 *  net.minecraft.world.item.ItemStack
 *  net.minecraft.world.level.ItemLike
 */
package net.exerion.compat.jei;

import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.recipe.category.IRecipeCategory;
import mezz.jei.api.recipe.types.IRecipeType;
import mezz.jei.api.registration.IRecipeCatalystRegistration;
import mezz.jei.api.registration.IRecipeCategoryRegistration;
import mezz.jei.api.registration.IRecipeRegistration;
import net.exerion.block.ModBlocks;
import net.exerion.compat.jei.CrusherRecipeCategory;
import net.exerion.compat.jei.ExerionRecipeMaker;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.ItemLike;

@JeiPlugin
public class ExerionJeiPlugin
implements IModPlugin {
    public Identifier getPluginUid() {
        return Identifier.fromNamespaceAndPath((String)"exerion", (String)"jei_plugin");
    }

    public void registerCategories(IRecipeCategoryRegistration registration) {
        registration.addRecipeCategories(new IRecipeCategory[]{new CrusherRecipeCategory(registration.getJeiHelpers().getGuiHelper())});
    }

    public void registerRecipes(IRecipeRegistration registration) {
        registration.addRecipes(CrusherRecipeCategory.TYPE, ExerionRecipeMaker.getCrusherRecipes());
    }

    public void registerRecipeCatalysts(IRecipeCatalystRegistration registration) {
        registration.addRecipeCatalyst(new ItemStack((ItemLike)ModBlocks.CRUSHER), new IRecipeType[]{CrusherRecipeCategory.TYPE});
        registration.addRecipeCatalyst(new ItemStack((ItemLike)ModBlocks.ELECTRIC_CRUSHER), new IRecipeType[]{CrusherRecipeCategory.TYPE});
    }
}

