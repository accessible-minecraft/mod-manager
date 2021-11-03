package com.shoaibkhan.modmanager.gui.panels;

import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.shoaibkhan.modmanager.config.SupportedMods;
import com.shoaibkhan.modmanager.gui.Gui;
import com.shoaibkhan.modmanager.mods.CheckIfInstalled;

public class ModsListPanel extends Thread {
    public void run() {

        BoxLayout layout = new BoxLayout(Gui.modsListPanel, BoxLayout.Y_AXIS);

        Gui.modsListPanel.setLayout(layout);
        SupportedMods.loadIfNotPresent();

        // Get supported mods list and panels for each mod
        List<String> modsList = SupportedMods.getSupportedModsList();
        for (String modName : modsList) {
            JPanel panel = new JPanel();
            panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
            panel.setAlignmentX(JPanel.RIGHT_ALIGNMENT);

            JLabel modNameLabel = new JLabel(modName);
            panel.add(modNameLabel);

            boolean isInstalled = CheckIfInstalled.checkIfInstalled(modName);

            JButton installOrUninstall = new JButton(isInstalled ? "Uninstall": "Install");
            panel.add(installOrUninstall);

            Gui.modsListPanel.add(panel);
        }

        Gui.mainFrame.pack();
        Gui.mainFrame.revalidate();
    }

}
