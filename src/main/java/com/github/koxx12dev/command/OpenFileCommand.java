package com.github.koxx12dev.command;

import club.maxstats.weave.loader.WeaveLoader;
import club.maxstats.weave.loader.api.command.Command;
import com.github.koxx12dev.util.ChatUtil;
import com.github.koxx12dev.util.WeaveUtil;
import net.minecraft.client.Minecraft;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;

import java.awt.*;
import java.io.File;

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
