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
import com.shoaibkhan.modmanager.gui.panels.ProfilesPanel;
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
			current = ProfilesPanel.profilesComboBox.getSelectedIndex();
		} catch (Exception e) {
			e.printStackTrace();
		}

		try {
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
		ProfilesPanel.profilesComboBox.refresh();
		return ActionResult.PASS;
	}
}
