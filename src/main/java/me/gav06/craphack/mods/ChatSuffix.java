package me.gav06.craphack.mods;

import me.gav06.craphack.Module;
import net.minecraftforge.client.event.ClientChatEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import org.apache.commons.lang3.StringEscapeUtils;

public class ChatSuffix extends Module {
    public ChatSuffix(String name, int key, Category c) {
        super(name, key, c);
    }

    @SubscribeEvent
    public void onChat(ClientChatEvent event) {
        String beta_suffix = StringEscapeUtils.unescapeCsv(" \uff5c \u1d04\u0280\u1d00\u1d18\u029c\u1d00\u1d04\u1d0b \u0299\u1d07\u1d1b\u1d00");
        String suffix = StringEscapeUtils.unescapeCsv(" \uff5c \u1d04\u0280\u1d00\u1d18\u029c\u1d00\u1d04\u1d0b");
        String msg = event.getMessage();
        if (!msg.startsWith("/") || !msg.startsWith(".")) {
            event.setMessage(msg.concat(suffix));
        }
    }
}
