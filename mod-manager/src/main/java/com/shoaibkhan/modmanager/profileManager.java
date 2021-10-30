package com.shoaibkhan.modmanager;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

enum OS {
    WINDOWS, MACOS, LINUX;
}

public class profileManager {

    public String getCurrentProfileName() {
        try {
            JsonNode profiles = Config.getData().path("profiles");

            if (!profiles.isMissingNode()) {
                String current = profiles.get("current").asText();
                String name = profiles.path(current).get("name").asText();
                return name;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Currupted json file | Reset the json to default
        Config.setDataToDefault();
        return "default";
    }

    public String getCurrentProfileDirectory() {
        try {
            JsonNode profiles = Config.getData().path("profiles");

            if (!profiles.isMissingNode()) {
                String current = profiles.get("current").asText();
                String directory = profiles.path(current).get("location").asText();
                if (!directory.equalsIgnoreCase("0")) {
                    if (!checkValidity(directory)) {
                        // Invalid Directory
                        // TODO set current directory to default
                    }
                }
                return directory;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Currupted json file | Reset the json to default
        Config.setDataToDefault();
        return "default";
    }

    public void addNewProfile(String name, String directory) {

        if (!checkValidity(directory)) {
            return;
        }

        ObjectNode data = (ObjectNode) Config.getData();

        // check if the config.json is valid
        try {
            ObjectNode profiles = (ObjectNode) Config.getData().path("profiles");
            if (profiles.isMissingNode()) {
                Config.setDataToDefault();
                data = (ObjectNode) Config.getData();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Check if the specified directory or name is already presetn
        List<JsonNode> allProfileList = data.findParents("name");
        for (JsonNode profile : allProfileList) {
            if (profile.findValue("name").asText().equalsIgnoreCase(name))
                return;
            if (profile.findValue("location").asText().equalsIgnoreCase(directory))
                return;
        }

        // Get current selected profile index and total profile
        int total = 0;
        try {
            total = data.path("profiles").get("total").asInt();
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Create new profile node
        ObjectMapper mapper = new ObjectMapper();
        ObjectNode newProfileNode = mapper.createObjectNode();
        newProfileNode.put("name", name);
        newProfileNode.put("location", directory);

        // Add the new profile node to the profiles node and reset the data
        total++;
        ((ObjectNode) data.path("profiles")).set(Integer.toString(total), newProfileNode);
        ((ObjectNode) data.path("profiles")).put("total", Integer.toString(total));
        Config.setData(data);
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
        if (!Files.exists(Paths.get(path)))
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
