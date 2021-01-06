package me.gav06.craphack.mods;

import me.gav06.craphack.Module;
import me.gav06.craphack.events.Event;
import me.gav06.craphack.events.listeners.EventClientTick;
import net.minecraft.client.Minecraft;
import net.minecraftforge.fml.common.ObfuscationReflectionHelper;

public class FastPlace extends Module {
    public FastPlace(String name, int key, Category c) {
        super(name, key, c);
    }

    public void onEvent(Event e) {
        if (e instanceof EventClientTick) {
            ObfuscationReflectionHelper.setPrivateValue(Minecraft.class, mc, 0, "rightClickDelayTimer", "field_71467_ac");
        }
    }

}
