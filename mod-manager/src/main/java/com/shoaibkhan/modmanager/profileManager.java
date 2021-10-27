package com.shoaibkhan.modmanager;

enum OS {
    WINDOWS, MACOS, LINUX;
}

public class profileManager {

    public static String getMinecraftDirectory() {
        OS currentOs = getOS();
        String homeDirectory = "";
        StringBuilder minecraftDirectory;

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
            default: {
                homeDirectory = System.getProperty("user.home");
                minecraftDirectory = new StringBuilder(homeDirectory);
                minecraftDirectory.append("/.minecraft");
                break;
            }
        }

        return minecraftDirectory.toString();
    }

    private static OS getOS() {
        String osName = System.getProperty("os.name").toLowerCase();

        if (osName.indexOf("win") >= 0) {
            return OS.WINDOWS;
        } else if (osName.indexOf("mac") >= 0) {
            return OS.MACOS;
        } else if (osName.indexOf("nix") >= 0 || osName.indexOf("nux") >= 0 || osName.indexOf("aix") > 0) {
            return OS.LINUX;
        }
        return OS.LINUX;
    }
}
