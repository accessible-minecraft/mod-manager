package com.shoaibkhan.modmanager.gui.panels;

import java.awt.FlowLayout;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JPanel;

import com.shoaibkhan.modmanager.configs.SupportedMods;
import com.shoaibkhan.modmanager.gui.Gui;
import com.shoaibkhan.modmanager.gui.widgets.FocusableLabel;
import com.shoaibkhan.modmanager.gui.widgets.InstallOrUninstallButton;
import com.shoaibkhan.modmanager.mods.CheckIfInstalled;
import com.shoaibkhan.modmanager.profiles.CurrentProfile;

public class ModsListPanel extends Thread {
    public void run() {

        BoxLayout layout = new BoxLayout(Gui.modsListPanel, BoxLayout.Y_AXIS);

        Gui.modsListPanel.setLayout(layout);
        SupportedMods.loadIfNotPresent();

        // Remove all components in modsListPanel
        Gui.modsListPanel.removeAll();

        // Get supported mods list and panels for each mod
        double version = CurrentProfile.getCurrentProfileVersion();
        List<String> modsList = SupportedMods.getSupportedModsListFor1_17();

        if (Double.compare(version, 1.17) == 0)
            modsList = SupportedMods.getSupportedModsListFor1_17();
        else if (Double.compare(version, 1.16) == 0)
            modsList = SupportedMods.getSupportedModsListFor1_16();

        for (String modName : modsList) {
            JPanel panel = new JPanel();
            panel.setLayout(new FlowLayout(FlowLayout.LEFT));

            boolean isInstalled = CheckIfInstalled.checkIfInstalled(modName);

            // Remove (-) from mod name
            String modifiedName = "";
            if (modName.contains("-"))
                modifiedName = modName.replaceAll("-", " ");

            FocusableLabel modNameLabel = new FocusableLabel(
                    modifiedName + (isInstalled ? " (installed)" : " (not installed)"));
            panel.add(modNameLabel);

            InstallOrUninstallButton installOrUninstall = new InstallOrUninstallButton(
                    isInstalled ? "Uninstall" : "Install", isInstalled, modName);
            panel.add(installOrUninstall);

            Gui.modsListPanel.add(panel);
        }

        Gui.modsListPanel.setMinimumSize(Gui.modsListPanel.getMinimumSize());

        Gui.mainFrame.pack();
        Gui.mainFrame.revalidate();
    }

}
