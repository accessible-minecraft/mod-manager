package com.shoaibkhan.modmanager.profiles;

import com.fasterxml.jackson.databind.JsonNode;
import com.shoaibkhan.modmanager.config.Config;

public class CurrentProfile {

    public static String getCurrentProfileName() {
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

    public static String getCurrentProfileDirectory() {
        try {
            JsonNode profiles = Config.getData().path("profiles");

            if (!profiles.isMissingNode()) {
                String current = profiles.get("current").asText();
                String directory = profiles.path(current).get("location").asText();
                if (!directory.equalsIgnoreCase("0")) {
                    if (!utils.checkValidity(directory)) {
                        // Invalid Directory
                        RemoveCurrentProfile.removeCurrentProfile();
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

}
