package com.shoaibkhan.modmanager.profiles;

import java.io.BufferedReader;
import java.io.FileReader;
import java.nio.file.Paths;

import com.fasterxml.jackson.databind.JsonNode;
import com.shoaibkhan.modmanager.configs.Config;
import com.shoaibkhan.modmanager.gui.panels.ProfilesPanel;

public class CurrentProfile {

	public static String getCurrentProfileName() {
		try {
			JsonNode profiles = Config.getData().path("profiles");

			if (!profiles.isMissingNode()) {
				// Get name from the config.json
				String current = profiles.get("current").asText();
				String name = profiles.path(current).get("name").asText();

				// Add version to name
				name += " (" + getCurrentProfileVersion() + ")";

				return name;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		// Currupted json file | Reset the json to default
		Config.setDataToDefault();
		return "default";
	}

	public static String getCurrentProfileDirectory() {
		try {
			JsonNode profiles = Config.getData().path("profiles");

			if (!profiles.isMissingNode()) {
				String current;
				try {
					current = ProfilesPanel.profilesComboBox.getSelectedIndex() + "";
				} catch (Exception e) {
					current = "0";
				}
				String directory = profiles.path(current).get("location").asText();
				if (!directory.equalsIgnoreCase("0")) {
					if (!utils.checkValidity(directory)) {
						// Invalid Directory
						RemoveCurrentProfile.removeCurrentProfile();
						getCurrentProfileDirectory();
					}
				}
				return directory;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		// Currupted json file | Reset the json to default
		Config.setDataToDefault();
		return utils.getMinecraftDirectory();
	}

	public static double getCurrentProfileVersion() {
		// Check if options.txt is present and then proceed
		if (!utils.isOptionsTxtPresent(getCurrentProfileDirectory()))
			return 1.17;

		try (BufferedReader optionsFileReader = new BufferedReader(
				new FileReader(Paths.get(getCurrentProfileDirectory(), "options.txt").toFile()))) {
			String lineText = null;
			while ((lineText = optionsFileReader.readLine()) != null) {
				// Get key
				String key = lineText.substring(0, lineText.indexOf(":"));

				// Check if current line is the versions line
				if (key.equalsIgnoreCase("version")) {
					// Get value
					String stringVal = lineText.substring(lineText.indexOf(":") + 1);

					int dataVersion = 2730;

					// Convert value to double
					try {
						dataVersion = Integer.parseInt(stringVal);
					} catch (Exception e) {
						// Corrupted options.txt file, don't change value's value
						e.printStackTrace();
					}

					// Convert and return the minecraft version
					return dataVersionToMinecraftVersion(dataVersion);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return 1.17;
	}

	private static double dataVersionToMinecraftVersion(int dataVersion) {

		// For 1.17.x
		if (dataVersion == 2730 || dataVersion == 2724)
			return 1.17;

		// For 1.16.x
		if (dataVersion == 2586 || dataVersion == 2584 || dataVersion == 2580 || dataVersion == 2578
				|| dataVersion == 2567 || dataVersion == 2566)
			return 1.16;

		return 0;
	}

}
