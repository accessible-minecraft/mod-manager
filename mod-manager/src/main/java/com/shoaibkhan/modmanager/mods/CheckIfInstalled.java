package com.shoaibkhan.modmanager.mods;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;

import com.shoaibkhan.modmanager.profiles.CurrentProfile;
import com.shoaibkhan.modmanager.profiles.utils;

public class CheckIfInstalled {
    public static boolean checkIfInstalled(String modName) {
        // Check mods folder is present
        String modFolderPath = CurrentProfile.getCurrentProfileDirectory()+"/mods";
        if(utils.getOS() == utils.OS.WINDOWS)
            modFolderPath = CurrentProfile.getCurrentProfileDirectory()+"\\mods";

        if (!Files.exists(Paths.get(modFolderPath)))
            return false;

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

        return false;
    }
}
