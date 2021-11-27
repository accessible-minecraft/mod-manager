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
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.shoaibkhan.modmanager.configs.Config;
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
