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



    public static CFontRenderer cFontRenderer = new CFontRenderer(new Font("Tahoma", 0 , 20), true, true);

    SimpleDateFormat formatter = new SimpleDateFormat("hh:mm a");

    @SubscribeEvent
    public void onRender(RenderGameOverlayEvent.Post event) {
        if (event.getType() == RenderGameOverlayEvent.ElementType.TEXT) {
            GlStateManager.pushMatrix();
            cFontRenderer.drawStringWithShadow(Craphack.MOD_NAME + " " + Craphack.VERSION, 3,2, Utils.getRGB(8.0F, 1.0F, 0.6F));

            ScaledResolution sr = new ScaledResolution(mc);


            Date date = new Date(System.currentTimeMillis());

            NetworkPlayerInfo np = mc.getConnection().getPlayerInfo(this.mc.player.getName());
            int ping = np != null ? np.getResponseTime() : -1;

            String time = "Time " + formatter.format(date);
            String ping1 = "Ping " + ping;
            String framerate = "FPS " + Minecraft.getDebugFPS();

            for (Module m : Craphack.mods) {
                if (m.name.equalsIgnoreCase("hud")) {
                    if (!m.isEnabled()) {
                        cFontRenderer.drawStringWithShadow("Press RCONTROL to show hacks",3,12,Utils.getRGB(8.0F, 1.0F, 0.6F));
                        //cFontRenderer.drawStringWithShadow("~~~~~~~~~~~~~~~~",3,22,Utils.getRGB(8.0F, 1.0F, 0.6F));
                    }
                }
            }

            int offset_potion = 0;

            if (mc.player.getActivePotionEffects().size() > 0) {
                offset_potion = 24;
            }

            if (mc.player.getActivePotionEffects().size() > 6) {
                offset_potion = 52;
            }

            cFontRenderer.drawStringWithShadow(time, sr.getScaledWidth() - cFontRenderer.getStringWidth(time) - 3, 2 + offset_potion, Utils.getRGB(8.0F, 1.0F, 0.6F));
            cFontRenderer.drawStringWithShadow(ping1, sr.getScaledWidth() - cFontRenderer.getStringWidth(ping1) - 3, 12 + offset_potion, Utils.getRGB(8.0F, 1.0F, 0.6F));
            cFontRenderer.drawStringWithShadow(framerate, sr.getScaledWidth() - cFontRenderer.getStringWidth(framerate) - 3, 22 + offset_potion, Utils.getRGB(8.0F, 1.0F, 0.6F));
            GlStateManager.popMatrix();
        }
    }
}
