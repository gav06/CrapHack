package me.gav06.craphack;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.ScaledResolution;

import java.awt.*;

public class Utils {
    public static int getRGBWave(float seconds, float brightness, float saturation, long index) {
        float hue = (float)((System.currentTimeMillis() + index) % (long)((int)(seconds * 1000.0F))) / (seconds * 1000.0F);
        int color = Color.HSBtoRGB(hue, saturation, brightness);
        return color;
    }

    public static int getRGB(float seconds, float brightness, float saturation) {
        float hue = (float)(System.currentTimeMillis() % (long)((int)(seconds * 1000.0F))) / (seconds * 1000.0F);
        int color = Color.HSBtoRGB(hue, saturation, brightness);
        return color;
    }

    public static double round(double value, int places) {
        if (places < 0) {
            throw new IllegalArgumentException();
        } else {
            long factor = (long)Math.pow(10.0D, (double)places);
            value *= (double)factor;
            long tmp = Math.round(value);
            return (double)tmp / (double)factor;
        }
    }

    public static void drawTextWithBackdrop(String text, int x, int y, int color) {
        FontRenderer fr = Minecraft.getMinecraft().fontRenderer;
        ScaledResolution sr = new ScaledResolution(Minecraft.getMinecraft());
        int w = sr.getScaledWidth();
        int h = sr.getScaledHeight();
        Gui.drawRect(x - 2, y - 1, x + fr.getStringWidth(text) + 1, y + fr.FONT_HEIGHT, Integer.MIN_VALUE);
        fr.drawString(text, x, y, color);
    }

    public static int clamp(int var, int min, int max) {
        if (var < min) {
            var = min;
        }

        if (var > max) {
            var = max;
        }

        return var;
    }
}