package com.shoaibkhan.modmanager.gui.panels;

import java.awt.FlowLayout;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JPanel;

import com.shoaibkhan.modmanager.configs.ModsJSON;
import com.shoaibkhan.modmanager.gui.Gui;
import com.shoaibkhan.modmanager.gui.widgets.common.ButtonLabel;
import com.shoaibkhan.modmanager.gui.widgets.common.HeadingLabel;
import com.shoaibkhan.modmanager.gui.widgets.mods.ChangeVersionButton;
import com.shoaibkhan.modmanager.gui.widgets.mods.InstallOrUninstallButton;
import com.shoaibkhan.modmanager.mods.CheckIfInstalled;
import com.shoaibkhan.modmanager.profiles.CurrentProfile;

public class ModsListPanel extends Thread {
    public void run() {

        BoxLayout layout = new BoxLayout(Gui.modsListPanel, BoxLayout.Y_AXIS);

        Gui.modsListPanel.setLayout(layout);
        ModsJSON.loadIfNotPresent();

        // Remove all components in modsListPanel
        Gui.modsListPanel.removeAll();

        // Add Heading Before mods list
        HeadingLabel headingLabel = new HeadingLabel("Supported Mods:-");
        JPanel headingPanel = new JPanel();
        headingPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        headingPanel.add(headingLabel);

        Gui.modsListPanel.add(headingPanel);

        // Get supported mods list and panels for each mod
        double minecraftVersion = CurrentProfile.getCurrentProfileVersion();
        List<String> modsList = ModsJSON.getSupportedModsList(minecraftVersion);

        for (String modName : modsList) {
            JPanel panel = new JPanel();
            panel.setLayout(new FlowLayout(FlowLayout.LEFT));

            boolean isInstalled = CheckIfInstalled.checkIfInstalled(modName);

            // Remove (-) from mod name
            String modifiedName = "";
            if (modName.contains("-"))
                modifiedName = modName.replaceAll("-", " ");

            ButtonLabel modNameLabel = new ButtonLabel(
                    modifiedName + (isInstalled ? " (installed)" : " (not installed)"));
            panel.add(modNameLabel);

            InstallOrUninstallButton installOrUninstall = new InstallOrUninstallButton(
                    isInstalled ? "Uninstall" : "Install", isInstalled, modName, minecraftVersion);
            panel.add(installOrUninstall);

            ChangeVersionButton changeVersionButton = new ChangeVersionButton("Change Version", modName,
                    minecraftVersion);
            // Disable if the mod is not installed
            changeVersionButton.setEnabled(true);
            if (!isInstalled) {
                changeVersionButton.setEnabled(false);
            }
            panel.add(changeVersionButton);

            Gui.modsListPanel.add(panel);
        }

        Gui.modsListPanel.setMinimumSize(Gui.modsListPanel.getMinimumSize());

        Gui.mainFrame.pack();
        Gui.mainFrame.revalidate();
    }

}
