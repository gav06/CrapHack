package me.gav06.craphack;

import me.gav06.craphack.font.CFontRenderer;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.network.NetworkPlayerInfo;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DefaultHUD {

    Minecraft mc = Minecraft.getMinecraft();



    public static CFontRenderer cFontRenderer = new CFontRenderer(new Font("Whitney", 0 , 20), true, true);

    @SubscribeEvent
    public void onRender(RenderGameOverlayEvent.Post event) {
        if (event.getType() == RenderGameOverlayEvent.ElementType.TEXT) {
            GlStateManager.pushMatrix();
            cFontRenderer.drawStringWithShadow(Craphack.MOD_NAME + " " + Craphack.VERSION, 3,2, Utils.getRGB(8.0F, 1.0F, 0.6F));

            ScaledResolution sr = new ScaledResolution(mc);


            for (Module m : Craphack.mods) {
                if (m.name.equalsIgnoreCase("hud")) {
                    if (!m.isEnabled()) {
                        cFontRenderer.drawStringWithShadow("Press RCONTROL to show hacks",3,12,Utils.getRGB(8.0F, 1.0F, 0.6F));
                        cFontRenderer.drawStringWithShadow("~~~~~~~~~~~~~~~~",3,22,Utils.getRGB(8.0F, 1.0F, 0.6F));
                    }
                }
            }
            GlStateManager.popMatrix();
        }
    }
}
