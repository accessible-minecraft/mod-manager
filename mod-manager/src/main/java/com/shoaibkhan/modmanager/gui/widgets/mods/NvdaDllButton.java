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
package com.shoaibkhan.modmanager.gui.widgets.mods;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Paths;

import javax.swing.JOptionPane;

import com.shoaibkhan.modmanager.gui.widgets.base.BaseButton;
import com.shoaibkhan.modmanager.profiles.CurrentProfile;
import com.shoaibkhan.modmanager.profiles.utils;

import org.apache.commons.io.FileUtils;

public class NvdaDllButton extends BaseButton {

    public NvdaDllButton() {

        this.addActionListener(e -> {
            if (utils.getOS() != utils.OS.WINDOWS) {
                return;
            }

            try {
                // Get file url for the correct architecture
                String fileUrl = "https://github.com/accessible-minecraft/mod-manager-configs/raw/main/nvdaControllerClient32.dll";
                String fileNameToInstall = "nvdaControllerClient32.dll";
                if (is64Bit()) {
                    fileUrl = "https://github.com/accessible-minecraft/mod-manager-configs/raw/main/nvdaControllerClient64.dll";
                    fileNameToInstall = "nvdaControllerClient64.dll";
                }

                // Download file
                String filePath = Paths.get(CurrentProfile.getCurrentProfileDirectory(), fileNameToInstall).toString();
                File modFile = new File(filePath);
                FileUtils.copyURLToFile(new URL(fileUrl), modFile);

                JOptionPane.showMessageDialog(this, "Nvda Controller DLL installed", "Nvda DLL Installed Successfully",
                        JOptionPane.INFORMATION_MESSAGE);
            } catch (IOException error) {
                error.printStackTrace();
            }
        });
    }

    private boolean is64Bit() {
        // From https://stackoverflow.com/questions/1856565/how-do-you-determine-32-or-64-bit-architecture-of-windows-using-java
        boolean is64bit;
        if (System.getProperty("os.name").contains("Windows")) {
            is64bit = (System.getenv("ProgramFiles(x86)") != null);
        } else {
            is64bit = (System.getProperty("os.arch").contains("64"));
        }

        return is64bit;
    }
}
