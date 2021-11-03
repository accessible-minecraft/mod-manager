package com.shoaibkhan.modmanager.gui.panels;

import java.awt.FlowLayout;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.plaf.DimensionUIResource;

import com.shoaibkhan.modmanager.configs.SupportedMods;
import com.shoaibkhan.modmanager.gui.Gui;
import com.shoaibkhan.modmanager.gui.widgets.FocusableLabel;
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
            panel.setAlignmentX(JPanel.RIGHT_ALIGNMENT);

            boolean isInstalled = CheckIfInstalled.checkIfInstalled(modName);

            // Remove (-) from mod name
            String modifiedName = "";
            if (modName.contains("-"))
                modifiedName = modName.replaceAll("-", " ");

            FocusableLabel modNameLabel = new FocusableLabel(
                    modifiedName + (isInstalled ? " (installed)" : " (not installed)"));
            // To get the width of the text to make the panel's width dynamic
            DimensionUIResource curProfileDimensions = new DimensionUIResource(
                    modNameLabel.getFontMetrics(modNameLabel.getFont()).stringWidth(modNameLabel.getText()), 50);
            modNameLabel.setPreferredSize(new DimensionUIResource(curProfileDimensions.width + 10, 50));
            panel.add(modNameLabel);

            JButton installOrUninstall = new JButton(isInstalled ? "Uninstall" : "Install");
            installOrUninstall.setPreferredSize(new DimensionUIResource(150, 50));
            installOrUninstall.addActionListener(e -> {
                String[] options = { "abc", "def", "ghi", "jkl" };
                JOptionPane.showOptionDialog(null, "Returns the position of your choice on the array", "Click a button",
                        JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);
            });
            panel.add(installOrUninstall);

            Gui.modsListPanel.add(panel);
        }

        Gui.modsListPanel.setMinimumSize(Gui.modsListPanel.getMinimumSize());

        Gui.mainFrame.pack();
        Gui.mainFrame.revalidate();
    }

}
