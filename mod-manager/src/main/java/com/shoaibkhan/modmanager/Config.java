package com.shoaibkhan.modmanager;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Paths;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

public class Config {
    private static JsonNode data = null;
    private static final ObjectMapper mapper = new ObjectMapper();
    private static String CONFIG_PATH = Paths.get("config", "config.json").toString();

    public static JsonNode getData() {
        if (data == null) {
            loadDataFromFile();
        }
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
                root = mapper.readTree(new File(CONFIG_PATH));
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
        defaultProfileNode.put("location", new profileManager().getMinecraftDirectory());

        profileNode.put("current", "0");
        profileNode.put("total", "0");
        profileNode.set("0", defaultProfileNode);

        root.set("profiles", profileNode);

        return root;
    }

    private static void resetData(JsonNode newData) {
        try {
            String jsonString = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(newData); // Return string
                                                                                                     // with indentation
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
