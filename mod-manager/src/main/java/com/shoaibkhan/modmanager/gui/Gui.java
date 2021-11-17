package com.shoaibkhan.modmanager.gui;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;

import com.shoaibkhan.modmanager.configs.ModsJSON;
import com.shoaibkhan.modmanager.gui.menu.FileMenu;
import com.shoaibkhan.modmanager.gui.panels.ModsListPanel;
import com.shoaibkhan.modmanager.gui.panels.ProfilesPanel;

public class Gui {
    public static JPanel modsListPanel;
    public static JFrame mainFrame;

    public static void init() {

        mainFrame = new JFrame("Mod Manager");
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setResizable(false);
        mainFrame.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                ModsJSON.DeleteTempFolder();
            }
        });

        // Add the file menu
        FileMenu fileMenu = new FileMenu();
        mainFrame.setJMenuBar(fileMenu);
        // main container
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

        ProfilesPanel profilesPanel = new ProfilesPanel();
        mainPanel.add(profilesPanel);

        modsListPanel = new JPanel();
        mainPanel.add(modsListPanel);

        // Load mods list panel
        ModsListPanel modsListPanel = new ModsListPanel();
        modsListPanel.start();

        mainFrame.add(mainPanel);
        mainFrame.pack();
        mainFrame.setVisible(true);
    }
}
