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
