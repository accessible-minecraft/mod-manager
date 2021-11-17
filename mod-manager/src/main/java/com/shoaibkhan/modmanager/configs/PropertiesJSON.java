package com.shoaibkhan.modmanager.configs;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Paths;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.apache.commons.io.FileUtils;

public class PropertiesJSON {
    private static final String TEMP_FOLDER = Paths.get("config", "temp").toString();
    private static final String FILE_NAME = Paths.get(TEMP_FOLDER, "properties.json").toString();
    private static final String FILE_URL = "https://raw.githubusercontent.com/accessible-minecraft/mod-manager-configs/main/properties.json";
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

    public static Float getLatestVersion() {
        try {
            // Load the file
            File modsJsonFile = new File(FILE_NAME);

            if (!modsJsonFile.exists())
                loadIfNotPresent();

            // Get json data
            JsonNode root = null;
            mapper.enable(DeserializationFeature.FAIL_ON_TRAILING_TOKENS);
            root = mapper.readTree(modsJsonFile);

            // Get the version
            String version = root.get("latest-version").asText();
            Float versionInFloat = Float.parseFloat(version);
            return versionInFloat;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
