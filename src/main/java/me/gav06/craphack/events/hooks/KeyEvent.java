package me.gav06.craphack.events.hooks;

import me.gav06.craphack.Craphack;
import me.gav06.craphack.Module;
import net.minecraft.client.Minecraft;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent;
import org.lwjgl.input.Keyboard;

import java.util.Iterator;

public class KeyEvent {
    int key;
    Minecraft mc = Minecraft.getMinecraft();

    @SubscribeEvent
    public void onKey(InputEvent.KeyInputEvent event) {
        if (Keyboard.getEventKeyState()) {
            this.key = Keyboard.getEventKey();
            Iterator var2 = Craphack.mods.iterator();

            while(var2.hasNext()) {
                Module m = (Module)var2.next();
                if (m.getKey() == this.key) {
                    m.toggle();
                }
            }
        }

    }
}