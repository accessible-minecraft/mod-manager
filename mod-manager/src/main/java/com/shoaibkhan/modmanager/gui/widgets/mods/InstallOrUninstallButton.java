package com.shoaibkhan.modmanager.gui.widgets.mods;

import javax.swing.JOptionPane;
import javax.swing.plaf.DimensionUIResource;

import com.shoaibkhan.modmanager.gui.panels.ModsListPanel;
import com.shoaibkhan.modmanager.gui.widgets.base.BaseButton;
import com.shoaibkhan.modmanager.mods.InstallMod;
import com.shoaibkhan.modmanager.mods.UninstallMod;
import com.shoaibkhan.modmanager.utils.ActionResult;

public class InstallOrUninstallButton extends BaseButton {
    public InstallOrUninstallButton(String text, boolean isInstalled, String forMod, double minecraftVersion) {
        super(text);
        this.setPreferredSize(new DimensionUIResource(150, 50));

        this.addActionListener(e -> {
            if (isInstalled) {
                // Confirm again
                int res = JOptionPane.showConfirmDialog(this, "Are you sure you want to delete this mod??");
                if (res != 0)
                    return;

                // Remove mod file
                ActionResult response = UninstallMod.uninstallMod(forMod);
                if (response != ActionResult.PASS) {
                    JOptionPane.showMessageDialog(this, response.getDescription());
                }

            } else {
                ActionResult response = InstallMod.installMod(forMod, minecraftVersion);
                if (response != ActionResult.PASS) {
                    if (response != ActionResult.CLOSE)
                        // Skip if user closed the option dialogue
                        JOptionPane.showMessageDialog(this, response.getDescription());
                    return;
                }

                JOptionPane.showMessageDialog(this, forMod + " installed", "Mod Installed Successfully",
                        JOptionPane.INFORMATION_MESSAGE);
            }

            // Refresh mods list panel
            ModsListPanel modsListPanel = new ModsListPanel();
            modsListPanel.start();
        });
    }
}
