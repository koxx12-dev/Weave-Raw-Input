package com.github.koxx12dev.command;

import com.github.koxx12dev.util.ChatUtil;
import com.github.koxx12dev.util.WeaveUtil;
import net.minecraft.util.EnumChatFormatting;
import net.weavemc.loader.api.command.Command;

import java.awt.*;

public class OpenFileCommand extends Command {
    public OpenFileCommand() {
        super("modfolder");
    }

    @Override
    public void handle(String[] args) {
        try {
            Desktop.getDesktop().open(WeaveUtil.getModFolder().toFile());
        } catch (Exception e) {
            ChatUtil.addMessage(EnumChatFormatting.RED, "[RawInput] You are currently on " + System.getProperty("os.name") + ", and RawInput had a problem trying to access your mods folder.");
        }
    }
}
