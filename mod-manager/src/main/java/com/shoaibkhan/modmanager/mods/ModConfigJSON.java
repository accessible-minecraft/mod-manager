package com.shoaibkhan.modmanager.mods;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.shoaibkhan.modmanager.configs.ModsJSON;

public class ModConfigJSON {
    private static final ObjectMapper mapper = new ObjectMapper();

    public static List<String> getVersionsList(String modName, double minecraftVersion) {
        // Get the mod's config file path
        String filePath = ModsJSON.getModConfigFilePath(modName);

        try {
            File configFile = new File(filePath);
            if (configFile.exists()) {
                JsonNode root = null;
                mapper.enable(DeserializationFeature.FAIL_ON_TRAILING_TOKENS);
                root = mapper.readTree(configFile);

                // Get node for the correct minecraft version
                JsonNode versionContainer = root.path(Double.toString(minecraftVersion));

                Iterator<Entry<String, JsonNode>> versions = versionContainer.fields();
                List<String> versionsList = new ArrayList<>();

                // Iterate through all versions
                while (versions.hasNext()) {
                    JsonNode entry = versions.next().getValue();
                    versionsList.add(entry.get("version").asText());
                }
                Collections.reverse(versionsList);

                return versionsList;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static List<String> getFileNameList(String modName, double minecraftVersion) {
        // Get the mod's config file path
        String filePath = ModsJSON.getModConfigFilePath(modName);

        try {
            File configFile = new File(filePath);
            if (configFile.exists()) {
                JsonNode root = null;
                mapper.enable(DeserializationFeature.FAIL_ON_TRAILING_TOKENS);
                root = mapper.readTree(configFile);

                // Get node for the correct minecraft version
                JsonNode versionContainer = root.path(Double.toString(minecraftVersion));

                Iterator<Entry<String, JsonNode>> versions = versionContainer.fields();
                List<String> fileNameList = new ArrayList<>();

                // Iterate through all versions
                while (versions.hasNext()) {
                    JsonNode entry = versions.next().getValue();
                    fileNameList.add(entry.get("file-name").asText());
                }
                Collections.reverse(fileNameList);

                return fileNameList;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static List<String> getDownloadUrlList(String modName, double minecraftVersion) {
        // Get the mod's config file path
        String filePath = ModsJSON.getModConfigFilePath(modName);

        try {
            File configFile = new File(filePath);
            // Download if not exist
            if (!configFile.exists()) {
            }

            JsonNode root = null;
            mapper.enable(DeserializationFeature.FAIL_ON_TRAILING_TOKENS);
            root = mapper.readTree(configFile);

            // Get node for the correct minecraft version
            JsonNode versionContainer = root.path(Double.toString(minecraftVersion));

            Iterator<Entry<String, JsonNode>> versions = versionContainer.fields();
            List<String> downloadUrlList = new ArrayList<>();

            // Iterate through all versions
            while (versions.hasNext()) {
                JsonNode entry = versions.next().getValue();
                downloadUrlList.add(entry.get("download-url").asText());
            }
            Collections.reverse(downloadUrlList);

            return downloadUrlList;
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }
}
