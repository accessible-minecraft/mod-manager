package com.shoaibkhan.modmanager.profiles;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.shoaibkhan.modmanager.Config.Config;
import com.shoaibkhan.modmanager.utils.ActionResult;

public class SelectNextProfile {
    public static ActionResult selectNextProfiResult() {
        try {
            JsonNode data = Config.getData();

            if (!data.path("profiles").isMissingNode()) {
                // Get current and total profiles index
                int current = data.path("profiles").get("current").asInt();
                int total = data.path("profiles").get("total").asInt();

                current++;
                if(current>total) current = 0;

                ((ObjectNode)data.path("profiles")).put("current", Integer.toString(current));
                Config.setData(data);
                return ActionResult.PASS;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        Config.setDataToDefault();
        return ActionResult.CORRUPTED_JSON_RESETTED_TO_DEFAULT;
    }
}
