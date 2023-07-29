package com.github.koxx12dev.util;

import net.minecraft.client.Minecraft;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.IChatComponent;

public class ChatUtil {
    public static void addMessage(IChatComponent message) {
        if (Minecraft.getMinecraft().thePlayer != null) {
            Minecraft.getMinecraft().thePlayer.addChatMessage(message);
        }
    }

    public static void addMessage(String message) {
        addMessage(new ChatComponentText(message));
    }

    public static void addMessage(EnumChatFormatting color, String message) {
        addMessage(color + message);
    }
}
