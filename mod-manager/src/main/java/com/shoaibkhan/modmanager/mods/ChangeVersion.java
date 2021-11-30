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
package com.shoaibkhan.modmanager.mods;

import com.shoaibkhan.modmanager.gui.dialog.ChooseVersionDialog;
import com.shoaibkhan.modmanager.gui.dialog.DownloadDialog;
import java.net.URL;
import java.nio.file.Paths;


import com.shoaibkhan.modmanager.profiles.CurrentProfile;
import com.shoaibkhan.modmanager.utils.ActionResult;
import java.awt.HeadlessException;
import java.net.MalformedURLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ChangeVersion {

    public static ActionResult changeVersion(String modName, double minecraftVersion) {
        try {

            // Get mod's versions, file name, download urls for the current minecraft
            // version
            Object[] versions = (ModConfigJSON.getVersionsList(modName, minecraftVersion)).toArray();
            Object[] fileNames = (ModConfigJSON.getFileNameList(modName, minecraftVersion)).toArray();
            Object[] downloadUrls = (ModConfigJSON.getDownloadUrlList(modName, minecraftVersion)).toArray();

            // Get version to install from user
            int response = new ChooseVersionDialog(null, false).choose(versions);

            if (response < 0) {
                // An error occured || Didn't chose anything, closed the option dialogue
                return ActionResult.CLOSE;
            }

            String fileNameToInstall = (String) fileNames[response];
            String downloadUrlToInstall = (String) downloadUrls[response];

            // Delete the previous version of the mod
            ActionResult uninstallResponse = UninstallMod.uninstallMod(modName);
            if (uninstallResponse != ActionResult.PASS) {
                return ActionResult.CLOSE;
            }

            // Download new version of the mod
            String filePath = Paths.get(CurrentProfile.getCurrentProfileDirectory(), "mods", fileNameToInstall)
                    .toString();
            try {
                new DownloadDialog(null, true).startDownloading(new URL(downloadUrlToInstall), filePath);
            } catch (MalformedURLException ex) {
                Logger.getLogger(InstallMod.class.getName()).log(Level.SEVERE, null, ex);
            }

            return ActionResult.PASS;

        } catch (HeadlessException e) {
            e.printStackTrace();
        }
        return ActionResult.FAILED;
    }
}
