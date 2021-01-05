package me.gav06.craphack.mods;

import me.gav06.craphack.Module;
import org.lwjgl.Sys;

public class Fullbright extends Module {
    public Fullbright(String name, int key, Category c) {
        super(name, key, c);
    }

    public void onEnable() {
        mc.gameSettings.gammaSetting = 100;
    }

    public void onDisable() {
        mc.gameSettings.gammaSetting = 1;
    }
}
