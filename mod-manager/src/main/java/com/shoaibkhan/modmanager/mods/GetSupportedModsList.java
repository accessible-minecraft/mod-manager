package com.shoaibkhan.modmanager.mods;

import java.io.BufferedInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;

public class GetSupportedModsList {
    private static final String FILE_NAME = "mods.json";
    String FILE_URL = "https://raw.githubusercontent.com/accessible-minecraft/mod-manager-configs/main/mods.json";

    public void getSupportedModsList() {
        try (BufferedInputStream in = new BufferedInputStream(new URL(FILE_URL).openStream());
                FileOutputStream fileOutputStream = new FileOutputStream(FILE_NAME)) {
            byte dataBuffer[] = new byte[1024];
            int bytesRead;
            while ((bytesRead = in.read(dataBuffer, 0, 1024)) != -1) {
                fileOutputStream.write(dataBuffer, 0, bytesRead);
            }
        } catch (IOException e) {
            // handle exception
        }
        try {
            BufferedInputStream in = new BufferedInputStream(new URL(FILE_URL).openStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
