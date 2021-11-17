package com.shoaibkhan.modmanager.profiles;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;

public class utils {
    public static enum OS {
        WINDOWS, MACOS, LINUX;
    }

    public static String getMinecraftDirectory() {
        OS currentOs = getOS();
        String homeDirectory = "";
        StringBuilder minecraftDirectory = new StringBuilder("");

        switch (currentOs) {
        case WINDOWS: {
            homeDirectory = System.getenv("APPDATA");
            minecraftDirectory = new StringBuilder(homeDirectory);
            minecraftDirectory.append("\\.minecraft");
            break;
        }
        case LINUX: {
            homeDirectory = System.getProperty("user.home");
            minecraftDirectory = new StringBuilder(homeDirectory);
            minecraftDirectory.append("/.minecraft");
            break;
        }
        case MACOS: {
            homeDirectory = System.getProperty("user.home");
            minecraftDirectory = new StringBuilder(homeDirectory);
            minecraftDirectory.append("/Library/Application Support/minecraft");
            break;
        }
        }

        if (Files.exists(Paths.get(minecraftDirectory.toString())))
            return minecraftDirectory.toString();
        else
            return homeDirectory;
    }

    public static OS getOS() {
        String osName = System.getProperty("os.name").toLowerCase();

        if (osName.contains("win")) {
            return OS.WINDOWS;
        } else if (osName.contains("mac")) {
            return OS.MACOS;
        } else if (osName.contains("nix") || osName.contains("nux") || osName.indexOf("aix") > 0) {
            return OS.LINUX;
        }
        return OS.LINUX;
    }

    public static boolean checkValidity(String path) {
        if (!Files.exists(Paths.get(path).toAbsolutePath()))
            return false;

        File folder = new File(path);

        try {
            String[] listOfItemsInFolder = folder.list();

            boolean hasSaves = false, hasOptionsTXT = false, hasServerDAT = false;
            for (String item : listOfItemsInFolder) {
                if (item.equalsIgnoreCase("saves"))
                    hasSaves = true;
                if (item.equalsIgnoreCase("options.txt"))
                    hasOptionsTXT = true;
                if (item.equalsIgnoreCase("servers.dat"))
                    hasServerDAT = true;
            }

            if (hasSaves && hasOptionsTXT && hasServerDAT)
                return true;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }
}
