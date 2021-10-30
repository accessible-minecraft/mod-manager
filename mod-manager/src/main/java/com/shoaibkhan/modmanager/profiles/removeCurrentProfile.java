package com.shoaibkhan.modmanager.profiles;

import java.util.Iterator;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.shoaibkhan.modmanager.Config.Config;

public class removeCurrentProfile {
    
    public removeCurrentProfile() {
        ObjectNode data = (ObjectNode) Config.getData();

        // check if the config.json is valid
        try {
            ObjectNode profiles = (ObjectNode) Config.getData().path("profiles");
            if (profiles.isMissingNode()) {
                // Reset and return if not valid
                Config.setDataToDefault();
                data = (ObjectNode) Config.getData();
                return;
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
            return;
        }

        // get the name of all nodes in profiles node and iterate over them
        Iterator<String> profiles = data.path("profiles").fieldNames();
        while (profiles.hasNext()) {
            String profileNode = profiles.next();

            if (profileNode.equalsIgnoreCase("current"))
                continue;
            if (profileNode.equalsIgnoreCase("total"))
                continue;
            if (profileNode.equalsIgnoreCase("0"))
                continue;

            int profileIndex = Integer.parseInt(profileNode);
            if (profileIndex == current) {
                // Delete the node
                ((ObjectNode) data.path("profiles")).remove(profileNode);
            }
            if (profileIndex > current) {
                JsonNode nodeToMove = data.path("profiles").get(profileNode);
                ((ObjectNode) data.path("profiles")).set(Integer.toString(profileIndex - 1), nodeToMove);
            }
        }

        // Decrease total and current by 1
        total--;
        ((ObjectNode) data.path("profiles")).put("total", Integer.toString(total));
        current--;
        ((ObjectNode) data.path("profiles")).put("current", Integer.toString(current));

        // Reset the data
        Config.setData(data);

    }
}
