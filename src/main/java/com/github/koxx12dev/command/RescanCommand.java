package com.github.koxx12dev.command;

import com.github.koxx12dev.RawInput;
import com.github.koxx12dev.util.ChatUtil;
import net.minecraft.util.EnumChatFormatting;
import net.weavemc.loader.api.command.Command;

public class RescanCommand extends Command {


    public RescanCommand() {
        super("rescan");
    }

    @Override
    public void handle(String[] args) {
        ChatUtil.addMessage(EnumChatFormatting.GOLD, "[RawInput] Rescanning input devices...");
        RawInput.mouse = null;
    }


}
