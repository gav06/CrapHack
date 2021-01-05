package me.gav06.craphack.mods;

import me.gav06.craphack.Module;
import me.gav06.craphack.events.Event;
import me.gav06.craphack.events.listeners.EventClientTick;
import net.minecraft.client.settings.KeyBinding;

public class BHop extends Module {
    public BHop(String name, int key, Module.Category c) {
        super(name, key, c);
    }

    public void onEvent(Event e) {
        if (e instanceof EventClientTick) {
            if (this.mc.player.moveForward > 0.0F) {
                this.mc.player.setSprinting(true);
            }

            if (this.mc.gameSettings.keyBindForward.isKeyDown()) {
                KeyBinding.setKeyBindState(this.mc.gameSettings.keyBindJump.getKeyCode(), true);
            }
        }

    }

    public void onDisable() {
        KeyBinding.setKeyBindState(this.mc.gameSettings.keyBindJump.getKeyCode(), false);
    }
}