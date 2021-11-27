/*
 * The MIT License
 *
 * Copyright 2021 shoaib.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
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
