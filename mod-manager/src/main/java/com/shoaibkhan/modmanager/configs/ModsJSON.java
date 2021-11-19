package com.shoaibkhan.modmanager.configs;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;

import org.apache.commons.io.FileUtils;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ModsJSON {
	private static final String TEMP_FOLDER = Paths.get("config", "temp").toString();
	private static final String FILE_NAME = Paths.get(TEMP_FOLDER, "mods.json").toString();
	private static final String MOD_CONFIG_PATH = Paths.get(TEMP_FOLDER, "configs").toString();
	private static final String FILE_URL = "https://raw.githubusercontent.com/accessible-minecraft/mod-manager-configs/main/mods.json";
	private static final ObjectMapper mapper = new ObjectMapper();

	public static void loadIfNotPresent() {
		try {
			// Check if present
			File temp = new File(FILE_NAME);
			if (temp.exists())
				return;

			// Store the file
			FileUtils.copyURLToFile(new URL(FILE_URL), temp);

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static List<String> getSupportedModsList(double minecraftVersion) {
		try {
			// Load the file
			File modsJsonFile = new File(FILE_NAME);

			if (!modsJsonFile.exists())
				loadIfNotPresent();

			// Get json data
			JsonNode root = null;
			mapper.enable(DeserializationFeature.FAIL_ON_TRAILING_TOKENS);
			root = mapper.readTree(modsJsonFile);

			// Get supported mod
			Iterator<Entry<String, JsonNode>> mods = root.fields();
			List<String> modsList = new ArrayList<>();

			// Save mod names in modsList and return it
			while (mods.hasNext()) {
				Entry<String, JsonNode> entry = mods.next();

				// Get supported versions
				JsonNode versions = entry.getValue().get("supported-versions");
				if (versions.isArray()) {
					for (final JsonNode objNode : versions) {
						if (Double.compare(objNode.asDouble(), minecraftVersion) == 0) {
							modsList.add(entry.getKey());

							// Download mod's config.json if not present
							String url = entry.getValue().get("url").asText();
							String fileName = entry.getValue().get("file-name").asText();
							String filePath = Paths.get(MOD_CONFIG_PATH, fileName).toString();

							File modFile = new File(filePath);
							if (!modFile.exists())
								FileUtils.copyURLToFile(new URL(url), modFile);
						}
					}
				}
			}

			return modsList;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static String getModConfigFilePath(String modName) {
		try {
			// Load the file
			File modsJsonFile = new File(FILE_NAME);

			if (!modsJsonFile.exists())
				loadIfNotPresent();

			// Get json data
			JsonNode root = null;
			mapper.enable(DeserializationFeature.FAIL_ON_TRAILING_TOKENS);
			root = mapper.readTree(modsJsonFile);

			// The mod's jsonNode
			JsonNode modNode = root.path(modName);
			if (modNode.isMissingNode()) {
				return null;
			}

			String fileName = modNode.get("file-name").asText();
			String filePath = Paths.get(MOD_CONFIG_PATH, fileName).toString();

			return filePath;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	public static void DeleteTempFolder() {
		File tempFolder = new File(TEMP_FOLDER);
		deleteDir(tempFolder);
	}

	private static void deleteDir(File file) {
		File[] contents = file.listFiles();
		if (contents != null) {
			for (File f : contents) {
				deleteDir(f);
			}
		}
		file.delete();
	}
}
