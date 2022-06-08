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
package com.shoaibkhan.modmanager.profiles;

import com.fasterxml.jackson.databind.JsonNode;
import com.shoaibkhan.modmanager.configs.Config;
import com.shoaibkhan.modmanager.gui.panels.ProfilesPanel;

public class CurrentProfile {

    public static String getCurrentProfileName() {
        try {
            JsonNode profiles = Config.getData().path("profiles");

            if (!profiles.isMissingNode()) {
                // Get name from the config.json
                String current;
                try {
                    current = ProfilesPanel.profilesComboBox.getSelectedIndex() + "";
                } catch (Exception e) {
                    current = "0";
                }

                return profiles.path(current).get("name").asText();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Currupted json file | Reset the json to default
        Config.setDataToDefault();
        return "default";
    }

    public static String getCurrentProfileDirectory() {
        try {
            JsonNode profiles = Config.getData().path("profiles");

            if (!profiles.isMissingNode()) {
                String current;
                try {
                    current = ProfilesPanel.profilesComboBox.getSelectedIndex() + "";
                } catch (Exception e) {
                    current = "0";
                }

                return profiles.path(current).get("location").asText();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Currupted json file | Reset the json to default
        Config.setDataToDefault();
        return utils.getMinecraftDirectory();
    }

    public static double getCurrentProfileVersion() {
        try {
            JsonNode profiles = Config.getData().path("profiles");

            if (!profiles.isMissingNode()) {
                String current;
                try {
                    current = ProfilesPanel.profilesComboBox.getSelectedIndex() + "";
                } catch (Exception e) {
                    current = "0";
                }

                return profiles.path(current).get("version").asDouble();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Currupted json file | Reset the json to default
        Config.setDataToDefault();
        return utils.getLatestMinecraftVersion();
    }
}
