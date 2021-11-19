package com.shoaibkhan.modmanager.profiles;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;

import com.fasterxml.jackson.databind.JsonNode;
import com.shoaibkhan.modmanager.configs.Config;

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

					// Check if the json node is `current` or `total`
					if (entry.getKey().equalsIgnoreCase("current") || entry.getKey().equalsIgnoreCase("total"))
						continue;

					String name = entry.getValue().get("name").asText();

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
