package com.shoaibkhan.modmanager.gui.widgets;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.plaf.DimensionUIResource;

import com.shoaibkhan.modmanager.gui.panels.ModsListPanel;
import com.shoaibkhan.modmanager.mods.InstallMod;
import com.shoaibkhan.modmanager.mods.UninstallMod;
import com.shoaibkhan.modmanager.utils.ActionResult;

public class InstallOrUninstallButton extends JButton {
    public InstallOrUninstallButton(String text, boolean isInstalled, String forMod, double minecraftVersion) {
        super(text);
        this.setPreferredSize(new DimensionUIResource(150, 50));
        this.setToolTipText("Add Profile Button");

        this.addActionListener(e -> {
            if (isInstalled) {
                // Remove mod file
                ActionResult response = UninstallMod.uninstallMod(forMod);
                if (response != ActionResult.PASS) {
                    JOptionPane.showMessageDialog(this, response.getDescription());
                }

                // Refresh mods list panel
                ModsListPanel modsListPanel = new ModsListPanel();
                modsListPanel.start();
            } else {
                InstallMod.installMod(forMod, minecraftVersion);
            }
        });
    }
}
