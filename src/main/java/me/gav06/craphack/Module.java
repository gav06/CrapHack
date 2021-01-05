package me.gav06.craphack;

import me.gav06.craphack.events.Event;
import net.minecraft.client.Minecraft;
import net.minecraftforge.common.MinecraftForge;

public class Module {
    public String name;
    public boolean toggled;
    public int keyCode;
    public Minecraft mc = Minecraft.getMinecraft();
    public Module.Category cat;

    public Module(String name, int key, Module.Category c) {
        this.name = name;
        this.keyCode = key;
        this.cat = c;
    }

    public boolean isEnabled() {
        return this.toggled;
    }

    public int getKey() {
        return this.keyCode;
    }

    public void toggle() {
        this.toggled = !this.toggled;
        if (this.toggled) {
            this.onEnable();
        } else {
            this.onDisable();
        }

    }

    public void onEnable() {
        MinecraftForge.EVENT_BUS.register(this);
    }

    public void onDisable() {
        MinecraftForge.EVENT_BUS.unregister(this);
    }

    public void onEvent(Event e) {
    }

    public static enum Category {
        RENDER,
        PLAYER,
        COMBAT,
        WORLD;
    }
}