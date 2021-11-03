package com.shoaibkhan.modmanager.gui;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;

import com.shoaibkhan.modmanager.gui.panels.ModsListPanel;
import com.shoaibkhan.modmanager.gui.panels.ProfilesPanel;
import com.shoaibkhan.modmanager.mods.SupportedModsList;

public class Gui {
    public static JPanel modsListPanel;
    public static JFrame f;

    public static void init() {

        f = new JFrame("Mod Manager");
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setResizable(false);
        f.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                SupportedModsList.DeleteTemporaryFile();
            }
        });

        // main container
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

        ProfilesPanel profilesPanel = new ProfilesPanel();
        mainPanel.add(profilesPanel);

        modsListPanel = new JPanel();
        modsListPanel.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0));
        mainPanel.add(modsListPanel);

        // Load mods list panel
        ModsListPanel modsListPanel = new ModsListPanel();
        modsListPanel.start();

        f.add(mainPanel);
        f.pack();
        f.setVisible(true);
    }
}
