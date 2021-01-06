package me.gav06.craphack.mods;

import me.gav06.craphack.DefaultHUD;
import me.gav06.craphack.Module;
import me.gav06.craphack.Utils;
import me.gav06.craphack.events.Event;
import me.gav06.craphack.events.listeners.EventRenderGUI;
import me.gav06.craphack.font.CFontRenderer;
import net.minecraft.client.gui.ScaledResolution;

public class Coords extends Module {

    private CFontRenderer cfr = DefaultHUD.cFontRenderer;

    public Coords(String name, int key, Category c) {
        super(name, key, c);
    }

    public void onEvent(Event e) {
        if (e instanceof EventRenderGUI) {
            ScaledResolution sr = new ScaledResolution(mc);
            String mainText = "Position " + Utils.round(this.mc.player.posX, 1) + ", " + Utils.round(this.mc.player.posY, 1) + ", " + Utils.round(this.mc.player.posZ, 1);
            //String dim_0 = "Nether XZ" + Utils.round(this.mc.player.posX / 8, 1) + ", " + Utils.round(this.mc.player.posZ / 8, 1);
            //String dim_neg1 = "Overworld XZ" + Utils.round(this.mc.player.posX * 8, 1) + ", " + Utils.round(this.mc.player.posZ * 8, 1);
            int offset = 0;
            cfr.drawStringWithShadow(mainText, sr.getScaledWidth() - cfr.getStringWidth(mainText) - 3, sr.getScaledHeight() - cfr.getHeight() - 2 - offset, Utils.getRGB(8.0F, 1.0F, 0.6F));

            String nether_shit = "Nether " + Utils.round(mc.player.posX / 8, 1) + ", " + Utils.round(mc.player.posZ / 8, 1);
            String overworld_shit = "Overworld " + Utils.round(mc.player.posX * 8, 1) + ", " + Utils.round(mc.player.posZ * 8, 1);
            switch (mc.player.dimension) {
                case 0:
                    offset = 22;
                    cfr.drawStringWithShadow(nether_shit,
                            sr.getScaledWidth() - cfr.getStringWidth(nether_shit) - 3, sr.getScaledHeight() - cfr.getHeight() - 12, Utils.getRGB(8.0F, 1.0F, 0.6F));
                    break;
                case -1:
                    offset = 22;
                    cfr.drawStringWithShadow(overworld_shit, sr.getScaledWidth() - cfr.getStringWidth(nether_shit) - 3,
                            sr.getScaledHeight() - cfr.getHeight() - 12, Utils.getRGB(8.0F, 1.0F, 0.6F));
                    break;
                case 1:
                    offset = 12;
                    break;
            }
        }
    }

    private String getMultipliedCoordsByDimension() {
        double x = this.mc.player.posX;
        double z = this.mc.player.posZ;
        String thing;
        switch(this.mc.player.dimension) {
            case 0:
                thing = "(" + Utils.round(x * 8.0D, 1) + ", " + Utils.round(z * 8.0D, 1) + ")";
            case 1:
                thing = "";
            case -1:
                thing = "(" + Utils.round(x / 8.0D, 1) + ", " + Utils.round(z / 8.0D, 1) + ")";
                break;
            default:
                thing = "error";
        }

        return thing;
    }
}
