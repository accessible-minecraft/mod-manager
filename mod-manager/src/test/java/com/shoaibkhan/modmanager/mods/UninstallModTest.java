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
            // Create mods folder if not present
            String modFolderPath = Paths.get(CurrentProfile.getCurrentProfileDirectory(), "mods").toString();
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
