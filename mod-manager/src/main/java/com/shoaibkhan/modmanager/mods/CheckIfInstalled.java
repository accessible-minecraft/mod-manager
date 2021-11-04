package com.shoaibkhan.modmanager.mods;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;

import com.shoaibkhan.modmanager.profiles.CurrentProfile;

public class CheckIfInstalled {
    public static boolean checkIfInstalled(String modName) {
        try {
            // Check mods folder is present
            String modFolderPath = Paths.get(CurrentProfile.getCurrentProfileDirectory(), "mods").toString();

            // Create mods folder if not present
            if (!Files.exists(Paths.get(modFolderPath)))
                Files.createDirectory(Paths.get(modFolderPath));

            // get mods list
            File modsFolder = new File(modFolderPath);
            try {
                String[] modsList = modsFolder.list();

                for (String name : modsList) {
                    name = name.toLowerCase();
                    if (name.contains(modName))
                        return true;
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;

    }
}
