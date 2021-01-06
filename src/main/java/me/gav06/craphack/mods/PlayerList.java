package me.gav06.craphack.mods;

import me.gav06.craphack.DefaultHUD;
import me.gav06.craphack.Module;
import me.gav06.craphack.Utils;
import me.gav06.craphack.events.Event;
import me.gav06.craphack.events.listeners.EventClientTick;
import me.gav06.craphack.events.listeners.EventRenderGUI;
import me.gav06.craphack.font.CFontRenderer;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.entity.player.EntityPlayer;

import java.util.ArrayList;
import java.util.List;

public class PlayerList extends Module {

    private CFontRenderer cfr = DefaultHUD.cFontRenderer;

    public PlayerList(String name, int key, Category c) {
        super(name, key, c);
    }

    public List<EntityPlayer> list = new ArrayList<EntityPlayer>();

    public void onEvent(Event e) {
        if (e instanceof EventClientTick) {
            list.clear();
            mc.world.loadedEntityList.stream()
                    .filter(entity->entity instanceof EntityPlayer)
                    .filter(entity->entity != mc.player)
                    .forEach(entity->{
                        list.add((EntityPlayer)entity);
                    });
        }

        if (e instanceof EventRenderGUI) {
            ScaledResolution sr = new ScaledResolution(mc);

            int offset = 0;
            int color = 0;
            if (list.size() != 0) {
                for (EntityPlayer plr : list) {
                    try {
                        String name = plr.getName();
                        if (plr.getName().equals(" "))
                            name = "null";

                        String text = "[" + (int) plr.getDistance(mc.player) + "] " + name;
                        cfr.drawStringWithShadow(text, sr.getScaledWidth() - cfr.getStringWidth(text) - 3, 70 + offset, Utils.getRGBWave(8, 1, 0.6F, 50 * color));
                        color += 10;
                        offset += 12;
                    } catch (Exception exp) {
                        exp.printStackTrace();
                    }
                }
            }
        }
    }
}
