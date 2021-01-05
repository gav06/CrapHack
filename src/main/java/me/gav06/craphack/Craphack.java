package me.gav06.craphack;

import me.gav06.craphack.events.Event;
import me.gav06.craphack.events.hooks.EventRegistry;
import me.gav06.craphack.events.hooks.KeyEvent;
import me.gav06.craphack.mods.*;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.Display;

import java.util.Collections;
import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;

@Mod(
        modid = "craphack",
        name = "CrapHack",
        version = "1.1",
        clientSideOnly = true
)
public class Craphack {
    public static final String MOD_ID = "craphack";
    public static final String MOD_NAME = "CrapHack";
    public static final String VERSION = "1.2";
    @Mod.Instance("craphack")
    public static Craphack INSTANCE;
    public static CopyOnWriteArrayList<Module> mods = new CopyOnWriteArrayList();

    public static void onEvent(Event e) {
        Iterator var1 = mods.iterator();

        while(var1.hasNext()) {
            Module m = (Module)var1.next();
            if (m.toggled) {
                m.onEvent(e);
            }
        }

    }

    @Mod.EventHandler
    public void preinit(FMLPreInitializationEvent event) {
        Display.setTitle(MOD_NAME + " " + VERSION);

        mods.add(new Step("Step", Keyboard.KEY_M, Module.Category.WORLD));
        mods.add(new NoFall("NoFall", Keyboard.KEY_N, Module.Category.WORLD));
        mods.add(new HUD("HUD", Keyboard.KEY_RCONTROL, Module.Category.RENDER));
        mods.add(new BHop("BHop", Keyboard.KEY_B, Module.Category.WORLD));
        mods.add(new Coords("Coords", Keyboard.KEY_V, Module.Category.RENDER));
        mods.add(new Fullbright("Fullbright", Keyboard.KEY_H, Module.Category.RENDER));

        Collections.sort(mods, this::compareTo);
        //mods.add(new FakePlayer("FakePlayer", 25, Module.Category.PLAYER));

        //mods.add(new CreativeFly("CreativeFly", 47, Module.Category.WORLD));
        //mods.add(new Sprint("Sprint", 35, Module.Category.WORLD));
        //mods.add(new BHop("BHop", 34, Module.Category.WORLD));
        //mods.add(new Fullbright("Fullbright", 23, Module.Category.RENDER));
        //mods.add(new AntiFog("AntiFog", 22, Module.Category.RENDER));
        //mods.add(new ChatSuffix("ChatSuffix", 21, Module.Category.PLAYER));
    }

    public static FontRenderer fr;

    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
        MinecraftForge.EVENT_BUS.register(new KeyEvent());
        MinecraftForge.EVENT_BUS.register(new EventRegistry());
        MinecraftForge.EVENT_BUS.register(new DefaultHUD());

        Minecraft.getMinecraft().gameSettings.gammaSetting = 1;
    }

    public int compareTo(Module firstMod, Module secondMod) {
        if (firstMod.name.compareTo(secondMod.name) < 0) {
            return -1;
        } else if (firstMod.name.compareTo(secondMod.name) > 0) {
            return 1;
        } else {
            return 0;
        }
    }
}