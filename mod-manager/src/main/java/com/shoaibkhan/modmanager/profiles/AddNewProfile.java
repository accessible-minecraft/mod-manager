package com.shoaibkhan.modmanager.profiles;

import java.util.List;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.shoaibkhan.modmanager.configs.Config;
import com.shoaibkhan.modmanager.utils.ActionResult;

public class AddNewProfile {
    
    public static ActionResult addNewProfile(String name, String directory, double version) {

        if (!utils.checkValidity(directory)) {
            return ActionResult.INVALID_DIRECORY;
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
                return ActionResult.NAME_ALREADY_PRESENT;
            if (profile.findValue("location").asText().equalsIgnoreCase(directory))
                return ActionResult.DIRECTORY_ALREADY_PRESENT;
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
        newProfileNode.put("version", version);

        // Add the new profile node to the profiles node and reset the data
        total++;
        ((ObjectNode) data.path("profiles")).set(Integer.toString(total), newProfileNode);
        ((ObjectNode) data.path("profiles")).put("total", Integer.toString(total));
        Config.setData(data);
        return ActionResult.PASS;
    }
}
