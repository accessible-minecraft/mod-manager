package com.shoaibkhan.modmanager;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Paths;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

public class Config {
    private static ObjectNode data;
    private static final ObjectMapper mapper = new ObjectMapper();
    private static String CONFIG_PATH = Paths.get("config", "config.json").toString();

    public static ObjectNode getData() {
        if (data == null) {
            loadDataFromFile();
        }
        return data;
    }

    public static void setData(ObjectNode data) {
        if (data == null) {
            loadDataFromFile();
        }
        Config.data = data;
    }

    private static ObjectNode loadDataFromFile() {
        File configFile = new File(CONFIG_PATH);
        if (configFile.exists()) {
            ObjectNode root = null;
            try {
                root = (ObjectNode) mapper.readTree(new File(CONFIG_PATH));
                setData(root);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return root;
        } else {
            // If the file does not exist, create it!
            ObjectNode defaultData = defaultData();
            resetData(defaultData);
            return defaultData;
        }
    }

    public static ObjectNode defaultData() {
        ObjectNode root = mapper.createObjectNode();

        ObjectNode profileNode = mapper.createObjectNode();
        ObjectNode defaultProfileNode = mapper.createObjectNode();

        defaultProfileNode.put("name", "default");
        defaultProfileNode.put("location", new profileManager().getMinecraftDirectory());

        profileNode.put("current", "default");
        profileNode.set("0", defaultProfileNode);

        root.set("profiles", profileNode);

        return root;
    }

    public static void resetData(ObjectNode newData) {
        try {
            String jsonString = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(newData); // Return string with indentation
            File configFile = new File(CONFIG_PATH);
            configFile.getParentFile().mkdirs(); // create the parent folder

            FileWriter configWriter = new FileWriter(configFile);
            configWriter.write(jsonString);
            configWriter.close();
            setData(newData);
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
}
