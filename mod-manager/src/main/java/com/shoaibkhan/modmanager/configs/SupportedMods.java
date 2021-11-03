package com.shoaibkhan.modmanager.configs;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

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
            if(temp.exists()) return;
            
            // Store the file
            FileUtils.copyURLToFile(new URL(FILE_URL), temp);
            System.out.println("herer\n\n");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static List<String> getSupportedModsList() {
        try {
            // Load the file
            File temp = new File(FILE_NAME);

            // Get json data
            JsonNode root = null;
            mapper.enable(DeserializationFeature.FAIL_ON_TRAILING_TOKENS);
            root = mapper.readTree(temp);

            // Get supported mod names
            Iterator<String> mods = root.fieldNames();
            List<String> modsList = new ArrayList<>();

            // Save mod names in modsList and return it
            while (mods.hasNext()) {
                modsList.add(mods.next());
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
