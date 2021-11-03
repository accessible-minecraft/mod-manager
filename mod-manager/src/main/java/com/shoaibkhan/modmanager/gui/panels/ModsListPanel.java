package com.shoaibkhan.modmanager.gui.panels;

import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.shoaibkhan.modmanager.gui.Gui;
import com.shoaibkhan.modmanager.mods.SupportedModsList;

public class ModsListPanel extends Thread {
    public void run() {

        BoxLayout layout = new BoxLayout(Gui.modsListPanel, BoxLayout.Y_AXIS);

        Gui.modsListPanel.setLayout(layout);
        SupportedModsList.loadIfNotPresent();

        // Get supported mods list and panels for each mod
        List<String> modsList = SupportedModsList.getSupportedModsList();
        for (String mod : modsList) {
            JPanel panel = new JPanel();
            panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
            panel.setAlignmentX(JPanel.RIGHT_ALIGNMENT);

            JLabel modName = new JLabel(mod);
            panel.add(modName);

            JButton installOrUninstall = new JButton("Install");
            panel.add(installOrUninstall);

            Gui.modsListPanel.add(panel);
        }

        Gui.f.pack();
        Gui.f.revalidate();
    }

}
