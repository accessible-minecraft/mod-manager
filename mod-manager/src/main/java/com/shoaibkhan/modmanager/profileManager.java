package com.shoaibkhan.modmanager;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;

import com.fasterxml.jackson.databind.JsonNode;

enum OS {
    WINDOWS, MACOS, LINUX;
}

public class profileManager {

    public String getCurrentProfileName() {
        JsonNode profiles = Config.getData().path("profiles");

        if(!profiles.isMissingNode()){
            String name = profiles.get("current").asText();
            if(!name.equalsIgnoreCase("default")){
                // TODO Checking validity of the profile here!
            }
            return name;
        }

        // Currupted json file | Reset the json to default
        Config.resetData(Config.defaultData());
        return "default";
    }

    public String getMinecraftDirectory() {
        OS currentOs = getOS();
        String homeDirectory = "";
        StringBuilder minecraftDirectory = new StringBuilder("");

        switch (currentOs) {
        case WINDOWS -> {
            homeDirectory = System.getenv("APPDATA");
            minecraftDirectory = new StringBuilder(homeDirectory);
            minecraftDirectory.append("\\.minecraft");
        }
        case LINUX -> {
            homeDirectory = System.getProperty("user.home");
            minecraftDirectory = new StringBuilder(homeDirectory);
            minecraftDirectory.append("/.minecraft");
        }
        case MACOS -> {
            homeDirectory = System.getProperty("user.home");
            minecraftDirectory = new StringBuilder(homeDirectory);
            minecraftDirectory.append("/Library/Application Support/minecraft");
        }
        }

        if (Files.exists(Paths.get(minecraftDirectory.toString())))
            return minecraftDirectory.toString();
        else
            return homeDirectory;
    }

    private OS getOS() {
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

    private boolean checkValidity(String path) {
        File folder = new File(path);
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

        return false;
    }
}
