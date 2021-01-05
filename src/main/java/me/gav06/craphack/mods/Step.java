package me.gav06.craphack.mods;

import me.gav06.craphack.Module;
import me.gav06.craphack.events.Event;
import me.gav06.craphack.events.listeners.EventClientTick;

public class Step extends Module {
    public Step(String name, int key, Module.Category c) {
        super(name, key, c);
    }

    public void onEvent(Event e) {
        if (e instanceof EventClientTick) {
            this.mc.player.stepHeight = 1.0F;
        }

    }

    public void onDisable() {
        this.mc.player.stepHeight = 0.5F;
    }
}