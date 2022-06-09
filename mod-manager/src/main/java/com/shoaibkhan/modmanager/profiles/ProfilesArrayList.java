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

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;

public class ProfilesArrayList {

    public static Object[] profilesArrayList() {
        try {
            JsonNode profilesRoot = Config.getData().path("profiles");

            if (!profilesRoot.isMissingNode()) {
                List<String> profilesList = new ArrayList<>();

                Iterator<Entry<String, JsonNode>> profile = profilesRoot.fields();

                // Store all profile names in profiles list
                while (profile.hasNext()) {
                    Entry<String, JsonNode> entry = profile.next();

                    // Check if the json node is `total`
                    if (entry.getKey().equalsIgnoreCase("total")) {
                        continue;
                    }

                    String name = entry.getValue().get("name").asText() + " (" + entry.getValue().get("version").asText() + ")";

                    profilesList.add(name);
                }

                return profilesList.toArray();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Currupted json file | Reset the json to default and run the method again
        Config.setDataToDefault();
        return profilesArrayList();
    }
}
