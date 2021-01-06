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





            while(var9.hasNext()) {
                Module m = (Module)var9.next();
                if (m.toggled) {
                    cfr.drawStringWithShadow(m.name + ": " + Keyboard.getKeyName(m.getKey()),3,12 + offset,Utils.getRGBWave(6,1,.6f, 20 * offset));
                } else {
                    cfr.drawStringWithShadow(ChatFormatting.GRAY + m.name + ": " + Keyboard.getKeyName(m.getKey()),3,12 + offset,-1);
                }
                offset += 10;
            }
        }

    }


}
