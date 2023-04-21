package com.github.koxx12dev.util;

import java.nio.file.Path;
import java.nio.file.Paths;

public class WeaveUtil {
    public static Path getModFolder() {
        return Paths.get(System.getProperty("user.home"), ".lunarclient", "mods");
    }
}
