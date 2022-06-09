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
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.shoaibkhan.modmanager.configs.Config;
import com.shoaibkhan.modmanager.utils.ActionResult;

import java.util.List;

public class AddNewProfile {

    public static ActionResult addNewProfile(String name, String directory, double version) {

        if (utils.checkInvalidity(directory)) {
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
            if (profile.findValue("name").asText().equalsIgnoreCase(name)) {
                return ActionResult.NAME_ALREADY_PRESENT;
            }
            if (profile.findValue("location").asText().equalsIgnoreCase(directory)) {
                return ActionResult.DIRECTORY_ALREADY_PRESENT;
            }
        }

        // Get total profile
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
