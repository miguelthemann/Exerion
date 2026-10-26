package net.exerion.client;

import net.exerion.menu.GeothermalGeneratorMenu;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Inventory;

import java.util.List;

public class GeothermalGeneratorScreen extends AbstractContainerScreen<GeothermalGeneratorMenu> {

    public GeothermalGeneratorScreen(GeothermalGeneratorMenu menu, Inventory playerInventory, Component title) {
        super(menu, playerInventory, title, 176, 166);
    }

    @Override
    protected void init() {
        super.init();
        this.titleLabelX = (this.imageWidth - this.font.width(this.title)) / 2;
    }

    @Override
    public void extractContents(GuiGraphicsExtractor graphics, int mouseX, int mouseY, float partialTick) {
        int x = (this.width - this.imageWidth) / 2;
        int y = (this.height - this.imageHeight) / 2;

        // Draw vanilla background
        GuiHelper.drawVanillaBackground(graphics, x, y, this.imageWidth, this.imageHeight);

        // Draw player inventory and hotbar slots
        GuiHelper.drawPlayerInventorySlots(graphics, x, y);

        // Slot backgrounds
        GuiHelper.drawVanillaSlot(graphics, x + 44, y + 35); // Input bucket
        GuiHelper.drawVanillaSlot(graphics, x + 116, y + 35); // Output empty bucket

        // Draw Energy Bar outline (Left side)
        int barX = x + 16;
        int barY = y + 20;
        int barW = 12;
        int barH = 50;

        graphics.fill(barX - 1, barY - 1, barX + barW + 1, barY, 0xFF373737);
        graphics.fill(barX - 1, barY, barX, barY + barH + 1, 0xFF373737);
        graphics.fill(barX, barY, barX + barW, barY + barH, 0xFF8B8B8B);
        graphics.fill(barX, barY + barH, barX + barW + 1, barY + barH + 1, 0xFFFFFFFF);
        graphics.fill(barX + barW, barY, barX + barW + 1, barY + barH, 0xFFFFFFFF);

        int energy = menu.getEnergy();
        int maxEnergy = menu.getMaxEnergy();

        if (energy > 0) {
            double ratio = (double) energy / maxEnergy;
            int fillH = (int) (barH * ratio);
            
            for (int i = 0; i < fillH; i++) {
                int currentY = barY + barH - 1 - i;
                float pct = (float) i / barH;
                int color = interpolateColor(0xFFFFEA00, 0xFFFF3D00, pct); // Hot red-orange to bright yellow
                graphics.fill(barX, currentY, barX + barW, currentY + 1, color);
            }
        }

        // Draw Lava Buffer Tank outline (Right side)
        int tankX = x + 148;
        int tankY = y + 20;
        int tankW = 12;
        int tankH = 50;

        graphics.fill(tankX - 1, tankY - 1, tankX + tankW + 1, tankY, 0xFF373737);
        graphics.fill(tankX - 1, tankY, tankX, tankY + tankH + 1, 0xFF373737);
        graphics.fill(tankX, tankY, tankX + tankW, tankY + tankH, 0xFF8B8B8B);
        graphics.fill(tankX, tankY + tankH, tankX + tankW + 1, tankY + tankH + 1, 0xFFFFFFFF);
        graphics.fill(tankX + tankW, tankY, tankX + tankW + 1, tankY + tankH, 0xFFFFFFFF);

        int lava = menu.getLavaBuffer();
        int maxLava = menu.getMaxLava();

        if (lava > 0) {
            double ratio = (double) lava / maxLava;
            int fillH = (int) (tankH * ratio);
            
            for (int i = 0; i < fillH; i++) {
                int currentY = tankY + tankH - 1 - i;
                float pct = (float) i / tankH;
                int color = interpolateColor(0xFFFF3D00, 0xFF7F0000, pct); // Lava magma red gradient
                graphics.fill(tankX, currentY, tankX + tankW, currentY + 1, color);
            }
        }

        // Hover Tooltips
        int rx = mouseX - x;
        int ry = mouseY - y;
        // Energy Bar Hover
        if (rx >= 15 && rx <= 29 && ry >= 19 && ry <= 71) {
            drawCustomTooltip(graphics, mouseX, mouseY, List.of(
                "§cGerador Geotérmico",
                "§eEnergia: §f" + energy + " / " + maxEnergy + " EU",
                "§7Geração: 20 EU/t (enquanto ativo)",
                "§7Distribui energia por cabos adjacentes"
            ));
        }
        // Lava Tank Hover
        if (rx >= 147 && rx <= 161 && ry >= 19 && ry <= 71) {
            drawCustomTooltip(graphics, mouseX, mouseY, List.of(
                "§6Tanque de Lava",
                "§fLava: §e" + lava + " / " + maxLava + " mB",
                "§7Consumo: 1 mB/t",
                "§7Absorve blocos de lava fonte adjacentes!"
            ));
        }

        super.extractContents(graphics, mouseX, mouseY, partialTick);
    }

    @Override
    protected void extractLabels(GuiGraphicsExtractor graphics, int mouseX, int mouseY) {
        graphics.text(this.font, this.title, this.titleLabelX, 6, 0xFF404040, false);
        graphics.text(this.font, this.playerInventoryTitle, 8, this.imageHeight - 94, 0xFF404040, false);

        graphics.text(this.font, Component.literal("Lava"), 42, 24, 0xFF404040, false);
        graphics.text(this.font, Component.literal("Baldes Vazios"), 104, 24, 0xFF404040, false);
    }

    private int interpolateColor(int color1, int color2, float fraction) {
        int r1 = (color1 >> 16) & 0xFF;
        int g1 = (color1 >> 8) & 0xFF;
        int b1 = color1 & 0xFF;

        int r2 = (color2 >> 16) & 0xFF;
        int g2 = (color2 >> 8) & 0xFF;
        int b2 = color2 & 0xFF;

        int r = (int) (r1 + (r2 - r1) * fraction);
        int g = (int) (g1 + (g2 - g1) * fraction);
        int b = (int) (b1 + (b2 - b1) * fraction);

        return (0xFF << 24) | (r << 16) | (g << 8) | b;
    }

    private void drawCustomTooltip(GuiGraphicsExtractor graphics, int mouseX, int mouseY, List<String> textLines) {
        int padding = 6;
        int lineHeight = 10;
        int tooltipW = 0;
        for (String line : textLines) {
            int w = this.font.width(line);
            if (w > tooltipW) {
                tooltipW = w;
            }
        }
        tooltipW += padding * 2;
        int tooltipH = textLines.size() * lineHeight + padding * 2 - 2;

        int tx = mouseX + 12;
        int ty = mouseY - 12;

        if (tx + tooltipW > this.width) {
            tx = mouseX - tooltipW - 8;
        }
        if (ty + tooltipH > this.height) {
            ty = this.height - tooltipH - 8;
        }

        graphics.fill(tx - 1, ty - 1, tx + tooltipW + 1, ty + tooltipH + 1, 0xFFFF5722); // Magma orange glow
        graphics.fill(tx, ty, tx + tooltipW, ty + tooltipH, 0xFA200A05); // Magma dark background

        int currentY = ty + padding;
        for (String line : textLines) {
            graphics.text(this.font, line, tx + padding, currentY, 0xFFFFFFFF, true);
            currentY += lineHeight;
        }
    }
}
