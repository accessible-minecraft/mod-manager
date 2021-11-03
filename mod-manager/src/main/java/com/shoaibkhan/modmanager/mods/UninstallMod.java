package com.shoaibkhan.modmanager.mods;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;

import com.shoaibkhan.modmanager.profiles.CurrentProfile;
import com.shoaibkhan.modmanager.profiles.utils;
import com.shoaibkhan.modmanager.utils.ActionResult;

public class UninstallMod {
    public static ActionResult uninstallMod(String modName) {
        // Double check if installed or not!
        if (!CheckIfInstalled.checkIfInstalled(modName))
            return ActionResult.MOD_NOT_INSTALLED;

        try {
            // Check mods folder is present
            String modFolderPath = CurrentProfile.getCurrentProfileDirectory() + "/mods";
            if (utils.getOS() == utils.OS.WINDOWS)
                modFolderPath = CurrentProfile.getCurrentProfileDirectory() + "\\mods";

            // Create mods folder if not present
            if (!Files.exists(Paths.get(modFolderPath)))
                Files.createDirectory(Paths.get(modFolderPath));

            // get mods list
            File modsFolder = new File(modFolderPath);
            String[] modsList = modsFolder.list();

            for (String name : modsList) {
                if (name.toLowerCase().contains(modName)) {
                    // Delete the mod file
                    if (utils.getOS() == utils.OS.WINDOWS)
                        new File(modsFolder + "\\" + name).delete();
                    else
                        new File(modsFolder + "/" + name).delete();

                    return ActionResult.PASS;
                }

            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return ActionResult.FAILED;
    }
}
