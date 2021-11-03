package com.shoaibkhan.modmanager.mods;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;

import com.shoaibkhan.modmanager.profiles.CurrentProfile;
import com.shoaibkhan.modmanager.profiles.utils;

import org.junit.Test;

public class UninstallModTest {
    @Test
    public void testUninstallMod() {
        System.out.println("Before:");
        printMods();

        UninstallMod.uninstallMod("controls-menu-bug-fix");

        System.out.println("Before:");
        printMods();
    }

    @Test
    public void printMods() {
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
                System.out.println(name);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
