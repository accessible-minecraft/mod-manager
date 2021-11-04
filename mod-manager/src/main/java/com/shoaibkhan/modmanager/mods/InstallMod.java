package com.shoaibkhan.modmanager.mods;

import javax.swing.JOptionPane;

import com.shoaibkhan.modmanager.utils.ActionResult;

public class InstallMod {
    public static ActionResult installMod(String modName, double minecraftVersion) {
        try {

            // Get mod's versions, file name, download urls for the current minecraft
            // version
            Object[] versions = (ModConfigJSON.getVersionsList(modName, minecraftVersion)).toArray();
            Object[] fileNames = (ModConfigJSON.getFileNameList(modName, minecraftVersion)).toArray();
            Object[] downloadUrls = (ModConfigJSON.getDownloadUrlList(modName, minecraftVersion)).toArray();

            // Get version to install from user
            int response = JOptionPane.showOptionDialog(null, "Choose a version to install", "Version selection",
                    JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, versions, versions[0]);

            String fileNameToInstall = (String) fileNames[response];
            String downloadUrlToInstall = (String) downloadUrls[response];

            // Download mod

        } catch (Exception e) {
            e.printStackTrace();
        }
        return ActionResult.FAILED;
    }
}
