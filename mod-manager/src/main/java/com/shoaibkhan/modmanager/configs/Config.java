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
package com.shoaibkhan.modmanager.configs;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Paths;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.shoaibkhan.modmanager.profiles.utils;

public class Config {
	private static JsonNode data = null;
	private static final ObjectMapper mapper = new ObjectMapper();
	private static final String CONFIG_PATH = Paths.get("config", "config.json").toString();

	public static JsonNode getData() {
		loadDataFromFile();
		return data;
	}

	public static void setData(JsonNode data) {
		if (data != null)
			resetData(data);
	}

	public static void setDataToDefault() {
		resetData(defaultData());
	}

	private static JsonNode loadDataFromFile() {
		File configFile = new File(CONFIG_PATH);
		if (configFile.exists()) {
			JsonNode root = null;
			try {
				mapper.enable(DeserializationFeature.FAIL_ON_TRAILING_TOKENS);
				root = mapper.readTree(configFile);
				setData(root);
			} catch (IOException e) {
				// Curropted Data | reset to default
				root = defaultData();
				resetData(root);
				e.printStackTrace();
			}
			return root;
		} else {
			// If the file does not exist, create it!
			JsonNode defaultData = defaultData();
			resetData(defaultData);
			return defaultData;
		}
	}

	private static JsonNode defaultData() {
		ObjectNode root = mapper.createObjectNode();

		ObjectNode profileNode = mapper.createObjectNode();
		ObjectNode defaultProfileNode = mapper.createObjectNode();

		defaultProfileNode.put("name", "default");
		defaultProfileNode.put("location", utils.getMinecraftDirectory());

		profileNode.put("total", "0");
		profileNode.set("0", defaultProfileNode);

		root.set("profiles", profileNode);

		return root;
	}

	private static void resetData(JsonNode newData) {
		try {
			// Return string with indentation
			String jsonString = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(newData);
			File configFile = new File(CONFIG_PATH);
			configFile.getParentFile().mkdirs(); // create the parent folder

			FileWriter configWriter = new FileWriter(configFile);
			configWriter.write(jsonString);
			configWriter.close();
			data = newData;
		} catch (IOException e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
		}
	}
}
