package com.shoaibkhan.modmanager.mods;

import java.io.File;
import java.net.URL;
import java.nio.file.Paths;

import javax.swing.JOptionPane;

import com.shoaibkhan.modmanager.profiles.CurrentProfile;
import com.shoaibkhan.modmanager.utils.ActionResult;

import org.apache.commons.io.FileUtils;

public class ChangeVersion {
    public static ActionResult changeVersion(String modName, double minecraftVersion){
        try {

            // Get mod's versions, file name, download urls for the current minecraft
            // version
            Object[] versions = (ModConfigJSON.getVersionsList(modName, minecraftVersion)).toArray();
            Object[] fileNames = (ModConfigJSON.getFileNameList(modName, minecraftVersion)).toArray();
            Object[] downloadUrls = (ModConfigJSON.getDownloadUrlList(modName, minecraftVersion)).toArray();

            // Get version to install from user
            int response = JOptionPane.showOptionDialog(null, "Choose a version to install", "Version selection",
                    JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, versions, null);

            if (response < 0) {
                // Didn't chose anything, closed the option dialogue
                return ActionResult.CLOSE;
            }

            String fileNameToInstall = (String) fileNames[response];
            String downloadUrlToInstall = (String) downloadUrls[response];

            // Confirm again
            int res = JOptionPane.showConfirmDialog(null, "Are you sure you want to change the version of the mod??");
            if (res != 0)
                return ActionResult.CLOSE;

            // Delete the previous version of the mod
            ActionResult uninstallResponse = UninstallMod.uninstallMod(modName);
            if(uninstallResponse!=ActionResult.PASS)
                return ActionResult.CLOSE;

            // Download new version of the mod
            String filePath = Paths.get(CurrentProfile.getCurrentProfileDirectory(), "mods", fileNameToInstall)
                    .toString();
            File modFile = new File(filePath);
            FileUtils.copyURLToFile(new URL(downloadUrlToInstall), modFile);

            return ActionResult.PASS;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return ActionResult.FAILED;
    }
}
