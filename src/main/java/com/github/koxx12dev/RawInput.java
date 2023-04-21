package com.github.koxx12dev;

import club.maxstats.weave.loader.api.ModInitializer;
import club.maxstats.weave.loader.api.command.CommandBus;
import club.maxstats.weave.loader.api.event.EventBus;
import com.github.koxx12dev.command.OpenFileCommand;
import com.github.koxx12dev.command.RescanCommand;
import com.github.koxx12dev.listener.UnintendedUsageWarningListener;
import com.github.koxx12dev.util.ChatUtil;
import com.github.koxx12dev.util.RawMouseHelper;
import net.java.games.input.Mouse;
import net.minecraft.client.Minecraft;
import net.minecraft.util.ChatComponentText;

import net.java.games.input.Controller;
import net.java.games.input.ControllerEnvironment;
import net.minecraft.util.EnumChatFormatting;

import java.lang.reflect.Constructor;

public class RawInput implements ModInitializer {
    public static Mouse mouse;
    // Delta for mouse
    public static int dx = 0;
    public static int dy = 0;

    @SuppressWarnings("unchecked")
    private static ControllerEnvironment createDefaultEnvironment() throws ReflectiveOperationException {    // Find constructor (class is package private, so we can't access it directly)
        Constructor<ControllerEnvironment> constructor = (Constructor<ControllerEnvironment>)
                Class.forName("net.java.games.input.DefaultControllerEnvironment").getDeclaredConstructors()[0];

        // Constructor is package private, so we have to deactivate access control checks
        constructor.setAccessible(true);
        // Create object with default constructor
        return constructor.newInstance();
    }

    @Override
    public void init() {
        // Abort mission if OS is not windows - Erymanthus / RayDeeUx
        if (!(System.getProperty("os.name").toLowerCase().contains("windows"))) { EventBus.subscribe(new UnintendedUsageWarningListener()); CommandBus.register(new OpenFileCommand()); return; }


        CommandBus.register(new RescanCommand());
        Minecraft.getMinecraft().mouseHelper = new RawMouseHelper();

        Thread inputThread = new Thread(() -> {
            ControllerEnvironment enviro = null;
            while (true) {
                if (enviro == null) {
                    try {
                        enviro = createDefaultEnvironment();
                    } catch(Exception e) {
                        e.printStackTrace();
                    }
                } else if (mouse == null) {
                    try {
                        Controller[] controllers = enviro.getControllers();
                        for (Controller controller : controllers) {
                            try {
                                if (controller.getType() == Controller.Type.MOUSE) {
                                    controller.poll();
                                    float px = ((Mouse) controller).getX().getPollData();
                                    float py = ((Mouse) controller).getY().getPollData();
                                    float eps = 0.1f;

                                    // check if mouse is moving
                                    if (px < -eps || px > eps || py < -eps || py > eps) {
                                        mouse = (Mouse) controller;
                                        ChatUtil.addMessage(EnumChatFormatting.GREEN, "[RawInput] Found mouse");

                                    }
                                }
                            } catch (Exception e) {
                                // skip to next
                                e.printStackTrace();
                            }
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else {
                    mouse.poll();
                    if (Minecraft.getMinecraft().currentScreen == null) {
                        dx += (int)mouse.getX().getPollData();
                        dy += (int)mouse.getY().getPollData();
                    }
                }
                try {
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        inputThread.setName("inputThread");
        inputThread.start();
    }
}