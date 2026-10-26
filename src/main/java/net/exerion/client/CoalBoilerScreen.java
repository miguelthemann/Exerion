package net.exerion.client;

import net.exerion.client.GuiHelper;
import net.exerion.menu.CoalBoilerMenu;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.FormattedText;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.AbstractContainerMenu;

public class CoalBoilerScreen
extends AbstractContainerScreen<CoalBoilerMenu> {
    public CoalBoilerScreen(CoalBoilerMenu menu, Inventory playerInventory, Component title) {
        super(menu, playerInventory, title, 176, 166);
    }

    protected void init() {
        super.init();
        this.titleLabelX = (this.imageWidth - this.font.width((FormattedText)this.title)) / 2;
    }

    public void extractContents(GuiGraphicsExtractor graphics, int mouseX, int mouseY, float partialTick) {
        int steamTimer;
        int x = (this.width - this.imageWidth) / 2;
        int y = (this.height - this.imageHeight) / 2;
        GuiHelper.drawVanillaBackground(graphics, x, y, this.imageWidth, this.imageHeight);
        GuiHelper.drawPlayerInventorySlots(graphics, x, y);
        GuiHelper.drawVanillaSlot(graphics, x + 56, y + 17);
        GuiHelper.drawVanillaSlot(graphics, x + 56, y + 53);
        GuiHelper.drawVanillaSlot(graphics, x + 116, y + 35);
        int flameX = x + 80;
        int flameY = y + 30;
        graphics.fill(flameX + 3, flameY, flameX + 5, flameY + 2, -22016);
        graphics.fill(flameX + 2, flameY + 2, flameX + 6, flameY + 4, -35072);
        graphics.fill(flameX + 1, flameY + 4, flameX + 7, flameY + 7, -43776);
        graphics.fill(flameX + 2, flameY + 5, flameX + 6, flameY + 7, -56832);
        graphics.fill(x + 79, y + 35, x + 113, y + 37, -7631989);
        int burnTime = ((CoalBoilerMenu)this.menu).getBurnTime();
        if (burnTime > 0) {
            graphics.fill(flameX + 3, flameY - 2, flameX + 5, flameY, -256);
        }
        if ((steamTimer = ((CoalBoilerMenu)this.menu).getSteamTimer()) > 0) {
            int progressW = steamTimer * 34 / 40;
            graphics.fill(x + 79, y + 35, x + 79 + progressW, y + 37, -16733628);
        }
        super.extractContents(graphics, mouseX, mouseY, partialTick);
    }

    protected void extractLabels(GuiGraphicsExtractor graphics, int mouseX, int mouseY) {
        graphics.text(this.font, this.title, this.titleLabelX, 4, -12566464, false);
        graphics.text(this.font, this.playerInventoryTitle, 8, this.imageHeight - 94, -12566464, false);
        graphics.text(this.font, (Component)Component.literal((String)"Fuel"), 56, 8, -8762880, false);
        graphics.text(this.font, (Component)Component.literal((String)"Water"), 56, 44, -16755303, false);
        graphics.text(this.font, (Component)Component.literal((String)"Out"), 116, 26, -16742332, false);
    }
}

