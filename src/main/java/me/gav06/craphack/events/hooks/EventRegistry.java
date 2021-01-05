package me.gav06.craphack.events.hooks;

import me.gav06.craphack.Craphack;
import me.gav06.craphack.events.listeners.EventClientTick;
import me.gav06.craphack.events.listeners.EventRenderGUI;
import net.minecraft.client.Minecraft;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

public class EventRegistry {

    Minecraft mc = Minecraft.getMinecraft();

    @SubscribeEvent
    public void onRenderGUI(RenderGameOverlayEvent.Post event) {
        if (event.getType() == RenderGameOverlayEvent.ElementType.TEXT && !mc.gameSettings.showDebugInfo) {
            Craphack.onEvent(new EventRenderGUI());
        }
    }

    @SubscribeEvent
    public void onClientTick(TickEvent.ClientTickEvent event) {
        if (mc.player == null || mc.world == null)
            return;
        Craphack.onEvent(new EventClientTick());
    }
}
