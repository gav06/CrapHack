package me.gav06.craphack.mods;

import com.mojang.realmsclient.gui.ChatFormatting;
import me.gav06.craphack.Craphack;
import me.gav06.craphack.DefaultHUD;
import me.gav06.craphack.Module;
import me.gav06.craphack.Utils;
import me.gav06.craphack.events.Event;
import me.gav06.craphack.events.listeners.EventRenderGUI;
import me.gav06.craphack.font.CFontRenderer;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.network.NetworkPlayerInfo;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.input.Keyboard;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;

public class HUD extends Module {


    public HUD(String name, int key, Module.Category c) {
        super(name, key, c);
        this.toggled = false;
    }

    private CFontRenderer cfr = DefaultHUD.cFontRenderer;

    public void onEvent(Event e) {
        if (e instanceof EventRenderGUI) {
            ScaledResolution sr = new ScaledResolution(this.mc);
            int offset = 0;
            Iterator var9 = Craphack.mods.iterator();

            SimpleDateFormat formatter = new SimpleDateFormat("hh:mm a");
            Date date = new Date(System.currentTimeMillis());

            NetworkPlayerInfo np = mc.getConnection().getPlayerInfo(this.mc.player.getName());
            int ping = np != null ? np.getResponseTime() : -1;

            String time = "Time " + formatter.format(date);
            String ping1 = "Ping " + ping;
            String framerate = "FPS " + Minecraft.getDebugFPS();

            int offset_potion = 0;

            if (mc.player.getActivePotionEffects().size() > 0) {
                offset_potion = 24;
            }

            if (mc.player.getActivePotionEffects().size() > 6) {
                offset_potion = 52;
            }

            cfr.drawStringWithShadow(time, sr.getScaledWidth() - cfr.getStringWidth(time) - 3, 2 + offset_potion, Utils.getRGB(8.0F, 1.0F, 0.6F));
            cfr.drawStringWithShadow(ping1, sr.getScaledWidth() - cfr.getStringWidth(ping1) - 3, 12 + offset_potion, Utils.getRGB(8.0F, 1.0F, 0.6F));
            cfr.drawStringWithShadow(framerate, sr.getScaledWidth() - cfr.getStringWidth(framerate) - 3, 22 + offset_potion, Utils.getRGB(8.0F, 1.0F, 0.6F));

            while(var9.hasNext()) {
                Module m = (Module)var9.next();
                if (m.toggled) {
                    cfr.drawStringWithShadow(m.name + ": " + Keyboard.getKeyName(m.getKey()),3,12 + offset,Utils.getRGBWave(6,1,.6f, 50 * offset));
                } else {
                    cfr.drawStringWithShadow(ChatFormatting.GRAY + m.name + ": " + Keyboard.getKeyName(m.getKey()),3,12 + offset,-1);
                }
                offset += 10;
            }
        }

    }


}
