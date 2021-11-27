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

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;

import com.shoaibkhan.modmanager.profiles.CurrentProfile;
import com.shoaibkhan.modmanager.utils.ActionResult;

public class UninstallMod {
    public static ActionResult uninstallMod(String modName) {
        // Double check if installed or not!
        if (!CheckIfInstalled.checkIfInstalled(modName))
            return ActionResult.MOD_NOT_INSTALLED;

        try {
            // Check mods folder is present
            String modFolderPath = Paths.get(CurrentProfile.getCurrentProfileDirectory(), "mods").toString();

            // Create mods folder if not present
            if (!Files.exists(Paths.get(modFolderPath)))
                Files.createDirectory(Paths.get(modFolderPath));

            // get mods list
            File modsFolder = new File(modFolderPath);
            String[] modsList = modsFolder.list();

            for (String name : modsList) {
                if (name.contains(modName)) {
                    // Delete the mod file
                    new File(Paths.get(modFolderPath, name).toString()).delete();

                    return ActionResult.PASS;
                }

            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return ActionResult.FAILED;
    }
}
