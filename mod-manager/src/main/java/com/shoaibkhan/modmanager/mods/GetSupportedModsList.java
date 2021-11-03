package com.shoaibkhan.modmanager.mods;

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

public class GetSupportedModsList {
    private static final String FILE_NAME = "config/temp/mods.json";
    private static final String FILE_URL = "https://raw.githubusercontent.com/accessible-minecraft/mod-manager-configs/main/mods.json";
    private static final ObjectMapper mapper = new ObjectMapper();

    public static List<String> getSupportedModsList() {
        try {
            // Store the file temporarily
            File temp = new File(FILE_NAME);
            FileUtils.copyURLToFile(new URL(FILE_URL), temp);

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

            // Remove the temporary file
            temp.delete();

            return modsList;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
