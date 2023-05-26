package com.github.koxx12dev.listener;

import com.github.koxx12dev.util.ChatUtil;
import net.minecraft.client.Minecraft;
import net.minecraft.util.EnumChatFormatting;
import net.weavemc.loader.api.event.SubscribeEvent;
import net.weavemc.loader.api.event.TickEvent;

public class UnintendedUsageWarningListener {
    private boolean hasSentWarningForSession = false;


    @SubscribeEvent
    public void sendWarning(TickEvent event) {
        if (hasSentWarningForSession || (System.getProperty("os.name").toLowerCase().contains("windows")) || Minecraft.getMinecraft().thePlayer == null)
            return;
        ChatUtil.addMessage(EnumChatFormatting.RED, "[RawInput] his mod is only intended for Windows. Please remove this mod from your mods folder and relaunch your game as soon as possible, as this mod is dormant when used outside of Windows. §ePlease run §l/modfolder§r§e to open it now.");
        ChatUtil.addMessage(EnumChatFormatting.RED, "[RawInput] You are currently on " + System.getProperty("os.name"));
        //prevent sending warning more than once per session
        hasSentWarningForSession = true;
    }
}
