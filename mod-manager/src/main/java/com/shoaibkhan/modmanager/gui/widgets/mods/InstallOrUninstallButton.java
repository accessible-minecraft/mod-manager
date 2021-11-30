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

import com.shoaibkhan.modmanager.gui.panels.ModsPanel;
import com.shoaibkhan.modmanager.gui.widgets.base.BaseButton;
import com.shoaibkhan.modmanager.mods.CheckIfInstalled;
import com.shoaibkhan.modmanager.mods.InstallMod;
import com.shoaibkhan.modmanager.mods.UninstallMod;
import com.shoaibkhan.modmanager.profiles.CurrentProfile;
import com.shoaibkhan.modmanager.utils.ActionResult;
import javax.swing.JOptionPane;

/**
 *
 * @author shoaib
 */
public class InstallOrUninstallButton extends BaseButton {

    public InstallOrUninstallButton() {
        refresh();

        this.addActionListener(e -> {
            String modName = (String) ModsPanel.modsComboBox.getSelectedItem();
            double minecraftVersion = CurrentProfile.getCurrentProfileVersion();
            boolean isInstalled = CheckIfInstalled.checkIfInstalled(modName);

            if (isInstalled) {
                // Delete the mod

                // Confirm again
                int res = JOptionPane.showConfirmDialog(this, "Are you sure you want to delete this mod??");
                if (res != 0) {
                    return;
                }

                // Remove mod file
                ActionResult response = UninstallMod.uninstallMod(modName);
                if (response != ActionResult.PASS) {
                    JOptionPane.showMessageDialog(this, response.getDescription());
                }

            } else {
                // Install the mod
                ActionResult response = InstallMod.installMod(modName, minecraftVersion);
                if (response != ActionResult.PASS) {
                    if (response != ActionResult.CLOSE) // Skip if user closed the option dialogue
                    {
                        JOptionPane.showMessageDialog(this, response.getDescription());
                    }
                    return;
                }

                JOptionPane.showMessageDialog(this, modName + " installed", "Mod Installed Successfully",
                        JOptionPane.INFORMATION_MESSAGE);
            }

            // Update mods panel
            if (ModsPanel.installUnistallButton != null) {
                ModsPanel.installUnistallButton.refresh();
                ModsPanel.changeVersionButton.refresh();
            }
        });
    }

    public final void refresh() {
        String modName;
        try {
            modName = (String) ModsPanel.modsComboBox.getSelectedItem();
        } catch (Exception error) {
            modName = "fabric-api";
        }
        boolean isInstalled = CheckIfInstalled.checkIfInstalled(modName);

        if (isInstalled) {
            this.setText("Uninstall Mod");
        } else {
            this.setText("Install Mod");
        }
    }
}
