package com.shoaibkhan.modmanager.profiles;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.shoaibkhan.modmanager.configs.Config;
import com.shoaibkhan.modmanager.gui.panels.ProfilesPanel;
import com.shoaibkhan.modmanager.utils.ActionResult;

public class EditCurrentProfile {
    public static ActionResult editCurrentProfile(String name, String directory, double version) {
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

        String current;
        try {
            current = ProfilesPanel.profilesComboBox.getSelectedIndex() + "";
        } catch (Exception e) {
            current = "0";
        }

        // Create new profile node
        ObjectMapper mapper = new ObjectMapper();
        ObjectNode newProfileNode = mapper.createObjectNode();
        newProfileNode.put("name", name);
        newProfileNode.put("location", directory);
        newProfileNode.put("version", version);

        // Add the new profile node to the profiles node and reset the data
        ((ObjectNode) data.path("profiles")).put(current, newProfileNode);
        Config.setData(data);
        return ActionResult.PASS;
    }
}
