/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.gui.GuiGraphicsExtractor
 *  net.minecraft.client.gui.screens.inventory.AbstractContainerScreen
 *  net.minecraft.network.chat.Component
 *  net.minecraft.network.chat.FormattedText
 *  net.minecraft.world.entity.player.Inventory
 *  net.minecraft.world.inventory.AbstractContainerMenu
 */
package net.exerion.client;

import net.exerion.client.GuiHelper;
import net.exerion.menu.GearPressMenu;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.FormattedText;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.AbstractContainerMenu;

public class GearPressScreen
extends AbstractContainerScreen<GearPressMenu> {
    public GearPressScreen(GearPressMenu menu, Inventory playerInventory, Component title) {
        super((AbstractContainerMenu)menu, playerInventory, title, 176, 166);
    }

    protected void init() {
        super.init();
        this.titleLabelX = (this.imageWidth - this.font.width((FormattedText)this.title)) / 2;
    }

    public void extractContents(GuiGraphicsExtractor graphics, int mouseX, int mouseY, float partialTick) {
        int x = (this.width - this.imageWidth) / 2;
        int y = (this.height - this.imageHeight) / 2;
        GuiHelper.drawVanillaBackground(graphics, x, y, this.imageWidth, this.imageHeight);
        GuiHelper.drawPlayerInventorySlots(graphics, x, y);
        GuiHelper.drawVanillaSlot(graphics, x + 56, y + 35);
        GuiHelper.drawVanillaSlot(graphics, x + 116, y + 35);
        GuiHelper.drawVanillaSlot(graphics, x + 152, y + 17);
        int pressX = x + 82;
        int pressY = y + 25;
        int pressW = 10;
        int pressMaxTravel = 20;
        graphics.fill(pressX - 4, pressY, pressX - 2, pressY + pressMaxTravel + 10, -10857393);
        graphics.fill(pressX + pressW + 2, pressY, pressX + pressW + 4, pressY + pressMaxTravel + 10, -10857393);
        graphics.fill(pressX - 6, pressY + pressMaxTravel + 8, pressX + pressW + 6, pressY + pressMaxTravel + 10, -13158601);
        int currentTravel = 0;
        int pressTime = ((GearPressMenu)this.menu).getPressTime();
        int maxPressTime = ((GearPressMenu)this.menu).getMaxPressTime();
        if (pressTime > 0) {
            double ratio = (double)pressTime / (double)maxPressTime;
            currentTravel = (int)((double)pressMaxTravel * ratio);
        }
        graphics.fill(pressX + 3, pressY, pressX + 7, pressY + currentTravel, -8555152);
        graphics.fill(pressX - 2, pressY + currentTravel, pressX + pressW + 2, pressY + currentTravel + 6, -4026834);
        graphics.fill(pressX - 1, pressY + currentTravel + 1, pressX + pressW + 1, pressY + currentTravel + 3, -1726148);
        super.extractContents(graphics, mouseX, mouseY, partialTick);
    }

    protected void extractLabels(GuiGraphicsExtractor graphics, int mouseX, int mouseY) {
        graphics.text(this.font, this.title, this.titleLabelX, 4, -12566464, false);
        graphics.text(this.font, this.playerInventoryTitle, 8, this.imageHeight - 94, -12566464, false);
        graphics.text(this.font, (Component)Component.literal((String)"Ingot"), 46, 23, -11184811, false);
        graphics.text(this.font, (Component)Component.literal((String)"Pressed"), 108, 23, -7709440, false);
    }
}

