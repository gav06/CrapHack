package me.gav06.craphack.mods;

import me.gav06.craphack.Module;
import me.gav06.craphack.events.Event;
import me.gav06.craphack.events.listeners.EventClientTick;
import net.minecraft.network.play.client.CPacketPlayer;

public class NoFall extends Module {
    public NoFall(String name, int key, Module.Category c) {
        super(name, key, c);
    }

    public void onEvent(Event e) {
        if (e instanceof EventClientTick && this.mc.player.fallDistance > 2.0F) {
            this.mc.getConnection().sendPacket(new CPacketPlayer(true));
        }

    }
}