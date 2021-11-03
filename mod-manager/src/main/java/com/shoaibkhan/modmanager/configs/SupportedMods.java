package com.shoaibkhan.modmanager.configs;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.apache.commons.io.FileUtils;

public class SupportedMods {
    private static final String FILE_NAME = "config/temp/mods.json";
    private static final String FILE_URL = "https://raw.githubusercontent.com/accessible-minecraft/mod-manager-configs/main/mods.json";
    private static final ObjectMapper mapper = new ObjectMapper();

    public static void loadIfNotPresent() {
        try {
            // Check if present
            File temp = new File(FILE_NAME);
            if (temp.exists())
                return;

            // Store the file
            FileUtils.copyURLToFile(new URL(FILE_URL), temp);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static List<String> getSupportedModsListFor1_17() {
        try {
            // Load the file
            File temp = new File(FILE_NAME);

            if (!temp.exists())
                loadIfNotPresent();

            // Get json data
            JsonNode root = null;
            mapper.enable(DeserializationFeature.FAIL_ON_TRAILING_TOKENS);
            root = mapper.readTree(temp);

            // Get supported mod
            Iterator<Entry<String, JsonNode>> mods = root.fields();
            List<String> modsList = new ArrayList<>();

            // Save mod names in modsList and return it
            while (mods.hasNext()) {
                Entry<String, JsonNode> entry = mods.next();

                // Get supported versions
                JsonNode versions = entry.getValue().get("supported-versions");
                if (versions.isArray()) {
                    for (final JsonNode objNode : versions) {
                        if (Double.compare(objNode.asDouble(), 1.17) == 0)
                            modsList.add(entry.getKey());
                    }
                }
            }

            return modsList;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static List<String> getSupportedModsListFor1_16() {
        try {
            // Load the file
            File temp = new File(FILE_NAME);

            if (!temp.exists())
                loadIfNotPresent();

            // Get json data
            JsonNode root = null;
            mapper.enable(DeserializationFeature.FAIL_ON_TRAILING_TOKENS);
            root = mapper.readTree(temp);

            // Get supported mod
            Iterator<Entry<String, JsonNode>> mods = root.fields();
            List<String> modsList = new ArrayList<>();

            // Save mod names in modsList and return it
            while (mods.hasNext()) {
                Entry<String, JsonNode> entry = mods.next();

                // Get supported versions
                JsonNode versions = entry.getValue().get("supported-versions");
                if (versions.isArray()) {
                    for (final JsonNode objNode : versions) {
                        if (Double.compare(objNode.asDouble(), 1.16) == 0)
                            modsList.add(entry.getKey());
                    }
                }
            }

            return modsList;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void DeleteTemporaryFile() {
        File temp = new File(FILE_NAME);
        temp.delete();
    }
}
