package com.shoaibkhan.modmanager.profiles;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.shoaibkhan.modmanager.Config.Config;
import com.shoaibkhan.modmanager.utils.ActionResult;

public class RemoveCurrentProfile {

    public static ActionResult removeCurrentProfile() {
        ObjectNode data = (ObjectNode) Config.getData();

        // check if the config.json is valid
        try {
            ObjectNode profiles = (ObjectNode) Config.getData().path("profiles");
            if (profiles.isMissingNode()) {
                // Reset and return if not valid
                Config.setDataToDefault();
                data = (ObjectNode) Config.getData();
                return ActionResult.CORRUPTED_JSON_RESETTED_TO_DEFAULT;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        int current = 0, total = 0;
        try {
            current = data.path("profiles").get("current").asInt();
            total = data.path("profiles").get("total").asInt();
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (current == 0) {
            return ActionResult.CANNOT_REMOVE_DEFAULT_PROFILE;
        }

        // Remove the current profile and move the rest profiles
        for (int i = current; i <= total; i++) {
            if (i > current) {
                // Move the profile one place below
                JsonNode toMove = ((ObjectNode) data.path("profiles")).get(Integer.toString(i));
                ((ObjectNode) data.path("profiles")).set(Integer.toString(i - 1), toMove);
            }

            // Remove the profile
            ((ObjectNode) data.path("profiles")).remove(Integer.toString(i));
        }

        // Decrease total and current by 1
        total--;
        ((ObjectNode) data.path("profiles")).put("total", Integer.toString(total));
        current--;
        ((ObjectNode) data.path("profiles")).put("current", Integer.toString(current));

        // Reset the data
        Config.setData(data);
        return ActionResult.PASS;
    }
}
