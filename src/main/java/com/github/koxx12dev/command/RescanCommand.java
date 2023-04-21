package com.github.koxx12dev.command;

import club.maxstats.weave.loader.api.command.Command;
import com.github.koxx12dev.RawInput;
import com.github.koxx12dev.util.ChatUtil;
import net.minecraft.client.Minecraft;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;

public class RescanCommand extends Command {


    public RescanCommand() {
        super("rescan");
    }

    @Override
    public void handle(String[] args) {
        ChatUtil.addMessage(EnumChatFormatting.GOLD,"[RawInput] Rescanning input devices...");
        RawInput.mouse = null;
    }


}
