package com.shoaibkhan.modmanager.gui.widgets.mods;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Paths;

import javax.swing.JOptionPane;
import javax.swing.plaf.DimensionUIResource;

import com.shoaibkhan.modmanager.gui.widgets.base.BaseButton;
import com.shoaibkhan.modmanager.profiles.CurrentProfile;
import com.shoaibkhan.modmanager.profiles.utils;

import org.apache.commons.io.FileUtils;

public class NvdaDllButton extends BaseButton {
    public NvdaDllButton() {
        this.setPreferredSize(new DimensionUIResource(150, 50));

        this.addActionListener(e -> {
            if (utils.getOS() != utils.OS.WINDOWS)
                return;

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
                return;
            } catch (IOException error) {
                error.printStackTrace();
            }
        });
    }

    private boolean is64Bit() {
        // From https://stackoverflow.com/questions/1856565/how-do-you-determine-32-or-64-bit-architecture-of-windows-using-java
        boolean is64bit = false;
        if (System.getProperty("os.name").contains("Windows")) {
            is64bit = (System.getenv("ProgramFiles(x86)") != null);
        } else {
            is64bit = (System.getProperty("os.arch").indexOf("64") != -1);
        }

        return is64bit;
    }
}
